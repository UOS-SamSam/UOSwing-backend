package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.notice.Notice;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
    }
}
