package kr.kernel360.anabada.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.comment.dto.CreateCommentRequest;
import kr.kernel360.anabada.domain.comment.dto.FindCommentDto;
import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.domain.comment.repository.CommentRepository;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final TradeRepository tradeRepository;

	@Transactional
	public Long create(CreateCommentRequest createCommentRequest, Long tradeId) {
		Member member = memberRepository.findById(createCommentRequest.getMemberId())
			.orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다"));
		Trade trade = tradeRepository.findById(tradeId)
			.orElseThrow(()-> new IllegalArgumentException("trade가 존재하지 않습니다"));
		Comment savedComment = commentRepository.save(CreateCommentRequest.toEntity(createCommentRequest, member, trade));
		return savedComment.getId();
	}

	// public FindCommentDto findAll(Long tradeId) {
	// 	List<Comment> comments = commentRepository.findAll();
	//
	// }
}
