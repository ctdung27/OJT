package ait.team.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findByCode(String code);
}
