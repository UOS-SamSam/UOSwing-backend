package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.report.ReportService;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReportApiController {

    private final ReportService reportService;

    @GetMapping("/api/v1/report")
    public List<ReportResponseDto> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/api/v1/report/{id}")
    public ReportResponseDto findById(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @PostMapping("/api/v1/report")
    public Long save(@RequestBody ReportSaveRequestDto requestDto) {
        return reportService.save(requestDto);
    }


    @DeleteMapping("/api/v1/report/{id}")
    public Long delete(@PathVariable Long id) {
        reportService.delete(id);
        return id;
    }
}
