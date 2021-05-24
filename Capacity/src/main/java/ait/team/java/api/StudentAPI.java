package ait.team.java.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.api.output.StudentOutput;
import ait.team.java.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentAPI {
	@Autowired
	private IStudentService studentService;
	
	@GetMapping()
	public List<StudentOutput> studentDetail(@RequestParam(value = "studentCode") String code) {
		return studentService.findOneUser(code);
		
	}
	
	@GetMapping("/statusComment/{id}")
	public int getStatusComment(@PathVariable("id") long id) {
		return studentService.getStatusComment(id);
		
	}
	
	@DeleteMapping()
	 public ResponseEntity<String> deleteEvent(@RequestBody long[] ids) {
		studentService.deleteEvent(ids);
		return ResponseEntity.ok("success");
		
	}
	@GetMapping(value ="/search")
	public List<StudentOutput> search(@RequestParam (value= "studentCode", required = false ) String studentCode,
									  @RequestParam (value= "classes", required = false) String classes,
									  @RequestParam (value= "eventName" ,required = false) String eventName,
									  @RequestParam (value= "status" , required = false) List<Long> status) {
		return studentService.searchResult(studentCode, classes, eventName, status);
		
	}
	@PutMapping
    public ResponseEntity<StudentOutput> updateEvent(@RequestBody StudentOutput dto) {
		
        return ResponseEntity.ok(studentService.updateStatus(dto));
    }
}
