package uos.samsam.wing.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;
import uos.samsam.wing.web.dto.ReportUpdateRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ReportService
 * 신고에 대한 비즈니스 로직을 정의하는 서비스 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final PadBoxRepository padBoxRepository;

    /**
     * 신고 저장(생성)
     * @param requestDto http request dto
     * @return 생성된 신고의 id
     */
    @Transactional
    public Long save(ReportSaveRequestDto requestDto) {
        Optional<PadBox> padBox = padBoxRepository.findById(requestDto.getPadBoxId());
        return reportRepository.save(requestDto.toEntity(padBox.get())).getId();
    }

    /**
     * id로 조회
     * @param id 불러오고자 하는 신고의 id
     * @return 신고 http response dto
     */
    @Transactional(readOnly = true)
    public ReportResponseDto findById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));

        return new ReportResponseDto(entity);
    }

    /**
     * 모든 신고 조회
     * @return 모든 공지 dto의 리스트
     */
    @Transactional(readOnly = true)
    public List<ReportResponseDto> findAll() {
        return reportRepository.findAll().stream()
                .map(ReportResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 신고 수정
     * @param id 수정하고자 하는 신고의 id
     * @param requestDto http request dto
     * @return 수정된 신고의 id
     */
    @Transactional
    public Long update(Long id, ReportUpdateRequestDto requestDto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));
        report.update(requestDto.getIsResolved());

        return id;
    }

    /**
     * 신고 삭제
     * @param id 삭제하고자 하는 신고의 id
     */
    @Transactional
    public void delete(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));

        reportRepository.delete(report);
    }
}
