package ait.team.java.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ait.team.java.entity.AbilityEntity;
import ait.team.java.entity.CommentEntity;
import ait.team.java.entity.EventEntity;
import ait.team.java.entity.UserEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	CommentEntity findById(Long id);
	List<CommentEntity> findAllByEventOrderById(EventEntity event);
	List<CommentEntity> findAllByEventOrderById(EventEntity event, Pageable pageable);
	int countByEvent(EventEntity event);

	List<CommentEntity> findByAbilities(List<AbilityEntity> abilities);
	int countByEventAndStatus(EventEntity entity, long i);
	int countByEventAndStatusTeach(EventEntity entity, long i);
	int countByEventAndStatusParent(EventEntity entity, long i);
	int countByEventAndStatusAdviser(EventEntity entity, long i);
	int countByEventAndUsers(EventEntity eventEntity, UserEntity userNow);

}
