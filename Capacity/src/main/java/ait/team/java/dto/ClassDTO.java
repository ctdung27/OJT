package ait.team.java.dto;

import java.util.List;

public class ClassDTO extends AbstractDTO {
	private String name;
	private String code;
	private Long status;
	private List<UserDTO> students;
	private UserDTO teacher;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<UserDTO> getStudents() {
		return students;
	}

	public void setStudents(List<UserDTO> students) {
		this.students = students;
	}

	public UserDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(UserDTO teacher) {
		this.teacher = teacher;
	}
}
