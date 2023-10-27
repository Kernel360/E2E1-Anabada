package kr.kernel360.anabada.domain.faq.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.faq.dto.CreateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.CreateFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FindAllFaqResponse;
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
		Member member = memberRepository.findById(createFaqRequest.getMemberId())
			.orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다"));

		Faq faq = faqRepository.save(createFaqRequest.toEntity(createFaqRequest, member));

		return CreateFaqResponse.of(faq);
	}

	@Transactional(readOnly = true)
	public FindAllFaqResponse findAll(){
		List<Faq> faqs = faqRepository.findAll();
		return FindAllFaqResponse.of(faqs);
	}

	@Transactional(readOnly = true)
	public FindFaqResponse find(Long faqId){
		Faq faq = faqRepository.findById(faqId)
			.orElseThrow(()-> new IllegalArgumentException("faq가 존재하지 않습니다"));
		return FindFaqResponse.of(faq);
	}

	@Transactional
	public UpdateFaqResponse update(Long faqId, UpdateFaqRequest updateFaqRequest){
		Faq faq = faqRepository.findById(faqId)
			.orElseThrow(() -> new IllegalArgumentException("faq가 존재하지 않습니다"));

		faq.update(updateFaqRequest.getTitle(),updateFaqRequest.getContent());

		return UpdateFaqResponse.toDto(faq);
	}

	@Transactional
	public void delete(Long faqId){
		faqRepository.deleteById(faqId);
	}
}
