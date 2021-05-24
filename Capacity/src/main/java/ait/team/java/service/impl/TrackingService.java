package ait.team.java.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ait.team.java.api.output.EventOutput;
import ait.team.java.api.output.TrackingOutput;
import ait.team.java.converter.UserConverter;
import ait.team.java.dto.UserDTO;
import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.CommentEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.AbilityRepository;
import ait.team.java.repository.CommentRepository;
import ait.team.java.repository.EventRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.ITrackingService;
@Component
public class TrackingService implements ITrackingService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private AbilityRepository abilityRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public UserDTO getUserByID(String userCode) {
		UserEntity user = userRepository.findByCode(userCode);		
		return userConverter.toDTO(user); 
	}


	@Override
	public List<TrackingOutput> getAbilityByUserId(String userCode) {
		List<EventEntity> list = new ArrayList<>();
		UserEntity user = new UserEntity();
		user = userRepository.findByCode(userCode);
		list = eventRepository.findByUsers(user);
	
		List<TrackingOutput> listtracking = new ArrayList<>();
		List<CommentEntity> listcmt= new ArrayList<>();
		List<AbilityEntity> abilities = new ArrayList<>();
		for (EventEntity eventEntity : list) {
			listcmt =eventEntity.getComments();
			for (CommentEntity commentEntity : listcmt) {
				abilities = commentEntity.getAbilities();
				for (AbilityEntity abilityEntity : abilities) {
					TrackingOutput trdata = new TrackingOutput();
					trdata.setClass_name(eventEntity.getClasses().getName());
					trdata.setAbility(abilityEntity.getName());
					trdata.setNumberAppear(1);
					listtracking.add(trdata);
				}
			}
		}
		
		return listtracking;
	}
 
	@Override
	public List<EventOutput> findByUsersCodeAndAbilityCode(String userCode, String abilityCode) {
		List<CommentEntity> comments = new ArrayList<>();
		List<AbilityEntity> abilities = new  ArrayList<>();
		List<EventOutput> events = new ArrayList<>();
		
		AbilityEntity ability = new AbilityEntity();
		ability = abilityRepository.findById(Long.parseLong(abilityCode));
		abilities.add(ability);
		
		comments= commentRepository.findByAbilities(abilities);
		for(CommentEntity comment:comments) {
			if(comment.getEvent().getUsers().getCode().equals(userCode)){
				EventOutput event = new EventOutput();
				event.setId(comment.getEvent().getId());
				event.setName(comment.getEvent().getCategory().getName());
				events.add(event);			
			}
		}
		
		return events;
	}
}
