package ait.team.java.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ait.team.java.dto.UserDTO;
import ait.team.java.entity.UserEntity;

@Component
public class UserConverter {

	@Autowired
	RoleConverter roleConverter;
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setUserName(dto.getUserName());
		entity.setPassWord(dto.getPassWord());
		entity.setFullName(dto.getFullName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setSex(dto.getSex());
		entity.setStatus(dto.getStatus());
		entity.setImage(dto.getImage());
		entity.setCode(dto.getCode());
		return entity;
	}
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setUserName(entity.getUserName());
		dto.setPassWord(entity.getPassWord());
		dto.setFullName(entity.getFullName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setSex(entity.getSex());
		dto.setStatus(entity.getStatus());
		dto.setImage(entity.getImage());
		dto.setCode(entity.getCode());
		dto.setRoles(roleConverter.toDTO(entity.getRoles()));
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public UserEntity toEntity(UserDTO dto, UserEntity entity) {
		entity.setUserName(dto.getUserName());
		entity.setPassWord(dto.getPassWord());
		entity.setFullName(dto.getFullName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setSex(dto.getSex());
		entity.setStatus(dto.getStatus());
		entity.setImage(dto.getImage());
		entity.setCode(dto.getCode());
		return entity;
	}
}
