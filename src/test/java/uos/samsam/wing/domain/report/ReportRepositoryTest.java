package uos.samsam.wing.domain.report;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReportRepositoryTest {

    ReportRepository reportRepository;

    @Autowired
    public ReportRepositoryTest(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @AfterEach
    void cleanup() {
        reportRepository.deleteAll();
    }

    @Test
    void report_저장_조회() {
        //given
//        LocalDateTime now = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.of(2021, 3, 27, 0, 0, 0);
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        reportRepository.save(Report.builder()
                .tag(tag)
                .content(content)
                .build());

        //when
        List<Report> reportList = reportRepository.findAll();

        //then
        Report report = reportList.get(0);
        assertThat(report.getTag()).isEqualTo(tag);
        assertThat(report.getContent()).isEqualTo(content);
        assertThat(report.getCreatedDate()).isAfter(now);
        System.out.println("notice.getCreatedDate() = " + report.getCreatedDate());
        assertThat(report.getCreatedDate()).isAfter(now);
    }
}