package fr.wcs.gowild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.wcs.gowild.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
