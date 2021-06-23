package uos.samsam.wing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.domain.notice.NoticeRepository;
import uos.samsam.wing.domain.user.User;
import uos.samsam.wing.domain.user.UserRepository;
import uos.samsam.wing.service.user.UserService;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.UserLoginRequestDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserApiControllerTest
 * UserApiController를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private UserRepository userRepository;
    @Autowired private NoticeRepository noticeRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        preUrl = "http://localhost:" + port + "/api/v1/admin/";
    }

    @AfterEach
    void tearDown() throws Exception {
        userRepository.deleteAll();
        noticeRepository.deleteAll();
    }

    @Test
    void 로그인() throws Exception {
        //given
        String email = "samsam-uos@gmail.com";
        String password = "thisisasimplekey";
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(User.builder()
                .email(email)
                .password(encodedPassword)
                .build());

        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = preUrl + "login";

        //when, then
        MvcResult mvcResult = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isNotNull().isNotEmpty();
    }

/*
    @Test
    void 로그인_후_공지등록() throws Exception {
        //given
        String email = "samsam-uos@gmail.com";
        String password = "thisisasimplekey";
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(User.builder()
                .email(email)
                .password(encodedPassword)
                .build());

        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build();

        String url = preUrl + "login";

        //when, then
        MvcResult mvcResult = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();

        //공지 등록
        NoticeSaveRequestDto noticeSaveRequestDto = NoticeSaveRequestDto.builder()
                .title("this is title")
                .content("this is content")
                .build();

        mvc.perform(post("http://localhost:" + port + "/api/v1/notice/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-AUTH-TOKEN", token)
                .content(new ObjectMapper().writeValueAsString(noticeSaveRequestDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
*/

    @Test
    void 로그인_실패() throws Exception {
        //given
        String email = "samsam-uos@gmail.com";
        String password = "thisisasimplekey";
        String encodedPassword = passwordEncoder.encode(password);
        String wrongKey = "thisisnotasimplekey";
        userRepository.save(User.builder()
                .email(email)
                .password(encodedPassword)
                .build());

        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email(email)
                .password(wrongKey)
                .build();

        String url = preUrl + "login";

        //when, then
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());  //인증 실패
    }
}