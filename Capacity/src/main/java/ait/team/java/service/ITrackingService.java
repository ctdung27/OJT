package ait.team.java.service;

import java.util.List;

import ait.team.java.api.output.EventOutput;
import ait.team.java.api.output.TrackingOutput;
import ait.team.java.dto.UserDTO;


public interface ITrackingService {
	UserDTO getUserByID(String userCode);
	
	List<TrackingOutput> getAbilityByUserId(String userCode);
	
	List<EventOutput> findByUsersCodeAndAbilityCode(String userCode, String abilityCode );
}
