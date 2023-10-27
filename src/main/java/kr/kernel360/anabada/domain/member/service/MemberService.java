package kr.kernel360.anabada.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse create(CreateMemberRequest createMemberRequest) {
		Member member = memberRepository.save(CreateMemberRequest.toEntity(createMemberRequest));
		return CreateMemberResponse.of(member);
	}

	//Dynamic Update Annotation class 에 추가
	@Transactional
	public UpdateMemberResponse update(UpdateMemberRequest updateMemberRequest) {
		Member member = memberRepository.findById(updateMemberRequest.getId())
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		member.update(updateMemberRequest.getEmail(), updateMemberRequest.getNickname(),
			updateMemberRequest.getPassword(), updateMemberRequest.getGender()
			, updateMemberRequest.getBirth());

		return UpdateMemberResponse.toDto(member);
	}

}
