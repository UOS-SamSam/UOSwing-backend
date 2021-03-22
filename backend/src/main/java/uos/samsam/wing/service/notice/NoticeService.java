package uos.samsam.wing.service.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.notice.Notice;
import uos.samsam.wing.domain.notice.NoticeRepository;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    public NoticeResponseDto findById(Long id) {
        Notice entity = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));

        return new NoticeResponseDto(entity);
    }

    @Transactional
    public List<NoticeResponseDto> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));
        notice.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));

        noticeRepository.delete(notice);
    }
}
