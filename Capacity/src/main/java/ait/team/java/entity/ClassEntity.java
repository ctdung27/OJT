package ait.team.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class ClassEntity extends BaseEntity{
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private Long status;
	
	@ManyToMany(mappedBy = "classes")
    private List<UserEntity> users;
	@OneToMany(mappedBy = "classes")
	private List<EventEntity> events = new ArrayList<>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	public List<EventEntity> getEvents() {
		return events;
	}
	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}
	
}
