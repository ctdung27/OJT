package ait.team.java.converter;

import org.springframework.stereotype.Component;

import ait.team.java.dto.ClassDTO;
import ait.team.java.entity.ClassEntity;

@Component
public class ClassConverter {
	public ClassEntity toEntity(ClassDTO dto) {
		ClassEntity entity = new ClassEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		return entity;
	}
	
	public ClassDTO toDTO(ClassEntity entity) {
		ClassDTO dto = new ClassDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public ClassEntity toEntity(ClassDTO dto, ClassEntity entity) {
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}
