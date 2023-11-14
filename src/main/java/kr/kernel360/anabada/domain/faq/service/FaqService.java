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
		Member member = memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다"));

		Faq faq = faqRepository.save(CreateFaqRequest.toEntity(createFaqRequest, member));

		return CreateFaqResponse.of(faq);
	}

	public FindAllFaqResponse findAll(FaqSearchCondition faqSearchCondition, Pageable pageable){
		Page<FindFaqDto> findFaqs = faqRepository.findFaqs(faqSearchCondition, pageable);
		return FindAllFaqResponse.of(findFaqs);
	}

	public FindFaqResponse find(Long faqId){
		FindFaqDto faq = Optional.ofNullable(faqRepository.findFaq(faqId))
			.orElseThrow(() -> new IllegalArgumentException("faq가 존재하지 않습니다"));
		return FindFaqResponse.of(faq);
	}

	@Transactional
	public UpdateFaqResponse update(UpdateFaqRequest updateFaqRequest){
		Faq faq = faqRepository.findById(updateFaqRequest.getFaqId())
			.orElseThrow(()-> new IllegalArgumentException("faq가 존재하지 않습니다"));
		faq.update(updateFaqRequest.getTitle(),updateFaqRequest.getContent());
		return UpdateFaqResponse.of(faq);
	}

	@Transactional
	public void delete(Long faqId){
		faqRepository.deleteById(faqId);
	}
}
