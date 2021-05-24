package ait.team.java.converter;

import org.springframework.stereotype.Component;

import ait.team.java.dto.AbilityDTO;
import ait.team.java.entity.AbilityEntity;

@Component
public class AbilityConverter {
	public AbilityEntity toEntity(AbilityDTO dto) {
		AbilityEntity entity = new AbilityEntity();
		entity.setCode(dto.getCode());
		entity.setCapacity(dto.getCapacity());
		entity.setName(dto.getName());
		entity.setColor(dto.getColor());
		return entity;
	}
	
	public AbilityDTO toDTO(AbilityEntity entity) {
		AbilityDTO dto = new AbilityDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		dto.setCapacity(entity.getCapacity());
		dto.setColor(entity.getColor());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public AbilityEntity toEntity(AbilityDTO dto, AbilityEntity entity) {
		entity.setCode(dto.getCode());
		entity.setCapacity(dto.getCapacity());
		entity.setName(dto.getName());
		entity.setColor(dto.getColor());
		return entity;
	}
}
