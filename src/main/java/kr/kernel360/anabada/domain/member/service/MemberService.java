package kr.kernel360.anabada.domain.member.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse create(CreateMemberRequest createMemberRequest) {
		Member member = memberRepository.save(createMemberRequest.toEntity());
		return CreateMemberResponse.toDto(member);
	}

	@Transactional
	public Long update(UpdateMemberRequest updateMemberRequest) {
		Member member = memberRepository.findById(updateMemberRequest.getId())
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
		member.updatePassword(updateMemberRequest.getPassword());

		return member.getId();
	}
}
