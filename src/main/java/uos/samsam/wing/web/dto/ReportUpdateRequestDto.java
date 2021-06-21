package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ReportUpdateRequestDto
 * 신고 수정 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "ReportUpdateRequest(신고 수정 정보)", description = "신고 수정에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class ReportUpdateRequestDto {

    @ApiModelProperty(value = "해결 여부")
    private Boolean isResolved;

    @Builder
    public ReportUpdateRequestDto(Boolean isResolved) {
        this.isResolved = isResolved;
    }
}
