package ait.team.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ait.team.java.converter.AbilityConverter;
import ait.team.java.converter.CategoryConverter;
import ait.team.java.converter.ClassConverter;
import ait.team.java.converter.CommentConverter;
import ait.team.java.converter.EventConverter;
import ait.team.java.converter.RoleConverter;
import ait.team.java.converter.UserConverter;
import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.ClassDTO;
import ait.team.java.dto.CommentDTO;
import ait.team.java.dto.EventDTO;
import ait.team.java.dto.RoleDTO;
import ait.team.java.dto.UserDTO;
import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.CategoryEntity;
import ait.team.java.entity.ClassEntity;
import ait.team.java.entity.CommentEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.RoleEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.AbilityRepository;
import ait.team.java.repository.CategoryRepository;
import ait.team.java.repository.ClassRepository;
import ait.team.java.repository.RoleRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IStudentListService;

@Service
public class StudentListService implements IStudentListService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	AbilityRepository abilityRepository;
	@Autowired
	UserConverter userConvert;
	@Autowired
	EventConverter eventConverter;
	@Autowired
	ClassConverter classConverter;
	@Autowired
	CategoryConverter categoryConverter;
	@Autowired
	CommentConverter commentConverter;
	@Autowired
	AbilityConverter abilityConverter;
	@Autowired
	RoleConverter roleConverter;
	@Autowired
	RoleRepository roleRepository;
	/*
	 * entities: List user from dtb
	 * usersDTO: List user used to interact client
	 * Nối các bảng users-comment-ability lấy 12 khả năg
	 * Và các bảng users-class lấy lớp của các học sinh
	 * Các bảng users-event-category lấy các sự kiện 
	 * Code by Huypq
	 * Fix by: diennv
	 * */
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		List<UserEntity> entities = userRepository.findAllByOrderByCodeAsc();
		List<UserDTO> usersDTO = new ArrayList<>();

		return ListConvert(entities, usersDTO);

	}
	@Override
	public List<AbilityDTO> findAllByOrderByIdAsc() {
		// TODO Auto-generated method stub
		List<AbilityEntity> entities = abilityRepository.findAllByOrderByIdAsc();
		List<AbilityDTO> abilities = new ArrayList<>();
		for(AbilityEntity ability : entities) {
			abilities.add(abilityConverter.toDTO(ability));
		}
		return abilities;
	}
	@Override
	public List<ClassDTO> findClass() {
		List<ClassEntity> entities = classRepository.findAllByOrderByIdAsc();
		List<ClassDTO> classes = new ArrayList<>();
		for(ClassEntity classEntity : entities) {
			classes.add(classConverter.toDTO(classEntity));
		}
		// TODO Auto-generated method stub
		return classes;
	}
	@Override
	public List<CategoryDTO> findEvent() {
		List<CategoryEntity> entities = categoryRepository.findAll();
		List<CategoryDTO> categories = new ArrayList<>();
		for(CategoryEntity cateEntity : entities) {
			categories.add(categoryConverter.toDTO(cateEntity));
		}
		// TODO Auto-generated method stub
		return categories;
	}
	@Override
    public int totalItem(String studentName,String classId,String eventId,String hashTag) {
        // TODO Auto-generated method stub
        int total = 0;
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity role = roleRepository.findByCode("001");
        roles.add(role);
        if(studentName!=null) {
        	if(studentName.equals("")) 
        		{studentName = null;}
        	else {
        		studentName = "%"+studentName.toLowerCase()+"%";
        	}
        }
        if(classId!=null) {if(classId.equals("")) {classId = null;}}
        if(eventId!=null) {if(eventId.equals("")) {eventId = null;}}
        if(hashTag!=null) {if(hashTag.equals("")) {hashTag = null;}}
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userRepository.findByUserName(userName);
        CategoryEntity category = categoryRepository.findByCode(eventId);
        for (RoleEntity roleEntity : user.getRoles()) {
            if(roleEntity.getCode().equals("003")) {
                total = userRepository.countUser(roles, user, null, null, studentName, classId, category, hashTag);
                break;
            }else if(roleEntity.getCode().equals("004")) {
                total = userRepository.countUser(roles, null, user, null, studentName, classId, category, hashTag);
                break;
            }else if(roleEntity.getCode().equals("002")) {
                List<ClassEntity> classes = user.getClasses();
                total = userRepository.countUser(roles, null, null, classes, studentName, classId, category, hashTag);
                break;
            }
        }
        return total;
    }
	
	/*
	 * entities: List user from dtb
	 * usersDTO: List user used to interact client
	 * Nối các bảng users-comment-ability lấy 12 khả năg
	 * Và các bảng users-class lấy lớp của các học sinh
	 * Các bảng users-event-category lấy các sự kiện 
	 * Thực hiện chức năng phân trang
	 * Role các user là học sinh ra màn hình
	 * Phân quyền cho các user như: gia đình, bác sĩ, giáo viên
	 * Code by Huypq
	 * Fix by: diennv
	 * */
	@Override
    public List<UserDTO> findAll(Pageable pageable,String studentName,String classId,String eventId,String hashTag) {
        List<UserDTO> results = new ArrayList<>();
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity role = roleRepository.findByCode("001");
        roles.add(role);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userRepository.findByUserName(userName);
        List<UserEntity> entities = null;
        CategoryEntity category = categoryRepository.findByCode(eventId);
        if(studentName!=null) {
        	if(studentName.equals("")) 
        		{studentName = null;}
        	else {
        		studentName = "%"+studentName.toLowerCase()+"%";
        	}
        }
        if(classId!=null) {if(classId.equals("")) {classId = null;}}
        if(eventId!=null) {if(eventId.equals("")) {eventId = null;}}
        if(hashTag!=null) {if(hashTag.equals("")) {hashTag = null;}}
        for (RoleEntity roleEntity : user.getRoles()) {
            if(roleEntity.getCode().equals("003")) {
                entities= userRepository.findByUserContaining(roles, user, null, null, studentName, classId, category ,hashTag, pageable);
                break;
            }else if(roleEntity.getCode().equals("004")) {
                entities= userRepository.findByUserContaining(roles, null, user, null, studentName, classId ,category , hashTag, pageable);
                break;
            }else if(roleEntity.getCode().equals("002")) {
                List<ClassEntity> classes = user.getClasses();
                entities = userRepository.findByUserContaining(roles, null, null, classes, studentName, classId, category, hashTag,  pageable);
                break;
            }
        }
        return ListConvert(entities, results);
    }
	
	public List<UserDTO> ListConvert(List<UserEntity> entities, List<UserDTO> results) {
		
		for (UserEntity item: entities) {
			List<EventDTO> eventDTO = new ArrayList<>();
			for (EventEntity entity : item.getEvents()) {
				EventDTO event = eventConverter.toDTO(entity);
				event.setCategory(categoryConverter.toDTO(entity.getCategory()));
				event.setCategorId(entity.getCategory().getId());
				List<CommentDTO> commnentDTOs = new ArrayList<>();
				for (CommentEntity commentEntity : entity.getComments()) {
					CommentDTO commentDTO = commentConverter.toDTO(commentEntity);
					List<AbilityDTO> abilities = new ArrayList<>();
					for (AbilityEntity abilityEntity : commentEntity.getAbilities()) {
						abilities.add(abilityConverter.toDTO(abilityEntity));
					}
					commentDTO.setAbilities(abilities);
					commnentDTOs.add(commentDTO);
				}
				event.setComments(commnentDTOs);
				eventDTO.add(event);
			}
			List<ClassDTO> classes = new ArrayList<>();
			for (ClassEntity entity : item.getClasses()) {
				ClassDTO classDTO = classConverter.toDTO(entity);
				classes.add(classDTO);
			}
			UserDTO userDTO = userConvert.toDTO(item);
			userDTO.setEvents(eventDTO);
			userDTO.setClasses(classes);
			results.add(userDTO);
		}
		return results;
	}


}
