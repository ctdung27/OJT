package ait.team.java.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ait.team.java.dto.RoleDTO;
import ait.team.java.entity.RoleEntity;

@Component
public class RoleConverter {

	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}

	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}

	public RoleEntity toEntity(RoleDTO dto, RoleEntity entity) {
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}

	public List<RoleDTO> toDTO(List<RoleEntity> roles) {
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		for (RoleEntity roleEntity : roles) {
			rolesDTO.add(toDTO(roleEntity));
		}
		return rolesDTO;
	}
}