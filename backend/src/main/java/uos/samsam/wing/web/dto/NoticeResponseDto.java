package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.notice.Notice;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {

    private String title;
    private String content;

    public NoticeResponseDto(Notice entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
