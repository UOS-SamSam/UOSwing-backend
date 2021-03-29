package uos.samsam.wing.service.report;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.domain.report.ReportTag;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;
import uos.samsam.wing.web.dto.ReportUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReportServiceTest {

    @Autowired ReportService reportService;
    @Autowired ReportRepository reportRepository;
    @Autowired PadBoxRepository padBoxRepository;

    @AfterEach
    void cleanup() {
        reportRepository.deleteAll();
    }

    @Test
    void 신고_저장_조회() {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        Boolean isResolved = false;
        PadBox savedPadBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());
        Long savedPadBoxId = savedPadBox.getId();

        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .padBoxId(savedPadBoxId)
                .tag(tag)
                .content(content)
                .isResolved(isResolved)
                .build();

        //when
        Long reportId = reportService.save(requestDto);

        //then
        ReportResponseDto responseDto = reportService.findById(reportId);
        assertThat(responseDto.getPadBox().getId()).isEqualTo(savedPadBoxId);
        assertThat(responseDto.getTag()).isEqualTo(tag);
        assertThat(responseDto.getContent()).isEqualTo(content);
        assertThat(responseDto.getIsResolved()).isEqualTo(isResolved);
    }

    @Test
    void 신고_상태_수정() {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        Boolean isResolved = false;
        PadBox savedPadBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());
        Long savedPadBoxId = savedPadBox.getId();

        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .padBoxId(savedPadBoxId)
                .tag(tag)
                .content(content)
                .isResolved(isResolved)
                .build();
        Long id = reportService.save(requestDto);

        //when
        Boolean newIsResolved = true;
        ReportUpdateRequestDto updateRequestDto = ReportUpdateRequestDto.builder()
                .isResolved(newIsResolved)
                .build();
        reportService.update(id, updateRequestDto);

        //then
        ReportResponseDto responseDto = reportService.findById(id);
        assertThat(responseDto.getIsResolved()).isEqualTo(newIsResolved);
    }

    @Test
    void 신고_삭제() {
        //given
        ReportTag tag = ReportTag.BROKEN;
        String content = "테스트 내용";
        Boolean isResolved = false;
        PadBox savedPadBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());
        Long savedPadBoxId = savedPadBox.getId();

        ReportSaveRequestDto requestDto = ReportSaveRequestDto.builder()
                .padBoxId(savedPadBoxId)
                .tag(tag)
                .content(content)
                .isResolved(isResolved)
                .build();

        Long id = reportService.save(requestDto);

        //when
        reportService.delete(id);

        //then
        List<ReportResponseDto> reportList = reportService.findAll();
        assertThat(reportList.size()).isEqualTo(0);
    }
}