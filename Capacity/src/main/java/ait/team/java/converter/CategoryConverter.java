package ait.team.java.converter;

import org.springframework.stereotype.Component;

import ait.team.java.dto.CategoryDTO;
import ait.team.java.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		entity.setIcon(dto.getIcon());
		return entity;
	}
	
	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		dto.setIcon(entity.getIcon());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity) {
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		entity.setIcon(dto.getIcon());
		return entity;
	}
}
