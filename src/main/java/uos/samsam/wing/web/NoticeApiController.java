package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.notice.NoticeService;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @GetMapping("/api/v1/notice")
    public List<NoticeResponseDto> findAllDesc() {
        return noticeService.findAllDesc();
    }

    @GetMapping("/api/v1/notice/{id}")
    public NoticeResponseDto findById(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @PostMapping("/api/v1/notice")
    public Long save(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.save(requestDto);
    }

    @PutMapping("/api/v1/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/notice/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }
}
