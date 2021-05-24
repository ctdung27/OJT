package ait.team.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column
	private String userName;
	@Column
	private String passWord;
	@Column
	private String fullName;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String code;
	@Column
	private String sex;
	@Column
	private String image;
	@Column
	private Long status;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles;

	@ManyToMany
	@JoinTable(name = "user_class", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
	private List<ClassEntity> classes;

	

	@OneToMany(mappedBy = "users")
	private List<EventEntity> events = new ArrayList<>();
	
	@OneToMany(mappedBy = "users")
	private List<CommentEntity> comments = new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private UserEntity parent;
	
	@OneToMany(mappedBy = "parent")
	private List<UserEntity> studentsParent = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "adviser_id")
	private UserEntity adviser;
	
	@OneToMany(mappedBy = "adviser")
	private List<UserEntity> studentsAdviser = new ArrayList<>();

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}



	public List<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public UserEntity getParent() {
		return parent;
	}

	public void setParent(UserEntity parent) {
		this.parent = parent;
	}

	public List<UserEntity> getStudentsParent() {
		return studentsParent;
	}

	public void setStudentsParent(List<UserEntity> studentsParent) {
		this.studentsParent = studentsParent;
	}

	public UserEntity getAdviser() {
		return adviser;
	}

	public void setAdviser(UserEntity adviser) {
		this.adviser = adviser;
	}

	public List<UserEntity> getStudentsAdviser() {
		return studentsAdviser;
	}

	public void setStudentsAdviser(List<UserEntity> studentsAdviser) {
		this.studentsAdviser = studentsAdviser;
	}

}