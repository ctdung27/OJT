package ait.team.java.api;

import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.config.JwtTokenUtil;
import ait.team.java.dto.JwtRequest;
import ait.team.java.dto.UserDTO;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IUserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginAPI {
	@Autowired
	private IUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.save(dto));
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response) throws Exception {
		
		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassWord());

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);
		Collection<? extends GrantedAuthority> roles =  userDetails.getAuthorities();
		String userName = userDetails.getUsername();
		UserEntity userEtity = userRepository.findByUserName(userName);
		
		createCookieToken("token", token, response);
		createCookie("userName", userName, response);
		createCookie("imageUser", userEtity.getImage(), response);
		for (GrantedAuthority grantedAuthority : roles) {
			String role = grantedAuthority.getAuthority();
			createCookie("role",role, response);
			if(role.equals("ROLE_STUDENT")) {
				createCookie("studentCode",userEtity.getCode(), response);
			}
		}
		return ResponseEntity.ok("true");
	}
	
	@GetMapping(value = "/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        deleteCookie("token","", response);
	        deleteCookie("userName","", response);
	        deleteCookie("role","", response);
	        deleteCookie("studentCode", "", response);
	        deleteCookie("imageUser", "", response);
	    }
		return ResponseEntity.ok("true");
	}
	
	private void authenticate(String userName, String passWord) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, passWord));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	private void createCookie(String name, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60); // expires in 1 hour
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
	}
	private void createCookieToken(String name, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60); // expires in 1 hour
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
	}
	private void deleteCookie(String name, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(0); 
        cookie.setPath("/");
        response.addCookie(cookie);
	}
}
