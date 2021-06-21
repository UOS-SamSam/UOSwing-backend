package uos.samsam.wing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.SecurityConfig;
import uos.samsam.wing.domain.notice.Notice;
import uos.samsam.wing.domain.notice.NoticeRepository;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * NoticeApiControllerTest
 * NoticeApiController를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private NoticeRepository noticeRepository;
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        preUrl = "http://localhost:" + port + "/api/v1/notice/";
    }

    @AfterEach
    void tearDown() throws Exception {
        noticeRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void 공지_등록() throws Exception {
        //given
        String title = "테스트 제목";
        String content = "테스트 내용";
        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = preUrl;

        //when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Notice> noticeList = noticeRepository.findAll();
        assertThat(noticeList.get(0).getTitle()).isEqualTo(title);
        assertThat(noticeList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void 공지_수정() throws Exception {
        //given
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build());

        Long updateId = savedNotice.getId();
        String expectedTitle = "수정된 제목";
        String expectedContent = "수정된 내용";

        NoticeUpdateRequestDto requestDto = NoticeUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = preUrl + updateId;

        //when
        mvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Notice> noticeList = noticeRepository.findAll();
        assertThat(noticeList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(noticeList.get(0).getContent()).isEqualTo(expectedContent);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 공지_삭제() throws Exception {
        //given
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build());

        Long deletedId = savedNotice.getId();

        String url = preUrl + deletedId;

        //when
        mvc.perform(delete(url))
                .andExpect(status().isOk());

        //then
        Optional<Notice> notice = noticeRepository.findById(deletedId);
        assertThat(notice).isEmpty();
    }

    @Test
    void 아무나_공지_조회() throws Exception {
        //given
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build());

        Long id = savedNotice.getId();
        String url1 = preUrl + id;
        String url2 = preUrl;

        //when, then
        mvc.perform(get(url1))      //특정 공지 조회
                .andExpect(status().isOk());
        mvc.perform(get(url2))      //공지 목록 조회
                .andExpect(status().isOk());
    }

    @Test
    void 아무나_공지_목록_조회() throws Exception {
        //given
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build());

        Long id = savedNotice.getId();
        String url = preUrl;

        //when, then
        mvc.perform(get(url))
                .andExpect(status().isOk());
    }
}