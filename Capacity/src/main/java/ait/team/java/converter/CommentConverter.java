package ait.team.java.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CommentDTO;
import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.CommentEntity;

@Component
public class CommentConverter {
	@Autowired
	UserConverter userConverter;
	@Autowired
	AbilityConverter abilityConverter;
	@Autowired
	EventConverter eventConverter;
	public CommentEntity toEntity(CommentDTO dto) {
		CommentEntity entity = new CommentEntity();
		entity.setContent(dto.getContent());
		entity.setStatus(dto.getStatus());
		entity.setStatusAdviser(dto.getStatusAdviser());
		entity.setStatusParent(dto.getStatusParent());
		entity.setStatusTeach(dto.getStatusTeach());
		return entity;
	}
	
	public CommentDTO toDTO(CommentEntity entity) {
		CommentDTO dto = new CommentDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		if(entity.getAbilities() != null) {
			List<AbilityDTO> abilityDTO = new ArrayList<>();
			for (AbilityEntity ability : entity.getAbilities()) {
				abilityDTO.add(abilityConverter.toDTO(ability));
			}
			dto.setAbilities(abilityDTO);
		}
		
		if(entity.getEvent() != null) {
			dto.setEvent(eventConverter.toDTO(entity.getEvent()));
		}
		
		dto.setContent(entity.getContent());
		dto.setStatus(entity.getStatus());
		dto.setUser(userConverter.toDTO(entity.getUsers()));
		dto.setStatusAdviser(entity.getStatusAdviser());
		dto.setStatusParent(entity.getStatusParent());
		dto.setStatusTeach(entity.getStatusTeach());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public CommentEntity toEntity(CommentDTO dto, CommentEntity entity) {
		entity.setContent(dto.getContent());
		entity.setStatus(dto.getStatus());
		entity.setStatusAdviser(dto.getStatusAdviser());
		entity.setStatusParent(dto.getStatusParent());
		entity.setStatusTeach(dto.getStatusTeach());
		return entity;
	}
}
