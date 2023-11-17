package kr.kernel360.anabada.domain.member.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByAgeGroupResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberByGenderResponse;
import kr.kernel360.anabada.domain.member.dto.FindAllMemberResponse;
import kr.kernel360.anabada.domain.member.dto.FindMemberResponse;
import kr.kernel360.anabada.domain.member.dto.UpdateMemberRequest;
import kr.kernel360.anabada.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Api(tags = "회원 API", description = "/api/v1/members")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@ApiOperation(value = "회원 정보 수정")
	@ApiResponses({@ApiResponse(code = 200, message = "회원 정보 수정 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PutMapping("/v1/members")
	public ResponseEntity<Long> update(@RequestBody UpdateMemberRequest updateMemberRequest) {
		Long updatedMemberId = memberService.update(updateMemberRequest);

		return ResponseEntity.ok(updatedMemberId);
	}

	@ApiOperation(value = "회원 비밀번호 수정")
	@ApiResponses({@ApiResponse(code = 200, message = "회원 비밀번호 수정 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PutMapping("/v1/members/password")
	public ResponseEntity<Long> updatePassword(@RequestBody Map<String,String> password) {
		Long updatedPassword = memberService.updatePassword(password.get("password"));

		return ResponseEntity.ok(updatedPassword);
	}

	@ApiOperation(value = "현재 회원 정보 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "현재 회원 정보 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 403, message = "비활성화 상태 계정입니다."),
		@ApiResponse(code = 404, message = "존재하지 않는 회원 입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/v1/members/info")
	public ResponseEntity<FindMemberResponse> find() {
		FindMemberResponse findMemberResponse = memberService.find();

		return ResponseEntity.ok(findMemberResponse);
	}

	@ApiOperation(value = "모든 회원 수 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "모든 회원 수 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@Secured("ROLE_ADMIN")
	@GetMapping("/v1/members")
	public ResponseEntity<FindAllMemberResponse> findAll() {
		FindAllMemberResponse findAllMemberResponse = memberService.findAll();

		return ResponseEntity.ok(findAllMemberResponse);
	}

	@ApiOperation(value = "성별 회원 수 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "성별 회원 수 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@Secured("ROLE_ADMIN")
	@GetMapping("/v1/members/gender")
	public ResponseEntity<FindAllMemberByGenderResponse> countMemberByGender() {
		FindAllMemberByGenderResponse findAllMemberByGenderResponse = memberService.countMembersByGender();

		return ResponseEntity.ok(findAllMemberByGenderResponse);
	}

	@ApiOperation(value = "연령별 회원 수 조회")
	@ApiResponses({@ApiResponse(code = 200, message = "연령별 회원 수 조회 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@Secured("ROLE_ADMIN")
	@GetMapping("/v1/members/age-group")
	public ResponseEntity<FindAllMemberByAgeGroupResponse> countMemberByAgeGroup() {
		FindAllMemberByAgeGroupResponse findAllMemberByAgeGroupResponse = memberService.countMembersByAgeGroup();

		return ResponseEntity.ok(findAllMemberByAgeGroupResponse);
	}

	@ApiOperation(value = "회원 탈퇴")
	@ApiResponses({@ApiResponse(code = 200, message = "회원 탈퇴 성공"),
		@ApiResponse(code = 401, message = "접근 권한이 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@ApiImplicitParam(name = "page_number", required = true
		, dataType = "int", paramType = "path", defaultValue = "1")
	@DeleteMapping("/v1/members/{id}")
	public ResponseEntity<Long> remove(@PathVariable Long id) {
		Long removedMemberId = memberService.remove(id);

		return ResponseEntity.ok(removedMemberId);

	}
}
