package kr.kernel360.anabada.domain.member.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.member.dto.FindAllMemberByAgeGroupResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByGenderResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
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

		member.update(updateMemberRequest.getGender(), updateMemberRequest.getBirth());
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
		List<Object[]> genderCounts = memberRepository.countMembersByGender();
		FindAllMemberByGenderResponse findAllMemberByGenderResponse = new FindAllMemberByGenderResponse();
		for (Object[] genderCount : genderCounts) {
			if (((String)genderCount[0]).equals("M")) {
				findAllMemberByGenderResponse.setMale((Long)genderCount[1]);
			} else {
				findAllMemberByGenderResponse.setFemale((Long)genderCount[1]);
			}
		}
		return findAllMemberByGenderResponse;
	}

	public FindAllMemberByAgeGroupResponse countMembersByAgeGroup() {
		List<Object[]> ageGroupCounts = memberRepository.countMembersByAgeGroup();
		FindAllMemberByAgeGroupResponse findAllMemberByAgeGroupResponse = new FindAllMemberByAgeGroupResponse();
		for (Object[] ageGroupCount : ageGroupCounts) {
			switch ((String) ageGroupCount[0]) {
				case "10대" -> findAllMemberByAgeGroupResponse.setTeenagers((Long)ageGroupCount[1]);
				case "20대" -> findAllMemberByAgeGroupResponse.setTwenties((Long)ageGroupCount[1]);
				case "30대" -> findAllMemberByAgeGroupResponse.setThirties((Long)ageGroupCount[1]);
				case "40대" -> findAllMemberByAgeGroupResponse.setForties((Long)ageGroupCount[1]);
				case "50대" -> findAllMemberByAgeGroupResponse.setFifties((Long)ageGroupCount[1]);
				case "60대" -> findAllMemberByAgeGroupResponse.setSixties((Long)ageGroupCount[1]);
			}
		}
		return findAllMemberByAgeGroupResponse;
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
