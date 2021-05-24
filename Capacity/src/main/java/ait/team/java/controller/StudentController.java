package ait.team.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ait.team.java.api.output.StudentOutput;
import ait.team.java.service.impl.StudentService;
import ait.team.java.utils.MessageUtil;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = { "/student", }, method = RequestMethod.GET)
    public ModelAndView index(@RequestParam (value= "studentCode", required = false ) String studentCode,
    						  @RequestParam (value= "classes", required = false) String classes,
    						  @RequestParam (value= "eventName" ,required = false) String eventName,
    						  @RequestParam (value= "status" , required = false) List<Long> status,
    						  @ModelAttribute("model") StudentOutput model,
    						  HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("student");
		List<StudentOutput> students = new ArrayList<>();
		if (studentCode == null) {
			students = studentService.searchResult(studentService.getStudentCode(), classes, eventName, status); 
		}else {
			students = studentService.searchResult(studentCode, classes, eventName, status);
		}
		mav.addObject("student", students);
		
		mav.addObject("classs", studentService.getClasss());
		mav.addObject("eventCategories", studentService.getEventName());
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
        return mav;
        
    }

}
