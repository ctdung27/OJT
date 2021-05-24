package ait.team.java.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long>{
	
	public java.util.List<ClassEntity> findAllByOrderByIdAsc();

}
