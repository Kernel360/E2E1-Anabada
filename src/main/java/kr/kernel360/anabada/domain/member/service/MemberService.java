package kr.kernel360.anabada.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse create(final CreateMemberRequest createMemberRequest) {
		Member member = memberRepository.save(CreateMemberRequest.toEntity(createMemberRequest));
		return CreateMemberResponse.of(member);
	}

	@Transactional
	public UpdateMemberResponse update(UpdateMemberRequest updateMemberRequest) {
		Member member = findMemberById(updateMemberRequest.getId());

		member.update(updateMemberRequest.getEmail(), updateMemberRequest.getNickname(),
			updateMemberRequest.getPassword(), updateMemberRequest.getGender()
			, updateMemberRequest.getBirth());

		return UpdateMemberResponse.of(member);
	}

	public FindMemberResponse find(Long id) {
		Member member = findMemberById(id);

		return FindMemberResponse.of(member);
	}

	public FindAllMemberResponse findAll() {
		List<Member> members = memberRepository.findAll();

		List<FindMemberResponse> responses = members.stream().map(FindMemberResponse::of).toList();

		return FindAllMemberResponse.of(responses);
	}

	@Transactional
	public Long remove(Long id) {
		Member member = findMemberById(id);

		memberRepository.delete(member);

		return id;
	}

	private Member findMemberById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
	}
}
