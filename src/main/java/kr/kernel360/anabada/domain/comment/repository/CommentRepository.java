package kr.kernel360.anabada.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.domain.trade.entity.Trade;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findCommentsByTradeOrderByIdDesc(Trade trade);
}
