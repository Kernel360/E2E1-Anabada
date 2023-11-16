package kr.kernel360.anabada.domain.comment.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.comment.dto.CreateCommentRequest;
import kr.kernel360.anabada.domain.comment.dto.FindCommentDto;
import kr.kernel360.anabada.domain.comment.dto.FindAllCommentResponse;
import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.domain.comment.repository.CommentRepository;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import kr.kernel360.anabada.global.error.code.CommentErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
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
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member findMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(CommentErrorCode.NOT_FOUND_MEMBER));
		Trade findTrade = findTrade(tradeId);
		Comment savedComment = commentRepository.save(CreateCommentRequest.toEntity(createCommentRequest, findMember, findTrade));

		return savedComment.getId();
	}

	public FindAllCommentResponse findAll(Long tradeId) {
		Trade findTrade = findTrade(tradeId);
		List<Comment> findComments = commentRepository.findCommentsByTradeOrderByIdDesc(findTrade);

		return FindAllCommentResponse.of(findComments.stream()
			.map(FindCommentDto::of)
			.toList());
	}

	private Trade findTrade(Long tradeId) {
		return tradeRepository.findById(tradeId)
			.orElseThrow(() -> new BusinessException(CommentErrorCode.NOT_FOUND_TRADE));
	}

}
