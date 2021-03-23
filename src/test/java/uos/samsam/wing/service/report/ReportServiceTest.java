package uos.samsam.wing.service.report;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.domain.report.ReportTag;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReportServiceTest {

    @Autowired ReportService reportService;
    @Autowired ReportRepository reportRepository;

    @AfterEach
    void cleanup() {
        reportRepository.deleteAll();
    }

    @Test
    void 신고_저장_조회() {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .tag(tag)
                .content(content)
                .build();

        //when
        Long reportId = reportService.save(requestDto);

        //then
        ReportResponseDto responseDto = reportService.findById(reportId);
        assertThat(responseDto.getTag()).isEqualTo(tag);
        assertThat(responseDto.getContent()).isEqualTo(content);
    }

    @Test
    void 공지_삭제() {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .tag(tag)
                .content(content)
                .build();
        Long id = reportService.save(requestDto);

        //when
        reportService.delete(id);

        //then
        List<ReportResponseDto> reportList = reportService.findAll();
        assertThat(reportList.size()).isEqualTo(0);
    }
}