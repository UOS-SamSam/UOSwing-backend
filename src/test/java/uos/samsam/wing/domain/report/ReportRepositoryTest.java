package uos.samsam.wing.domain.report;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.domain.padbox.PadBox;

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
        Boolean isResolved = false;
        PadBox padBox = PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build();

        reportRepository.save(Report.builder()
                .padBox(padBox)
                .tag(tag)
                .content(content)
                .isResolved(isResolved)
                .build());

        //when
        List<Report> reportList = reportRepository.findAll();

        //then
        Report report = reportList.get(0);
        assertThat(report.getPadBox().getId()).isEqualTo(padBox.getId());
        assertThat(report.getTag()).isEqualTo(tag);
        assertThat(report.getContent()).isEqualTo(content);
        assertThat(report.getIsResolved()).isEqualTo(isResolved);
        assertThat(report.getCreatedDate()).isAfter(now);
    }
}