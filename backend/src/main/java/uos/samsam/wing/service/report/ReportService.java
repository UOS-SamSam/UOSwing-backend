package uos.samsam.wing.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportRepository;
import uos.samsam.wing.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public Long save(ReportSaveRequestDto requestDto) {
        return reportRepository.save(requestDto.toEntity()).getId();
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
    public void delete(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 없습니다. id=" + id));

        reportRepository.delete(report);
    }
}
