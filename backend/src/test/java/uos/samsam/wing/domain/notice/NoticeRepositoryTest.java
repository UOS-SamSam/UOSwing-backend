package uos.samsam.wing.domain.notice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NoticeRepositoryTest {

    NoticeRepository noticeRepository;

    @Autowired
    public NoticeRepositoryTest(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @AfterEach
    public void cleanup() {
        noticeRepository.deleteAll();
    }

    @Test
    void 모든_notice_조회() {
        //given
//        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        String title = "테스트 제목";
        String content = "테스트 내용";
        noticeRepository.save(Notice.builder()
                .title(title)
                .content(content)
                .build());

        //when
        List<Notice> notices = noticeRepository.findAll();

        //then
        Notice notice = notices.get(0);
        assertThat(notice.getTitle()).isEqualTo(title);
        assertThat(notice.getContent()).isEqualTo(content);
        System.out.println("notice.getCreated() = " + notice.getCreated());
        assertThat(notice.getCreated()).isAfter(now);
    }
}