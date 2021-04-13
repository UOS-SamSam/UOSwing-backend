package uos.samsam.wing.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.padbox.PadBoxService;
import uos.samsam.wing.web.dto.*;

import java.util.List;

@Api(value = "PadBoxApiController v1")
@RequiredArgsConstructor
@RequestMapping("/api/v1/padbox")
@RestController
public class PadBoxApiController {

    private final PadBoxService padBoxService;

    @ApiOperation(value = "생리대함 전체조회", notes = "전체 생리대함을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping
    public List<PadBoxListResponseDto> findAll() {
        return padBoxService.findAll();
    }

    @ApiOperation(value = "생리대함 조회", notes = "id에 해당하는 생리대함을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 생리대함을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping("/{id}")
    public PadBoxResponseDto findById(@PathVariable Long id) {
        return padBoxService.findById(id);
    }

    @ApiOperation(value = "생리대함 상태 갱신(아두이노)", notes = "id에 해당하는 생리대함의 상태(남은 수량, 온습도)를 갱신합니다. 아두이노 전용입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상태 갱신 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 생리대함을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PatchMapping("/updateState/{id}")
    public Long updateState(@PathVariable Long id, @RequestBody PadBoxUpdateStateRequestDto requestDto) {
        return padBoxService.updateState(id, requestDto);
    }

    @ApiOperation(value = "생리대함 저장", notes = "새로운 생리대함을 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PostMapping
    public Long save(@RequestBody PadBoxSaveRequestDto requestDto) {
        return padBoxService.save(requestDto);
    }

    @ApiOperation(value = "생리대함 정보 수정", notes = "id에 해당하는 생리대함의 정보(위도, 경도, 주소, 이름)를 수정합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 생리대함을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PadBoxUpdateRequestDto requestDto) {
        return padBoxService.update(id, requestDto);
    }

    @ApiOperation(value = "생리대함 삭제", notes = "id에 해당하는 생리대함을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 생리대함을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        padBoxService.delete(id);
    }

}
