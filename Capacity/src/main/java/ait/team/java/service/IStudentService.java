package ait.team.java.service;

import java.util.List;
import java.util.Map;

import ait.team.java.api.output.StudentOutput;

public interface IStudentService {
	List<StudentOutput> findOneUser(String userCode);
	void deleteEvent(long[] ids);
	Map<String,String> getClasss();
	Map<String,String> getEventName();

	List<StudentOutput> searchClass(String studentCode, String classCode);
	List<StudentOutput> searchEvent(String studentCode, String eventName);
	List<StudentOutput> searchClassAndEvent(String studentCode,String classCode, String eventName);
	List<StudentOutput> searchClassAndStatus(String studentCode,String classCode,List<Long> s);
	List<StudentOutput> searchEventAndStatus(String studentCode,String eventName,List<Long> s);
	List<StudentOutput> search(String studentCode,String classCode, String eventName,List<Long> s);
	List<StudentOutput> searchByStatus(String studentCode, List<Long> s);

	List<StudentOutput> searchResult(String studentCode,String classCode, String eventName,List<Long> s);
	
	StudentOutput updateStatus(StudentOutput s);
	
	String getStudentCode();
	int getStatusComment(long id);
	
}
