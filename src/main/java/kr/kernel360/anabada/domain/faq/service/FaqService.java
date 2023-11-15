package kr.kernel360.anabada.domain.faq.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.faq.dto.CreateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.CreateFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FaqSearchCondition;
import kr.kernel360.anabada.domain.faq.dto.FindAllFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FindFaqDto;
import kr.kernel360.anabada.domain.faq.dto.FindFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqResponse;
import kr.kernel360.anabada.domain.faq.entity.Faq;
import kr.kernel360.anabada.domain.faq.repository.FaqRepository;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.global.error.code.FaqErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FaqService {
	private final FaqRepository faqRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public CreateFaqResponse create(CreateFaqRequest createFaqRequest) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member findMember = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new BusinessException(FaqErrorCode.NOT_FOUND_MEMBER));
		Faq savedFaq = faqRepository.save(CreateFaqRequest.toEntity(createFaqRequest, findMember));

		return CreateFaqResponse.of(savedFaq);
	}

	public FindAllFaqResponse findAll(FaqSearchCondition faqSearchCondition, Pageable pageable){
		Page<FindFaqDto> findFaqs = faqRepository.findFaqs(faqSearchCondition, pageable);

		return FindAllFaqResponse.of(findFaqs);
	}

	public FindFaqResponse find(Long faqId){
		FindFaqDto findFaq = Optional.ofNullable(faqRepository.findFaq(faqId))
			.orElseThrow(() -> new BusinessException(FaqErrorCode.NOT_FOUND_FAQ));

		return FindFaqResponse.of(findFaq);
	}

	@Transactional
	public UpdateFaqResponse update(UpdateFaqRequest updateFaqRequest){
		Faq findFaq = faqRepository.findById(updateFaqRequest.getFaqId())
			.orElseThrow(()-> new BusinessException(FaqErrorCode.NOT_FOUND_FAQ));
		findFaq.update(updateFaqRequest.getTitle(),updateFaqRequest.getContent());

		return UpdateFaqResponse.of(findFaq);
	}

	@Transactional
	public Long delete(Long faqId){
		faqRepository.deleteById(faqId);

		return faqId;
	}
}
