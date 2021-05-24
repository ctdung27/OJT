package ait.team.java.controller.output;

public class ChatOutput {
	private long id;
	private long eventId;
	private String userName;
	private String roleName;
	private String imageUser;
	private String content;
	private String created;
	private String method;
	
	public ChatOutput() {
		
	}
	
	public ChatOutput(String userName, String imageUser, String content, String created) {
		super();
		this.userName = userName;
		this.imageUser = imageUser;
		this.content = content;
		this.created = created;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImageUser() {
		return imageUser;
	}
	public void setImageUser(String imageUser) {
		this.imageUser = imageUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
