package ait.team.java.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.api.output.EventOutput;
import ait.team.java.api.output.TrackingOutput;
import ait.team.java.dto.UserDTO;
import ait.team.java.service.ITrackingService;

@RestController
@CrossOrigin
public class TrackingAPI {
	
	@Autowired
    private ITrackingService trackingService;
	
	 @GetMapping(value = "/api/usertracking")
	    public UserDTO getUserByID(@RequestParam(value = "code") String code) {
	        return trackingService.getUserByID(code);
	    }
	 @GetMapping(value = "/api/tracking")
	    public List<TrackingOutput> getAbilityByUserId(@RequestParam(value = "code") String code) {
	        return trackingService.getAbilityByUserId(code);
	    }
	 @GetMapping(value = "/api/events")
	    public List<EventOutput> findByUsersCodeAndAbilityCode(@RequestParam(value = "code") String code,@RequestParam(value = "ability") String abilityCode) {
	        return trackingService.findByUsersCodeAndAbilityCode(code,abilityCode);
	    }
}
