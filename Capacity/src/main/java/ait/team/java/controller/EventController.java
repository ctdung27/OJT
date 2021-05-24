package ait.team.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {	 
	@GetMapping(value = "event")
    public String event(Model model,@RequestParam(value = "id", required = false) String id) {
	 	model.addAttribute("content","event");
	 	if(id != null) {
	 		model.addAttribute("id",id);
	 	}
        return "index";
        
    }
	 
}