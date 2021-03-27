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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.domain.report.ReportTag;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReportApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private ReportRepository reportRepository;
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        preUrl = "http://localhost:" + port + "/api/v1/report/";
    }

    @AfterEach
    void tearDown() throws Exception {
        reportRepository.deleteAll();
    }

    @Test
    void 아무나_신고_등록() throws Exception {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .tag(tag)
                .content(content)
                .build();

        String url = preUrl;

        //when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList.get(0).getTag()).isEqualTo(tag);
        assertThat(reportList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 신고_조회() throws Exception {
        //given
        Report savedReport = reportRepository.save(Report.builder()
                .tag(ReportTag.BROKEN)
                .content("테스트 내용")
                .build());

        Long id = savedReport.getId();
        String url = preUrl + id;

        //when, then
        mvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    void 신고_조회_실패() throws Exception {
        //given
        Report savedReport = reportRepository.save(Report.builder()
                .tag(ReportTag.BROKEN)
                .content("테스트 내용")
                .build());

        Long id = savedReport.getId();
        String url = preUrl + id;

        //when, then
        mvc.perform(get(url))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 신고_삭제() throws Exception {
        //given
        Report savedReport = reportRepository.save(Report.builder()
                .tag(ReportTag.BROKEN)
                .content("테스트 내용")
                .build());

        Long deletedId = savedReport.getId();
        String url = preUrl + deletedId;

        //when
        mvc.perform(delete(url))
                .andExpect(status().isOk());

        //then
        Optional<Report> report = reportRepository.findById(deletedId);
        assertThat(report).isEmpty();
    }
}