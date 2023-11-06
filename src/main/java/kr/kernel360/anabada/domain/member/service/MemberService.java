package kr.kernel360.anabada.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public UpdateMemberResponse update(UpdateMemberRequest updateMemberRequest) {
		Member member = memberRepository.findById(updateMemberRequest.getId())
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		member.update(updateMemberRequest.getEmail(), updateMemberRequest.getNickname(),
			updateMemberRequest.getPassword(), updateMemberRequest.getGender()
			, updateMemberRequest.getBirth());

		return UpdateMemberResponse.of(member);
	}

	public FindMemberResponse find(Long id) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		return FindMemberResponse.of(member);
	}

	public FindAllMemberResponse findAll() {
		List<Member> members = memberRepository.findAll();
		List<FindMemberResponse> responses = members.stream().map(FindMemberResponse::of).toList();

		return FindAllMemberResponse.of(responses);
	}

	@Transactional
	public Long remove(Long id) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

		memberRepository.delete(member);
		return id;
	}
}
