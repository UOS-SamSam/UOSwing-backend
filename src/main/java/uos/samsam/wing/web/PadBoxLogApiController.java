package uos.samsam.wing.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.samsam.wing.service.padboxlog.PadBoxLogService;
import uos.samsam.wing.web.dto.StatisticsResponseDto;

import java.util.List;

@Api(value = "PadBoxLogApiController v1")
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
@RestController
public class PadBoxLogApiController {

    private final PadBoxLogService padBoxLogService;

    @ApiOperation(value = "사용량 통계 조회", notes = "duration일 동안의 생리대함 사용량 통계를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "통계 조회 성공"),
            @ApiResponse(code = 400, message = "양수의 기간을 입력하지 않음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping("/{duration}")
    public List<StatisticsResponseDto> statistics(@PathVariable Integer duration) {
        return padBoxLogService.statistics(duration);
    }
}
