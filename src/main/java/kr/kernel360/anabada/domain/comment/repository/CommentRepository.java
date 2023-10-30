package kr.kernel360.anabada.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
