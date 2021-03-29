package uos.samsam.wing.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.web.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final PadBoxRepository padBoxRepository;

    @Transactional
    public Long save(ReportSaveRequestDto requestDto) {
        Optional<PadBox> padBox = padBoxRepository.findById(requestDto.getPadBoxId());
        return reportRepository.save(requestDto.toEntity(padBox.get())).getId();
    }

    public ReportResponseDto findById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));

        return new ReportResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ReportResponseDto> findAll() {
        return reportRepository.findAll().stream()
                .map(ReportResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, ReportUpdateRequestDto requestDto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));
        report.update(requestDto.getIsResolved());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));

        reportRepository.delete(report);
    }
}
