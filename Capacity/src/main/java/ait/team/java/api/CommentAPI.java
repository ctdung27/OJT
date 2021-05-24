package ait.team.java.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.api.output.CommentOutput;
import ait.team.java.dto.CommentDTO;
import ait.team.java.service.impl.CommentService;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentAPI {
	@Autowired
	CommentService commentService;
	
	@PostMapping(value = "")
	public ResponseEntity<?> createComment(@RequestBody CommentDTO dto) {
		return ResponseEntity.ok(commentService.save(dto));
	}
	@GetMapping(value = "")
	public CommentOutput readComment(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "event_id", required = false) Long event_id) {
       CommentOutput result = new CommentOutput();
       if (page != null && limit != null) {
           result.setPage(page);
           Pageable pageable = new PageRequest(page - 1, limit);
           result.setListResult(commentService.findAll(event_id, pageable));
           result.setTotalPage((int) Math.ceil((double) (commentService.totalItem(event_id)) / limit));
       } else {
           result.setListResult(commentService.findAll(event_id));
       }
       return result;
   }
	@PutMapping(value = "/{id}")
	public CommentDTO updateNew(@RequestBody CommentDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return commentService.save(model);
	}

	@DeleteMapping(value = "")
	public List<CommentDTO> deleteNew(@RequestBody long[] ids) {
		return commentService.delete(ids);
	}
	
	@PutMapping(value = "status/{id}")
	public void updateStatus(@PathVariable("id") long id) {
		commentService.updateStatus(id);
	}
}
