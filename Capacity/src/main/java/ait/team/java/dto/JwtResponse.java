package ait.team.java.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private final String jwtToken;

	private final Collection<? extends GrantedAuthority> roles;
	private final String userName;
	public JwtResponse(String jwtToken, Collection<? extends GrantedAuthority> roles, String userName) {
		this.jwtToken = jwtToken;
		this.roles = roles;
		this.userName = userName;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public String getUserName() {
		return userName;
	}

}
