package ait.team.java.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ait.team.java.entity.CategoryEntity;
import ait.team.java.entity.ClassEntity;
import ait.team.java.entity.RoleEntity;
import ait.team.java.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	public java.util.List<UserEntity> findAllByOrderByCodeAsc();
	UserEntity findByUserName(String userName);
	UserEntity findByCode(String userCode);
	
	@Query("SELECT DISTINCT u FROM UserEntity u INNER JOIN u.classes c INNER JOIN u.roles r LEFT JOIN u.events e"
            + " LEFT JOIN e.comments cmt LEFT JOIN cmt.abilities ability  WHERE"
            + " r IN (:roles) and (:parent is null or u.parent = :parent) and (:adviser is null or u.adviser = :adviser)"
            + " and ((:classes) is null or (c IN (:classes) and c.status = 1)) and ((:userName) is null or lower(u.fullName) LIKE (:userName))"
            + " and (:classSearch is null or (c.name = :classSearch and c.status = 1)) and (:categories is null or e.category = :categories)"
            + " and (:abilities is null or ability.name = :abilities)"
            + " ORDER BY u.code")    
    List<UserEntity> findByUserContaining(@Param("roles") List<RoleEntity> roles, @Param("parent") UserEntity parent,
            @Param("adviser") UserEntity adviser, @Param("classes") List<ClassEntity> classes,
            @Param("userName") String userName, @Param("classSearch") String classSearch, 
            @Param("categories") CategoryEntity categories, @Param("abilities") String abilities, Pageable pageable);
    
    
    @Query("SELECT count(DISTINCT u) FROM UserEntity u INNER JOIN u.classes c INNER JOIN u.roles r LEFT JOIN u.events e"
            + " LEFT JOIN e.comments cmt LEFT JOIN cmt.abilities ability  WHERE"
            + " r IN (:roles) and (:parent is null or u.parent = :parent) and (:adviser is null or u.adviser = :adviser)"
            + " and ((:classes) is null or (c IN (:classes) and c.status = 1)) and ((:userName) is null or lower(u.fullName) LIKE (:userName))"
            + " and (:classSearch is null or (c.name = :classSearch and c.status = 1)) and (:categories is null or e.category = :categories)"
            + " and (:abilities is null or ability.name = :abilities)")    
    int countUser(@Param("roles") List<RoleEntity> roles, @Param("parent") UserEntity parent,
            @Param("adviser") UserEntity adviser,@Param("classes") List<ClassEntity> classes,
            @Param("userName") String userName, @Param("classSearch") String classSearch, 
            @Param("categories") CategoryEntity categories, @Param("abilities") String abilities);

}
