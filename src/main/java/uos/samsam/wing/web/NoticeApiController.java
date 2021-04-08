package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.notice.NoticeService;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @GetMapping
    public List<NoticeResponseDto> findAllDesc() {
        return noticeService.findAllDesc();
    }

    @GetMapping("/{id}")
    public NoticeResponseDto findById(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @PostMapping
    public Long save(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }
}
