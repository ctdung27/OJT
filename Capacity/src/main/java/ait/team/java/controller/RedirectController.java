package ait.team.java.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
	@RequestMapping(value = { "/redirect"}, method = RequestMethod.GET)
    public String redirect(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String role = "";
		if (cookies != null) {
	       	 for (Cookie cookie : cookies) {
	       	   if (cookie.getName().equals("role")) {
	       		   role = cookie.getValue();
	       	    }
	       	  }
		}
		if(role.equals("ROLE_STUDENT")) {
			return "redirect:/student";
		}else if(role.equals("ROLE_TEACHER")) {
			return "redirect:/studentList";
		}else if(role.equals("ROLE_PARENT")) {
			return "redirect:/studentList";
		}else if(role.equals("ROLE_ADVISER")) {
			return "redirect:/studentList";
		}
        return "redirect:/";
    }
}
