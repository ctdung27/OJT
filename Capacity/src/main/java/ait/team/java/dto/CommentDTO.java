package ait.team.java.dto;

import java.util.List;

public class CommentDTO extends AbstractDTO{
	private String content;
	private EventDTO event;
	private List<AbilityDTO> abilities;
	private String abilityName;
	private Long status;
	private Long user_id;
	private Long event_id;
	private UserDTO user;
	private Long statusTeach;
	private Long statusParent;
	private Long statusAdviser;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<AbilityDTO> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<AbilityDTO> abilities) {
		this.abilities = abilities;
	}
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Long getStatusTeach() {
		return statusTeach;
	}

	public void setStatusTeach(Long statusTeach) {
		this.statusTeach = statusTeach;
	}

	public Long getStatusParent() {
		return statusParent;
	}

	public void setStatusParent(Long statusParent) {
		this.statusParent = statusParent;
	}

	public Long getStatusAdviser() {
		return statusAdviser;
	}

	public void setStatusAdviser(Long statusAdviser) {
		this.statusAdviser = statusAdviser;
	}

	public String getAbilityName() {
		return abilityName;
	}

	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}



}
