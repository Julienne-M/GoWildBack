package fr.wcs.gowild.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.wcs.gowild.exception.RessourceNotFoundException;
import fr.wcs.gowild.model.Comment;
import fr.wcs.gowild.repository.CommentRepository;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/comments")
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@GetMapping("/comments/{id}")
	public Comment getCommentById(@PathVariable(value = "id") Long commentId) {
		return commentRepository.findById(commentId).orElseThrow(() -> new RessourceNotFoundException("Comment","id", commentId ));
	}

	@PostMapping("/comments")
	public Comment createComment(@Valid @RequestBody Comment comment) {
		return commentRepository.save(comment);
	}

	@PutMapping("/comments/{id}")
	public Comment updateComment(@PathVariable(value = "id") Long commentId, @RequestBody Comment commentDetails) {
		final Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RessourceNotFoundException("Comment", "id", commentId));
		comment.setContent(commentDetails.getContent());
		comment.setLiky(commentDetails.getLiky());

		final Comment updateComment = commentRepository.save(comment);
		return updateComment;
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long commentId) {
		final Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RessourceNotFoundException("Comment", "id", commentId));
		commentRepository.delete(comment);
		return ResponseEntity.ok().build();
	}
}
