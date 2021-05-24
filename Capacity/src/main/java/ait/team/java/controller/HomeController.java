package ait.team.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	 @RequestMapping(value = { "/", }, method = RequestMethod.GET)
	    public String index(Model model) {
	        return "login";
	       
	    }


	 @RequestMapping(value = { "/studentList", }, method = RequestMethod.GET)
	    public String studentList(Model model) {
		 	model.addAttribute("content","StudentListTable");
	        return "StudentList";
	        
	    }
}
