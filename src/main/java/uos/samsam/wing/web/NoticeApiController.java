package uos.samsam.wing.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.notice.NoticeService;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.util.List;

/**
 * NoticeApiController
 * 공지 api가 정의되는 컨트롤러 클래스입니다.
 */
@Api(value = "NoticeApiController v1")
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @ApiOperation(value = "공지 전체조회", notes = "전체 공지를 내림차순으로 조회합니다. 나중에 생성한 공지가 먼저 조회됩니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping
    public List<NoticeResponseDto> findAllDesc() {
        return noticeService.findAllDesc();
    }

    @ApiOperation(value = "공지 조회", notes = "id에 해당하는 공지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 공지를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @GetMapping("/{id}")
    public NoticeResponseDto findById(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @ApiOperation(value = "공지 저장", notes = "새로운 공지를 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장 성공"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PostMapping
    public Long save(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.save(requestDto);
    }

    @ApiOperation(value = "공지 수정", notes = "id에 해당하는 공지를 수정합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 공지를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }

    @ApiOperation(value = "공지 삭제", notes = "id에 해당하는 공지를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 400, message = "id에 해당하는 공지를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }
}
