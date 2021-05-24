package ait.team.java.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ait.team.java.api.output.StudentOutput;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.RoleEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.CategoryRepository;
import ait.team.java.repository.ClassRepository;
import ait.team.java.repository.CommentRepository;
import ait.team.java.repository.EventRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IStudentService;

@Service
public class StudentService implements IStudentService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public StudentOutput utils(EventEntity event){
			StudentOutput sOutput = new StudentOutput();
			sOutput.setId(event.getId());
			sOutput.setStudentName(event.getUsers().getFullName());
			sOutput.setStudentCode(event.getUsers().getCode());
			sOutput.setStudentClass(event.getUsers().getClasses().stream().filter(s -> s.getStatus() == 1).map(s -> s.getName().toString()).collect(Collectors.joining()));
			sOutput.setStatus(event.getStatus());
			sOutput.setEventName(event.getCategory().getName());
			sOutput.setClasses(event.getClasses().getName());
			sOutput.setIcon(event.getCategory().getIcon());
	
		return sOutput;
	}

	@Override
	public List<StudentOutput> findOneUser(String userCode) {
		UserEntity users = userRepository.findByCode(userCode);
		List<EventEntity> events = eventRepository.findByUsers(users) ;
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}
	
	@Override
	@Transactional
	public void deleteEvent(long[] ids) {
		for (Long id : ids) {
				eventRepository.delete(id);
		}
	}
	
	@Override
	public Map<String,String> getClasss() {
		Map<String, String> results = new TreeMap<String, String>();
		classRepository.findAllByOrderByIdAsc().forEach(item -> results.put(item.getName(), item.getName()));
		Comparator<String> c = (s1, s2) -> Integer.parseInt(s1.split(" ")[1]) - Integer.parseInt(s2.split(" ")[1]);
		return results.keySet().stream().sorted(c).collect(Collectors.toMap(k -> k, k -> results.get(k), (k, v) -> v, LinkedHashMap::new));
	}
	
	@Override
	public Map<String,String> getEventName() {
		Map<String, String> results = new HashMap<>();
		categoryRepository.findAll().forEach(item ->results.put(item.getCode(), item.getName()));
		return results;
	}

	@Override
	public List<StudentOutput> searchClass(String studentCode, String classCode) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndClassesName(studentCode, classCode);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}
	@Override
	public List<StudentOutput> searchEvent(String studentCode, String eventName) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndCategoryCode(studentCode, eventName);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}

	@Override
	public List<StudentOutput> searchClassAndEvent(String studentCode, String classCode, String eventName) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndClassesNameAndCategoryCode(studentCode, classCode, eventName);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}

	@Override
	public List<StudentOutput> search(String studentCode, String classCode, String eventName, List<Long> s) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndClassesNameAndCategoryCodeAndStatusIn(studentCode, classCode, eventName,s);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}

	@Override
	public List<StudentOutput> searchClassAndStatus(String studentCode, String classCode, List<Long> s) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndClassesNameAndStatusIn(studentCode, classCode, s);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}

	@Override
	public List<StudentOutput> searchEventAndStatus(String studentCode, String eventName, List<Long> s) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndCategoryCodeAndStatusIn(studentCode, eventName, s);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}


	@Override
	public List<StudentOutput> searchByStatus(String studentCode, List<Long> s) {
		List<EventEntity> events = eventRepository.findByUsersCodeAndStatusIn(studentCode, s);
		List<StudentOutput> res = new ArrayList<StudentOutput>();
		for (EventEntity item: events) {
			res.add(utils(item));
		
		}
		return  res;
	}

	@Override
	public List<StudentOutput> searchResult(String studentCode, String classes, String eventName, List<Long> status) {
		UserEntity users = userRepository.findByCode(studentCode);
		String Classessss = users.getClasses().stream().filter(s -> s.getStatus() == 1).map(s -> s.getName().toString()).collect(Collectors.joining());
		String studentname = users.getFullName();
		
		List<StudentOutput> students = new ArrayList<>();
		if ((classes == null) && (eventName == null) &&  (status == null) || (classes.isEmpty()) && (eventName.isEmpty()) &&  (status == null)) {
			students = findOneUser(studentCode);
		}else if((eventName.isEmpty()) &&  (status == null)) {
			students = searchClass(studentCode, classes);
		}else if((classes.isEmpty()) && (status == null)) {
			students = searchEvent(studentCode, eventName);
		}else if((status == null)) {
			students = searchClassAndEvent(studentCode, classes, eventName);
		}else if((classes.isEmpty()) && (eventName.isEmpty())) {
			students = searchByStatus(studentCode, status);
		}else if(eventName.isEmpty()) {
			students = searchClassAndStatus(studentCode, classes, status);
		}else if(classes.isEmpty()) {
			students = searchEventAndStatus(studentCode, eventName, status);
		}else {
			students = search(studentCode, classes, eventName, status);
		}
		if(students.isEmpty()) {
			students.add(0, new StudentOutput(Classessss,studentname,studentCode));
		}
		return students;
	}

	@Override
	@Transactional
	public StudentOutput updateStatus(StudentOutput s) {
		EventEntity event = eventRepository.findOne(s.getId());
		event.setStatus(1L);
		return utils(eventRepository.save(event));
		
	}

	@Override
	public  String getStudentCode() {
		String studentCode ;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		UserEntity user = userRepository.findByUserName(userName);
		studentCode  = user.getCode();
		return studentCode;
	}

	@Override
	public int getStatusComment(long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		UserEntity user = userRepository.findByUserName(userName);
		EventEntity entity = eventRepository.findById(id);
		RoleEntity roleEntity = user.getRoles().get(0);
		if(roleEntity.getCode().equals("001")) {
			return commentRepository.countByEventAndStatus(entity,0);
		}
		if(roleEntity.getCode().equals("002")) {
			int check_teach = 0;
			for (UserEntity userClass : entity.getClasses().getUsers()) {
				if(userClass.getUserName().equals(userName)) {
					check_teach = 1;
				}
			}
			if(check_teach == 0) {
				return 0;
			}
			return commentRepository.countByEventAndStatusTeach(entity,0);
		}
		if(roleEntity.getCode().equals("003")) {
			return commentRepository.countByEventAndStatusParent(entity,0);
		}
		if(roleEntity.getCode().equals("004")) {
			return commentRepository.countByEventAndStatusAdviser(entity,0);
		}
		return -1;
		
	}


	

	

}
