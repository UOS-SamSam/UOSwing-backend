package uos.samsam.wing.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.report.ReportService;
import uos.samsam.wing.web.dto.ReportResponseDto;
import uos.samsam.wing.web.dto.ReportSaveRequestDto;
import uos.samsam.wing.web.dto.ReportUpdateRequestDto;

import java.util.List;

@Api(value = "ReportApiController v1")
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
@RestController
public class ReportApiController {

    private final ReportService reportService;

    @ApiOperation(value = "신고 전체조회", notes = "전체 신고를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping
    public List<ReportResponseDto> findAll() {
        return reportService.findAll();
    }

    @ApiOperation(value = "신고 조회", notes = "id에 해당하는 신고를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 신고를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping("/{id}")
    public ReportResponseDto findById(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @ApiOperation(value = "신고 저장", notes = "id에 해당하는 신고를 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PostMapping
    public Long save(@RequestBody ReportSaveRequestDto requestDto) {
        return reportService.save(requestDto);
    }

    @ApiOperation(value = "신고 수정", notes = "id에 해당하는 신고를 수정합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 신고를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReportUpdateRequestDto requestDto) {
        return reportService.update(id, requestDto);
    }

    @ApiOperation(value = "신고 삭제", notes = "id에 해당하는 신고를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 신고를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        reportService.delete(id);
        return id;
    }
}
