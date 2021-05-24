package ait.team.java.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
	@Column
	private String content;
	@Column
	private Long status;
	@Column
	private Long statusTeach;
	@Column
	private Long statusParent;
	@Column
	private Long statusAdviser;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity users;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;

	@ManyToMany()
	@JoinTable(name = "ability_comment", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "ability_id"))
    private List<AbilityEntity> abilities;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity users) {
		this.users = users;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public List<AbilityEntity> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<AbilityEntity> abilities) {
		this.abilities = abilities;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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
}
