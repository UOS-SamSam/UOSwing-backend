package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.report.ReportService;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
@RestController
public class ReportApiController {

    private final ReportService reportService;

    @GetMapping("/")
    public List<ReportResponseDto> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/{id}")
    public ReportResponseDto findById(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @PostMapping("/")
    public Long save(@RequestBody ReportSaveRequestDto requestDto) {
        return reportService.save(requestDto);
    }


    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        reportService.delete(id);
        return id;
    }
}
