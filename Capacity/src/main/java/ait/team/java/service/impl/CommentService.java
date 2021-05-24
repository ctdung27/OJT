package ait.team.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ait.team.java.converter.CommentConverter;
import ait.team.java.dto.CommentDTO;
import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.ClassEntity;
import ait.team.java.entity.CommentEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.RoleEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.AbilityRepository;
import ait.team.java.repository.CommentRepository;
import ait.team.java.repository.EventRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.ICommentService;

@Component
public class CommentService implements ICommentService{

	@Autowired
	CommentConverter commentConverter;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AbilityRepository abilityRepository;
	
	
	@Override
	public CommentDTO save(CommentDTO CommentDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		UserEntity userNow = userRepository.findByUserName(userName);
		EventEntity eventEntity = eventRepository.findById(CommentDTO.getEvent_id());
		UserEntity userEvent = eventEntity.getUsers();
		long status = 0;
		CommentDTO.setStatus(status);
		CommentDTO.setStatusAdviser(status);
		CommentDTO.setStatusParent(status);
		CommentDTO.setStatusTeach(status);
		String userParent = "";
		if(userEvent.getParent() != null) {
			userParent = userEvent.getParent().getUserName();
		}
		String userAdviser = "";
		if(userEvent.getAdviser() != null) {
			userAdviser = userEvent.getAdviser().getUserName();
		}
		status = 1;
		int checkClass = 0;
		String roleCode = "";
		for (RoleEntity roleEntity : userNow.getRoles()) {
			if(roleEntity.getCode().equals("001")) {
				CommentDTO.setStatus(status);
			}
			if(roleEntity.getCode().equals("002")) {
				CommentDTO.setStatusTeach(status);
				ClassEntity classtecher = eventEntity.getClasses();
				for (ClassEntity classEntity : userNow.getClasses()) {
					if(classEntity == classtecher) {
						checkClass = 1;
						break;
					}
				}
			}
			if(roleEntity.getCode().equals("003")) {
				CommentDTO.setStatusParent(status);
			}
			if(roleEntity.getCode().equals("004")) {
				CommentDTO.setStatusAdviser(status);
				roleCode = "004";
			}
		}
	
		if(userName.equals(userEvent.getUserName()) || userName.equals(userParent) || userName.equals(userAdviser) || checkClass == 1) {
			List<AbilityEntity> abilities = new ArrayList<>();
			CommentEntity cmtEntity = new CommentEntity();
			if (CommentDTO.getId() != null) {
				CommentEntity oldCommentEntity = commentRepository.findOne(CommentDTO.getId());
				if(oldCommentEntity.getEvent().getStatus() == 1) {
					if(oldCommentEntity.getAbilities().size() > 0) {
						return null;
					}
					
				}
				UserEntity userComment = oldCommentEntity.getUsers();
				if(userComment != null) {
					if(userName.equals(userComment.getUserName())) {
						cmtEntity = commentConverter.toEntity(CommentDTO, oldCommentEntity);
					}else {
						return null;
					}
				}else {
					return null;
				}
			}else {
				cmtEntity = commentConverter.toEntity(CommentDTO);
			}
			
			if(eventEntity.getStatus() != 1) {
				String contentComment = CommentDTO.getAbilityName();
				String[] parts = contentComment.split(",");
				if(parts.length > 1) {
					for (String part : parts) {
						int check = 0;
						for (CommentEntity comment : eventEntity.getComments()) {
							for (AbilityEntity abi : comment.getAbilities()) {
								if(abi.getName().equals(part)) {
									check = 1;
									break;
								}
							}
							if(check == 1) {
								break;
							}
							
						}
						if(check == 0) {
							AbilityEntity abilityEntity = abilityRepository.findByName(part);
							if(abilityEntity != null && roleCode.equals("004")) {
								abilities.add(abilityEntity);
							}
						}
					}
					cmtEntity.setAbilities(abilities);
				}
			}
			cmtEntity.setUsers(userNow);
			cmtEntity.setEvent(eventEntity);
			cmtEntity = commentRepository.save(cmtEntity);
			if(userNow.getRoles().get(0).getCode().equals("004") && cmtEntity != null && eventEntity.getStatus() == (long) 3) {
				eventEntity.setStatus((long) 2);
				eventRepository.save(eventEntity);
			}
			CommentDTO dto = commentConverter.toDTO(cmtEntity);
			return dto;
		}
		return null;
	}

	@Override
	public List<CommentDTO> findAll(Long event_id, Pageable pageable) {
		EventEntity event = eventRepository.findById(event_id);
		List<CommentDTO> results = new ArrayList<>();
		List<CommentEntity> entities = commentRepository.findAllByEventOrderById(event, pageable);
		for (CommentEntity item: entities) {
			CommentDTO CommentDTO = commentConverter.toDTO(item);
			results.add(CommentDTO);
		}
		return results;
	}

	@Override
	public List<CommentDTO> findAll(Long event_id) {
		EventEntity event = eventRepository.findById(event_id);
		List<CommentDTO> results = new ArrayList<>();
		List<CommentEntity> entities = commentRepository.findAllByEventOrderById(event);
		for (CommentEntity item: entities) {
			CommentDTO newDTO = commentConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}

	@Override
	public int totalItem(Long event_id) {
		EventEntity event = eventRepository.findById(event_id);
		return (int) commentRepository.countByEvent(event);
	}

	@Override
	public List<CommentDTO> delete(long[] ids) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<CommentDTO> comments = new ArrayList<>();
		for(long item: ids) {
			CommentEntity oldCommentEntity = commentRepository.findOne(item);
			if(oldCommentEntity.getEvent().getStatus() != 1 || oldCommentEntity.getAbilities().size() == 0) {
				UserEntity userComment = oldCommentEntity.getUsers();
				if(userName.equals(userComment.getUserName())) {
					comments.add(commentConverter.toDTO(oldCommentEntity));
					commentRepository.delete(item);
				}
			}
			if(oldCommentEntity.getEvent().getStatus() == 2) {
				EventEntity eventEntity = oldCommentEntity.getEvent();
				UserEntity userNow = userRepository.findByUserName(userName);
				int t = commentRepository.countByEventAndUsers(eventEntity,userNow);
				if(commentRepository.countByEventAndUsers(eventEntity,userNow) == 0){
					eventEntity.setStatus((long) 3);
					eventRepository.save(eventEntity);
				}
			}
		}
		return comments;
	}

	@Override
	public void updateStatus(long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		UserEntity userNow = userRepository.findByUserName(userName);
		EventEntity eventEntity = eventRepository.findById(id);
		String userParent = "";
		if(eventEntity.getUsers().getParent() != null) {
			userParent = eventEntity.getUsers().getParent().getUserName();
		}
		String userAdviser = "";
		if(eventEntity.getUsers().getAdviser() != null) {
			userAdviser = eventEntity.getUsers().getAdviser().getUserName();
		}
		
		int checkTech = 0;
		if(userNow.getRoles().get(0).getCode().equals("002")) {
			List<UserEntity> userEntities = eventEntity.getClasses().getUsers();
			for (UserEntity user : userEntities) {
				if(user.getUserName().equals(userNow.getUserName())) {
					checkTech = 1; 
					break;
				}
			}
		}
		if(userName.equals(eventEntity.getUsers().getUserName()) || userName.equals(userParent) || userName.equals(userAdviser) || checkTech == 1) {
			List<CommentEntity> commentEntities = commentRepository.findAllByEventOrderById(eventEntity);
			for (CommentEntity comment : commentEntities) {
				RoleEntity roleEntity = userNow.getRoles().get(0);
				long status = 1;
				if(roleEntity.getCode().equals("001")) {
					comment.setStatus(status);
				}
				if(roleEntity.getCode().equals("002")) {
					comment.setStatusTeach(status);
				}
				if(roleEntity.getCode().equals("003")) {
					comment.setStatusParent(status);
				}
				if(roleEntity.getCode().equals("004")) {
					comment.setStatusAdviser(status);
				}
				commentRepository.save(comment);
			}
		};
	}

}
