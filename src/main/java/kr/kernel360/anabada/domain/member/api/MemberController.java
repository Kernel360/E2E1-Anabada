package kr.kernel360.anabada.domain.member.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
import kr.kernel360.anabada.domain.member.dto.CreateMemberResponse;
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
		return ResponseEntity.ok(createMemberResponse);
	}

	@PostMapping("/")
}
