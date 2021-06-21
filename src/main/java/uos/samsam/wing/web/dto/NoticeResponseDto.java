package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.notice.Notice;

import java.time.LocalDateTime;

/**
 * NoticeResponseDto
 * 공지 엔티티를 나타내는 DTO입니다.
 */
@ApiModel(value = "NoticeResponse(공지 조회 정보)", description = "공지 조회에 대한 응답입니다.")
@Getter
@NoArgsConstructor
public class NoticeResponseDto {

    @ApiModelProperty(value = "공지 id")
    private Long id;
    @ApiModelProperty(value = "공지 제목")
    private String title;
    @ApiModelProperty(value = "공지 내용")
    private String content;
    @ApiModelProperty(value = "공지 생성일")
    private LocalDateTime createdDate;

    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
    }
}
