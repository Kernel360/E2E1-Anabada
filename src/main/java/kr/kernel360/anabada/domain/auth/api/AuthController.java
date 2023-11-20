package kr.kernel360.anabada.domain.auth.api;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.kernel360.anabada.domain.auth.dto.LoginRequest;
import kr.kernel360.anabada.domain.auth.dto.LoginResponse;
import kr.kernel360.anabada.domain.auth.dto.SignUpRequest;
import kr.kernel360.anabada.domain.auth.dto.TokenDto;
import kr.kernel360.anabada.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@Api(tags = "인증 API", description = "/api/v1/auth")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@ApiOperation(value = "로그인 인증")
	@ApiResponses({@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 401, message = "계정정보가 일치하지 않습니다. \t\n 비밀번호가 일치하지 않습니다. \t\n 비활성화 상태 계정입니다."),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 409, message = "소셜로 가입한 회원입니다. 소셜로 로그인 해주세요."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/authenticate")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authService.authenticate(loginRequest);

		return ResponseEntity.ok().body(loginResponse);
	}

	@ApiOperation(value = "화면 인증")
	@ApiResponses({@ApiResponse(code = 200, message = "인증 성공"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@GetMapping("/v1/authorize")
	public HttpStatus authorize() {
		return HttpStatus.OK;
	}

	@ApiOperation(value = "이메일 중복 확인")
	@ApiResponses({@ApiResponse(code = 204, message = "이메일 중복 확인 성공"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 409, message = "사용 중인 이메일입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/isEmailUnique")
	public ResponseEntity<Void> isEmailUnique(@RequestBody SignUpRequest signUpRequest) {
		authService.isEmailUnique(signUpRequest.getEmail());

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "닉네임 중복 확인")
	@ApiResponses({@ApiResponse(code = 204, message = "닉네임 중복 확인 성공"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 409, message = "사용 중인 닉네임입니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/isNicknameUnique")
	public ResponseEntity<Void> isNicknameUnique(@RequestBody SignUpRequest signUpRequest) {
		authService.isNicknameUnique(signUpRequest.getNickname());

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "회원 가입")
	@ApiResponses({@ApiResponse(code = 201, message = "회원 가입 성공"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/signup")
	public ResponseEntity<Long> signUp(@RequestBody SignUpRequest signUpRequest) {
		Long savedMemberId = authService.signUp(signUpRequest);
		URI uri = URI.create("/api/v1/auth/signup/" + savedMemberId);

		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "액세스 토큰 재발급")
	@ApiResponses({@ApiResponse(code = 200, message = "액세스 토큰 재발급 성공"),
		@ApiResponse(code = 401, message = "만료일자가 지난 refreshToken \t\n 해당 refreshToken의 accessToken이 아님"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/reissue")
	public ResponseEntity<TokenDto> reissueAccessToken(@RequestBody TokenDto requestTokenDto) {
		TokenDto tokenDto = authService.reissueAccessToken(requestTokenDto);

		return ResponseEntity.ok().body(tokenDto);
	}

	@ApiOperation(value = "로그아웃")
	@ApiResponses({@ApiResponse(code = 204, message = "로그아웃 성공"),
		@ApiResponse(code = 403, message = "잘못된 접근입니다."),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없습니다."),
		@ApiResponse(code = 500, message = "서버 오류")})
	@PostMapping("/v1/auth/logout")
	public ResponseEntity<Void> logout(@RequestBody Map<String,String> map) {
		authService.logout(map.get("refreshToken"));

		return ResponseEntity.noContent().build();
	}
}
