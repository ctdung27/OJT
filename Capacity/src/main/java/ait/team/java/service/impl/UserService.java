package ait.team.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ait.team.java.converter.ClassConverter;
import ait.team.java.converter.EventConverter;
import ait.team.java.converter.UserConverter;
import ait.team.java.dto.ClassDTO;
import ait.team.java.dto.EventDTO;
import ait.team.java.dto.RoleDTO;
import ait.team.java.dto.UserDTO;
import ait.team.java.entity.ClassEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.RoleEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.RoleRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EventConverter eventConverter;
	@Autowired
	private ClassConverter classConverter;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserEntity user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<RoleEntity> roles = user.getRoles();
		for (RoleEntity role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(),
				grantedAuthorities);

	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		if(userRepository.findByUserName(userDTO.getUserName()) == null) {
			UserEntity entity = new UserEntity();
			List<RoleEntity> roleEntity = new ArrayList<>();
			UserDTO dto = new UserDTO();
			for (RoleDTO role : userDTO.getRoles()) {
				roleEntity.add(roleRepository.findByCode(role.getCode()));
			}
			entity = userConverter.toEntity(userDTO);
			entity.setPassWord(bcryptEncoder.encode(userDTO.getPassWord()));
			entity.setRoles(roleEntity);
			entity = userRepository.save(entity);
			dto = userConverter.toDTO(entity);
			return dto;
		}
		return null;
	}
	
	public UserDTO findByUserName(String userName) {
		UserEntity entity = userRepository.findByUserName(userName);
		UserDTO dto = userConverter.toDTO(entity);
		List<EventDTO> events = new ArrayList<>();
		for (EventEntity event : entity.getEvents()) {
			EventDTO eventDTO = eventConverter.toDTO(event);
			events.add(eventDTO);
		}
		List<ClassDTO> classes = new ArrayList<>();
		for (ClassEntity classEntity : entity.getClasses()) {
			ClassDTO classDTO = classConverter.toDTO(classEntity);
			classes.add(classDTO);
		}
		dto.setClasses(classes);
		dto.setEvents(events);
		return dto;
	}
	


}
