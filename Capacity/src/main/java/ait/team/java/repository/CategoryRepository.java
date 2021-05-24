package ait.team.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findById(long id);
	CategoryEntity findByCode(String code);
}
