package ait.team.java.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.EventDTO;

public interface IEventService {
	EventDTO save(EventDTO eventDTO);
	EventDTO findOne(long id);
	List<CategoryDTO> findCategoryAll();
	boolean delete(long[] ids);
	List<EventDTO> findAll();
	List<EventDTO> findAll(Pageable pageable);
	List<AbilityDTO> findAllByOrderByIdAsc(long id);
}
