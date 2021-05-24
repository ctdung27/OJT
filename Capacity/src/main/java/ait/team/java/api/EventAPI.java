package ait.team.java.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.EventDTO;
import ait.team.java.service.IEventService;


@RestController
@CrossOrigin
@RequestMapping("/api/")
public class EventAPI {
	@Autowired
	private IEventService eventService;
	
	@GetMapping(value = "event/{id}")
	public EventDTO showEvent(@PathVariable("id") long id) {
		EventDTO dto = eventService.findOne(id);
		return dto;
		
	}
	
	@GetMapping(value = "event/category")
	public List<CategoryDTO> showCategory() {
		List<CategoryDTO> dto = eventService.findCategoryAll();
		return dto;
	}
	
	@GetMapping(value = "event/abilityEvent/{id}")
    public List<AbilityDTO> getAbility(@PathVariable("id") long id) {
        return eventService.findAllByOrderByIdAsc(id);
    }
	
	@PostMapping(value = "event")
	public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
		long status = (long) 3;
		eventDTO.setStatus(status);
		return eventService.save(eventDTO);
	}
	
	@PutMapping(value = "event")
	public EventDTO updateEvent(@RequestBody EventDTO eventDTO) {
		return eventService.save(eventDTO);
	}
	
	@DeleteMapping(value = "event")
	public boolean deleteEvent(@RequestBody long[] ids) {
		return eventService.delete(ids);
	}
	
}
