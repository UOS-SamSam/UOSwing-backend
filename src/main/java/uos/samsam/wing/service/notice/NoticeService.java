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

/**
 * NoticeService
 * 공지에 대한 비즈니스 로직을 정의하는 서비스 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지 저장(생성)
     * @param requestDto http request dto
     * @return 생성된 공지의 id
     */
    @Transactional
    public Long save(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * id로 조회
     * @param id 불러오고자 하는 공지의 id
     * @return 공지 http response dto
     */
    public NoticeResponseDto findById(Long id) {
        Notice entity = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));

        return new NoticeResponseDto(entity);
    }

    /**
     * 생성일 기준 역순 출력
     * @return 생성일 기준 역순 정렬된 공지 리스트
     */
    @Transactional(readOnly = true)
    public List<NoticeResponseDto> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 공지 수정
     * @param id 수정하고자 하는 공지의 id
     * @param requestDto http request dto
     * @return 수정된 공지의 id
     */
    @Transactional
    public Long update(Long id, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));
        notice.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * 공지 삭제
     * @param id 삭제하고자 하는 공지의 id
     */
    @Transactional
    public void delete(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));

        noticeRepository.delete(notice);
    }
}
