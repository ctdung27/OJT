package ait.team.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class TrackingController {
	
	@RequestMapping(value = { "/tracking", }, method = RequestMethod.GET)
    public String index(Model model) {
	 	model.addAttribute("content","tracking");
        return "index";  
    }
}
