package ait.team.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.AbilityEntity;

public interface AbilityRepository extends JpaRepository<AbilityEntity, Long>{

	List<AbilityEntity> findAllByOrderByIdAsc();

	AbilityEntity findById(Long abilityCode);
	


	AbilityEntity findOneByCode(String code);

	AbilityEntity findByCode(String part);

	AbilityEntity findByName(String part);


}
