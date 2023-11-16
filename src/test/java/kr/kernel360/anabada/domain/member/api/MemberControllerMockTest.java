// package kr.kernel360.anabada.domain.member.api;
//
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import kr.kernel360.anabada.domain.member.dto.CreateMemberRequest;
//
// @DisplayName("회원 컨트롤러 mock 단위 테스트")
// @SpringBootTest
// @AutoConfigureMockMvc
// class MemberControllerMockTest {
// 	@Autowired
// 	private ObjectMapper objectMapper;
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@Test
// 	@DisplayName("회원이 저장되면, 201 Created를 반환한다.")
// 	void testCreateMember() throws Exception {
// 	    //given
// 		CreateMemberRequest member = createMemberDto();
//
// 	    //when
// 		String body = objectMapper.writeValueAsString(member);
//
// 	    //then
// 		mockMvc.perform(post("/api/v1/members")
// 				.content(body) //HTTP Body에 데이터를 담는다
// 				.contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
// 			)
// 			.andExpect(status().isCreated())
// 			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//
// 	}
//
// 	private static CreateMemberRequest createMemberDto() {
// 		return CreateMemberRequest.builder()
// 			.email("ad2d@naver.com")
// 			.nickname("iwanttogohome")
// 			.password("123412")
// 			.gender("M")
// 			.birth("1991-10-10")
// 			.build();
// 	}
// }
//
