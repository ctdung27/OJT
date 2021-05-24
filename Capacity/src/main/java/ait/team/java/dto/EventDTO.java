package ait.team.java.dto;

import java.util.List;

public class EventDTO extends AbstractDTO {
	private Long status;
	private String community;
	private String abilityAction;
	private String abilityProve;
	private String abilityDevelop;
	private String abilityThink;
	private CategoryDTO category;
	private UserDTO user;
	private ClassDTO classes;
	
	private List<CommentDTO> comments;

	private long categoryId;
	
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

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategorId(long categoryId) {
		this.categoryId = categoryId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public ClassDTO getClasses() {
		return classes;
	}

	public void setClasses(ClassDTO classes) {
		this.classes = classes;
	}
}
