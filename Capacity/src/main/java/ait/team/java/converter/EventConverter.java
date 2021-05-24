package ait.team.java.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ait.team.java.dto.EventDTO;
import ait.team.java.entity.EventEntity;

@Component
public class EventConverter {
	@Autowired
	UserConverter userConverter;
	public EventEntity toEntity(EventDTO dto) {
		EventEntity entity = new EventEntity();
		entity.setAbilityAction(dto.getAbilityAction());
		entity.setAbilityDevelop(dto.getAbilityDevelop());
		entity.setAbilityProve(dto.getAbilityProve());
		entity.setAbilityThink(dto.getAbilityThink());
		entity.setCommunity(dto.getCommunity());
		entity.setStatus(dto.getStatus());
		return entity;
	}
	
	public EventDTO toDTO(EventEntity entity) {
		EventDTO dto = new EventDTO();
		dto.setUser(userConverter.toDTO(entity.getUsers()));
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setAbilityAction(entity.getAbilityAction());
		dto.setAbilityDevelop(entity.getAbilityDevelop());
		dto.setAbilityProve(entity.getAbilityProve());
		dto.setAbilityThink(entity.getAbilityThink());
		dto.setCommunity(entity.getCommunity());
		dto.setStatus(entity.getStatus());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public EventEntity toEntity(EventDTO dto, EventEntity entity) {
		entity.setAbilityAction(dto.getAbilityAction());
		entity.setAbilityDevelop(dto.getAbilityDevelop());
		entity.setAbilityProve(dto.getAbilityProve());
		entity.setAbilityThink(dto.getAbilityThink());
		entity.setCommunity(dto.getCommunity());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}
