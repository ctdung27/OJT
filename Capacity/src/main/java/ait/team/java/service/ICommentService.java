package ait.team.java.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ait.team.java.dto.CommentDTO;

public interface ICommentService {
	CommentDTO save(CommentDTO CommentDTO);
	List<CommentDTO> findAll(Long event_id, Pageable pageable);
	List<CommentDTO> findAll(Long event_id);
	int totalItem(Long event_id);
	List<CommentDTO> delete(long[] ids);
	void updateStatus(long id);
}
