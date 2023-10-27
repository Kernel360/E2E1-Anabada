package kr.kernel360.anabada.domain.member.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberResponse;
import kr.kernel360.anabada.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/v1/members")
	public ResponseEntity<CreateMemberResponse> create(@RequestBody CreateMemberRequest createMemberRequest) {
		CreateMemberResponse createMemberResponse = memberService.create(createMemberRequest);
		URI url = URI.create("api/v1/members/" + createMemberResponse.getId());
		return ResponseEntity.created(url).body(createMemberResponse);
	}

	@PutMapping("/v1/members")
	public ResponseEntity<UpdateMemberResponse> update(@RequestBody UpdateMemberRequest updateMemberRequest) {
		UpdateMemberResponse updateMemberResponse = memberService.update(updateMemberRequest);
		return ResponseEntity.ok(updateMemberResponse);
	}

	@GetMapping("/v1/members/{id}")
	public ResponseEntity<FindMemberResponse> find(@PathVariable Long id) {
		FindMemberResponse findMemberResponse = memberService.find(id);
		return ResponseEntity.ok(findMemberResponse);
	}
	@PutMapping("/v1/members/{id}")
	public ResponseEntity<Long> remove(@PathVariable Long id) {
		memberService.remove(id);
		return ResponseEntity.ok(id);
	}
}
