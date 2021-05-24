package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.EventEntity;
import ait.team.java.entity.UserEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long>{
	List<EventEntity>  findByUsers(UserEntity user) ;
	public EventEntity findById(Long id);
	
	
	List<EventEntity> findByUsersCodeAndClassesNameAndCategoryCodeAndStatusIn(String codeStudent, String codeClasses, String codeEventName, List<Long> s);
	List<EventEntity> findByUsersCodeAndClassesName(String codeStudent, String codeClasses);
	List<EventEntity> findByUsersCodeAndCategoryCode(String codeStudent, String codeEventName);
	List<EventEntity> findByUsersCodeAndStatusIn(String codeStudent, List<Long> s);
	List<EventEntity> findByUsersCodeAndClassesNameAndCategoryCode(String codeStudent, String codeClasses, String codeEventName);
	List<EventEntity> findByUsersCodeAndClassesNameAndStatusIn(String codeStudent, String codeClasses, List<Long> s);
	List<EventEntity> findByUsersCodeAndCategoryCodeAndStatusIn(String codeStudent, String codeClasses, List<Long> s);
	
}
