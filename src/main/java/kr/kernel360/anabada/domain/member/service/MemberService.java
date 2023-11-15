package kr.kernel360.anabada.domain.member.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.AgeGroupDto;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByAgeGroupResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByGenderResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.GenderDto;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	private final PasswordEncoder passwordEncoder;
	@Transactional
	public Long update(UpdateMemberRequest updateMemberRequest) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = findByEmail(findEmailByJwt);

		member.update(updateMemberRequest.getNickname(), updateMemberRequest.getGender(),
			updateMemberRequest.getBirth());
		return member.getId();
	}

	@Transactional
	public Long updatePassword(String password) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = findByEmail(findEmailByJwt);

		member.updatePassword(passwordEncoder.encode(password));
		return member.getId();
	}

	public FindMemberResponse find() {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = findByEmail(findEmailByJwt);

		return FindMemberResponse.of(member);
	}

	public FindAllMemberResponse findAll() {
		List<Member> members = memberRepository.findAll();
		List<FindMemberResponse> responses = members.stream().map(FindMemberResponse::of).toList();

		return FindAllMemberResponse.of(responses);
	}

	public FindAllMemberByGenderResponse countMembersByGender() {
		List<GenderDto> genderList = memberRepository.countMembersByGender();

		return FindAllMemberByGenderResponse.of(genderList);
	}

	public FindAllMemberByAgeGroupResponse countMembersByAgeGroup() {
		List<AgeGroupDto> ageGroupList = memberRepository.countMembersByAgeGroup();

		return FindAllMemberByAgeGroupResponse.of(ageGroupList);
	}

	@Transactional
	public Long remove(Long id) {
		String findEmailByJwt = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = findByEmail(findEmailByJwt);

		memberRepository.delete(member);
		return id;
	}

	private Member findByEmail(String findEmailByJwt) {
		return memberRepository.findByEmail(findEmailByJwt)
			.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
	}
}
