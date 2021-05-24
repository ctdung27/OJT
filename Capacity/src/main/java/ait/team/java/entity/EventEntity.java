package ait.team.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class EventEntity extends BaseEntity {
	@Column
	private Long status;
	@Column
	private String community;
	@Column
	private String abilityAction;
	@Column
	private String abilityProve;
	@Column
	private String abilityDevelop;
	@Column
	private String abilityThink;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity users;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@OneToMany(mappedBy = "event")
	private List<CommentEntity> comments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity classes;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getAbilityAction() {
		return abilityAction;
	}

	public void setAbilityAction(String abilityAction) {
		this.abilityAction = abilityAction;
	}

	public String getAbilityProve() {
		return abilityProve;
	}

	public void setAbilityProve(String abilityProve) {
		this.abilityProve = abilityProve;
	}

	public String getAbilityDevelop() {
		return abilityDevelop;
	}

	public void setAbilityDevelop(String abilityDevelop) {
		this.abilityDevelop = abilityDevelop;
	}

	public String getAbilityThink() {
		return abilityThink;
	}

	public void setAbilityThink(String abilityThink) {
		this.abilityThink = abilityThink;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity user) {
		this.users = user;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public ClassEntity getClasses() {
		return classes;
	}

	public void setClasses(ClassEntity classes) {
		this.classes = classes;
	}
}
