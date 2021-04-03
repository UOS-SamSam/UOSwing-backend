package uos.samsam.wing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.domain.admin.Admin;
import uos.samsam.wing.domain.admin.AdminRepository;
import uos.samsam.wing.web.dto.AdminLoginRequestDto;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private AdminRepository adminRepository;
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
        adminRepository.deleteAll();
    }

    /*
        로그인 테스트에서 response body가 비어있는 문제 발생함: 수정요함
        근데 swagger에는 token이 return 되는 것으로 써있음(?)
    * */
    @Test
    void 로그인() throws Exception {
        //given
        String key = "thisisasimplekey";
        adminRepository.save(Admin
        .builder()
        .key(key)
        .build());
        AdminLoginRequestDto requestDto = AdminLoginRequestDto.builder()
                .key(key)
                .build();

        String url = preUrl + "login";

        //when, then
        MvcResult mvcResult = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("content = " + content);
    }

    @Test
    void 로그인_실패() throws Exception {
        //given
        String key = "thisisasimplekey";
        String wrongKey = "thisisnotasimplekey";
        adminRepository.save(Admin
                .builder()
                .key(key)
                .build());
        AdminLoginRequestDto requestDto = AdminLoginRequestDto.builder()
                .key(wrongKey)
                .build();

        String url = preUrl + "login";

        //when, then
        MvcResult result = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("content = " + content);
    }
}