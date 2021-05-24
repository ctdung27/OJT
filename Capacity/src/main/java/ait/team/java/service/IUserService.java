package ait.team.java.service;

import org.springframework.security.core.userdetails.UserDetails;

import ait.team.java.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);
	UserDetails loadUserByUsername(String userName);
	UserDTO findByUserName(String userName);
}
