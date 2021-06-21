package uos.samsam.wing.service.notice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.domain.notice.NoticeRepository;
import uos.samsam.wing.web.dto.NoticeResponseDto;
import uos.samsam.wing.web.dto.NoticeSaveRequestDto;
import uos.samsam.wing.web.dto.NoticeUpdateRequestDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * NoticeServiceTest
 * NoticeService를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class NoticeServiceTest {

    @Autowired NoticeRepository noticeRepository;
    @Autowired NoticeService noticeService;

    @AfterEach
    void cleanup() {
        noticeRepository.deleteAll();
    }

    @Test
    void 공지_저장_조회() {
        //given
        String title = "테스트 제목";
        String content = "테스트 내용";
        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        //when
        Long noticeId = noticeService.save(requestDto);

        //then
        NoticeResponseDto responseDto = noticeService.findById(noticeId);
        assertThat(responseDto.getTitle()).isEqualTo(title);
        assertThat(responseDto.getContent()).isEqualTo(content);
        assertThat(responseDto.getCreatedDate()).isBefore(LocalDateTime.now());
    }

    @Test
    void 공지_역순_출력() {
        //given
        String title1 = "테스트 제목1";
        String content1 = "테스트 내용1";

        NoticeSaveRequestDto requestDto1 = NoticeSaveRequestDto.builder()
                .title(title1)
                .content(content1)
                .build();

        String title2 = "테스트 제목2";
        String content2 = "테스트 내용2";
        NoticeSaveRequestDto requestDto2 = NoticeSaveRequestDto.builder()
                .title(title2)
                .content(content2)
                .build();
        //when
        noticeService.save(requestDto1);
        noticeService.save(requestDto2);

        //then
        List<NoticeResponseDto> noticeList = noticeService.findAllDesc();

        assertThat(noticeList.size()).isEqualTo(2);     //크기 확인
        //역순정렬 확인
        assertThat(noticeList.get(0).getTitle()).isEqualTo(title2);
        assertThat(noticeList.get(0).getContent()).isEqualTo(content2);
        assertThat(noticeList.get(1).getTitle()).isEqualTo(title1);
        assertThat(noticeList.get(1).getContent()).isEqualTo(content1);
    }

    @Test
    void 공지_수정() {
        //given
        String title = "테스트 제목";
        String content = "테스트 내용";
        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        Long id = noticeService.save(requestDto);

        //when
        String newTitle = "수정된 테스트 제목";
        String newContent = "수정된 테스트 내용";
        NoticeUpdateRequestDto updateRequestDto = NoticeUpdateRequestDto.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        noticeService.update(id, updateRequestDto);

        //then
        NoticeResponseDto responseDto = noticeService.findById(id);
        assertThat(responseDto.getTitle()).isNotEqualTo(title);
        assertThat(responseDto.getContent()).isNotEqualTo(content);
        assertThat(responseDto.getTitle()).isEqualTo(newTitle);
        assertThat(responseDto.getContent()).isEqualTo(newContent);
    }

    @Test
    void 공지_삭제() {
        //given
        String title = "테스트 제목";
        String content = "테스트 내용";
        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        Long id = noticeService.save(requestDto);

        //when
        noticeService.delete(id);

        //then
        List<NoticeResponseDto> noticeList = noticeService.findAllDesc();
        assertThat(noticeList.size()).isEqualTo(0);
    }
}