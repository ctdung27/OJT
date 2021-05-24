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
import ait.team.java.converter.EventConverter;
import ait.team.java.converter.UserConverter;
import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.ClassDTO;
import ait.team.java.dto.EventDTO;
import ait.team.java.dto.UserDTO;
import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.CategoryEntity;
import ait.team.java.entity.ClassEntity;
import ait.team.java.entity.CommentEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.AbilityRepository;
import ait.team.java.repository.CategoryRepository;
import ait.team.java.repository.EventRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IEventService;

@Service
public class EventService implements IEventService{
	@Autowired
	EventRepository eventRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	AbilityRepository abilityRepository;
	@Autowired
	EventConverter eventConverter;
	@Autowired
	CategoryConverter categoryConverter;
	@Autowired
	AbilityConverter abilityConverter;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ClassConverter classConverter;
	@Autowired
	UserConverter userConverter;
	@Override
	public EventDTO save(EventDTO eventDTO) {
		EventEntity entity = new EventEntity();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = "";
		if (authentication == null || !authentication.isAuthenticated()) {
		    
	    }else {
	    	userName = authentication.getName();
	    }
		if(eventDTO.getId() != null) {
			EventEntity OldEntity = eventRepository.findOne(eventDTO.getId());
			if(userName.equals(OldEntity.getUsers().getUserName()) && OldEntity.getStatus() != 1) {
				entity = eventConverter.toEntity(eventDTO,OldEntity);
			}else {
				return null;
			}
		}else {
			entity = eventConverter.toEntity(eventDTO);
	        UserEntity user = userRepository.findByUserName(userName);
			entity.setUsers(user);
			for (ClassEntity classEntity : user.getClasses()) {
				if(classEntity.getStatus() == 1) {
					entity.setClasses(classEntity);
				}
			}
		}
		CategoryEntity ca_entity = categoryRepository.findOne(eventDTO.getCategoryId());
		entity.setCategory(ca_entity);
		entity = eventRepository.save(entity);
		EventDTO dto = eventConverter.toDTO(entity);
		dto.setCategorId(categoryConverter.toDTO(ca_entity).getId());
		return dto;
	}
	@Override
	public EventDTO findOne(long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		UserEntity user = userRepository.findByUserName(userName);
		EventEntity entity = eventRepository.findOne(id);
		int check = 0;
		if(user.getId() == entity.getUsers().getAdviser().getId() || user.getId() == entity.getUsers().getParent().getId()  || user.getId() == entity.getUsers().getId()) {
			check = 1;
		}else {
			for (ClassEntity classes : entity.getUsers().getClasses()) {
				for (UserEntity userc : classes.getUsers()) {
					if(userc.getId() == user.getId() && userc.getRoles().get(0).getCode().equals("002") ) {
						check = 1;
						break;
					}
				}
				if(check == 1) {
					break;
				}
			}
		}
		
		if(check == 1) {
			if(entity != null) {
				EventDTO dto = eventConverter.toDTO(entity);
				CategoryDTO cateDTO = categoryConverter.toDTO(entity.getCategory());
				ClassDTO classes = classConverter.toDTO(entity.getClasses());
				UserDTO teacherDTO = new UserDTO();
				for (UserEntity userEntity : entity.getClasses().getUsers()) {
					if(userEntity.getRoles().get(0).getCode().equals("002")) {
						teacherDTO = userConverter.toDTO(userEntity);
						break;
					}
				}
				classes.setTeacher(teacherDTO);
				dto.setClasses(classes);
				dto.setCategory(cateDTO);
				dto.setCategorId(cateDTO.getId());
				return dto;
			}
		}
		
		return null;
	}
	@Override
	public List<CategoryDTO> findCategoryAll() {
		List<CategoryEntity> entity = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (CategoryEntity category : entity) {
			categoryDTOs.add(categoryConverter.toDTO(category));
		}
		return categoryDTOs;
	}
	@Override
	public boolean delete(long[] ids) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = "";
		if (authentication == null || !authentication.isAuthenticated()) {
		    return false;
		}else {
			userName = authentication.getName();
		}
		EventEntity entity;
		for (long id : ids) {
			entity = eventRepository.findOne(id);
			if(userName.equals(entity.getUsers().getUserName()) && entity.getStatus() != 1) {
				try {
					eventRepository.delete(id);
				} catch (Exception e) {
					return false;
				}
				
			}
		}
		return true;
		
	}
	@Override
	public List<EventDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EventDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AbilityDTO> findAllByOrderByIdAsc(long id) {
		EventEntity eventEntity = eventRepository.findById(id);
		List<AbilityDTO> listDTO = new ArrayList<>();
		List<String> listName = new ArrayList<>();
		if(eventEntity != null) {
			for (CommentEntity comment : eventEntity.getComments()) {
				for (AbilityEntity ability : comment.getAbilities()) {
					listName.add(ability.getName());
				}
			}
			List<AbilityEntity> listEntity = abilityRepository.findAll();
			for (AbilityEntity abilityEntity : listEntity) {
				int check = 0;
				for (String name : listName) {
					if(name.equals(abilityEntity.getName())) {
						check = 1;
						break;
					}
				}
				if(check == 0) {
					listDTO.add(abilityConverter.toDTO(abilityEntity));
				}
			}
			return listDTO;
		}
		return null;
	}
	
}
