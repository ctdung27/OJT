package ait.team.java.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.api.output.StudentListOutput;
import ait.team.java.dto.AbilityDTO;
import ait.team.java.dto.CategoryDTO;
import ait.team.java.dto.ClassDTO;
import ait.team.java.dto.UserDTO;
import ait.team.java.service.IStudentListService;

@RestController
@CrossOrigin
public class StudentListAPI {
	
	@Autowired
    private IStudentListService studentListService;

	
    @GetMapping(value = "api/StudentList")
    public List<UserDTO> getEvent() {
        return studentListService.findAll();
    }
    
    @GetMapping(value = "api/Ability")
    public List<AbilityDTO> getAbility() {
        return studentListService.findAllByOrderByIdAsc();
    }
    
    @GetMapping(value = "api/Class")
    public List<ClassDTO> geClass() {
        return studentListService.findClass();
    }
    
    @GetMapping(value = "api/Event")
    public List<CategoryDTO> geCategory() {
        return studentListService.findEvent();
    }
    
    @GetMapping(value = "api/listPage")
    public StudentListOutput showNew(@RequestParam(value = "pageNo", required = false) Integer pageNo
    		, @RequestParam(value = "studentName", required = false) String studentName
    		, @RequestParam(value = "className", required = false) String classId
    		, @RequestParam(value = "eventName", required = false) String eventId
    		, @RequestParam(value = "hashTag", required = false) String hashTag) {
    	StudentListOutput result = new StudentListOutput();
    	int pageSize = 10;
		if (pageNo != null) {
			result.setPage(pageNo);
			Pageable pageable = new PageRequest(pageNo - 1, pageSize);
			result.setListResult(studentListService.findAll(pageable, studentName, classId, eventId, hashTag));
            result.setTotalPage((int) Math.ceil((double) (studentListService.totalItem(studentName, classId, eventId, hashTag)) / pageSize));
		} else {
			result.setListResult(studentListService.findAll());
		}
		return result;
		
	}

}
