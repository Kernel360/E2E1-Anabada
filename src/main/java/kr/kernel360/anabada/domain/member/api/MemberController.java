package kr.kernel360.anabada.domain.member.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.member.dto.FindAllMemberByAgeGroupResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByGenderResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PutMapping("/v1/members")
	public ResponseEntity update(@RequestBody UpdateMemberRequest updateMemberRequest) {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/v1/members/{id}")
	public ResponseEntity<FindMemberResponse> find(@PathVariable Long id) {
		FindMemberResponse findMemberResponse = memberService.find(id);
		return ResponseEntity.ok(findMemberResponse);
	}

	@GetMapping("/v1/members")
	public ResponseEntity<FindAllMemberResponse> findAll() {
		FindAllMemberResponse findAllMemberResponse = memberService.findAll();
		return ResponseEntity.ok(findAllMemberResponse);
	}

	@GetMapping("/v1/members/gender")
	public ResponseEntity<FindAllMemberByGenderResponse> countMemberByGender() {
		FindAllMemberByGenderResponse findAllMemberByGenderResponse = memberService.countMembersByGender();
		return ResponseEntity.ok(findAllMemberByGenderResponse);
	}

	@GetMapping("/v1/members/age-group")
	public ResponseEntity<FindAllMemberByAgeGroupResponse> countMemberByAgeGroup() {
		FindAllMemberByAgeGroupResponse findAllMemberByAgeGroupResponse = memberService.countMembersByAgeGroup();
		return ResponseEntity.ok(findAllMemberByAgeGroupResponse);
	}

	@DeleteMapping("/v1/members/{id}")
	public ResponseEntity<Long> remove(@PathVariable Long id) {
		memberService.remove(id);
		return ResponseEntity.ok(id);
	}
}
