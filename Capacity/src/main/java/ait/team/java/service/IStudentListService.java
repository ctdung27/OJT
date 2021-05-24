package ait.team.java.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.ClassDTO;
import ait.team.java.dto.UserDTO;

public interface IStudentListService {
	List<UserDTO> findAll(Pageable pageable,String studentName,String classId,String eventId,String hashTag);
	List<AbilityDTO> findAllByOrderByIdAsc();
	List<ClassDTO> findClass();
	List<CategoryDTO> findEvent();
	List<UserDTO> findAll();
	int totalItem(String studentName,String classId,String eventId,String hashTag);
}
