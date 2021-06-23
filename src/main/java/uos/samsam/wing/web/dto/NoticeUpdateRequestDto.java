package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NoticeUpdateRequestDto
 * 공지 수정 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "NoticeUpdateRequest(공지 수정 정보)", description = "공지 수정에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    @ApiModelProperty(value = "수정된 공지 제목")
    private String title;
    @ApiModelProperty(value = "수정된 공지 내용")
    private String content;

    @Builder
    public NoticeUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
