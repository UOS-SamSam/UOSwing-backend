package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.notice.Notice;

@ApiModel(value = "NoticeSaveRequest(공지 저장 정보)", description = "공지 저장에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {

    @ApiModelProperty(value = "공지 제목")
    private String title;
    @ApiModelProperty(value = "공지 내용")
    private String content;

    @Builder
    public NoticeSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .build();
    }
}
