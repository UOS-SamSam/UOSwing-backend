package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

/**
 * ReportSaveRequestDto
 * 신고 저장 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "ReportSaveRequest(신고 저장 정보)", description = "신고 저장에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    @ApiModelProperty(value = "신고 생리대함 id")
    private Long padBoxId;
    @ApiModelProperty(value = "신고 분류")
    private ReportTag tag;
    @ApiModelProperty(value = "신고 내용")
    private String content;
    @ApiModelProperty(value = "해결 여부")
    private Boolean isResolved;

    @Builder
    public ReportSaveRequestDto(Long padBoxId, ReportTag tag, String content, Boolean isResolved) {
        this.padBoxId = padBoxId;
        this.tag = tag;
        this.content = content;
        this.isResolved = isResolved;
    }

    public Report toEntity(PadBox padBox) {
        return Report.builder()
                .padBox(padBox)
                .tag(tag)
                .content(content)
                .isResolved(isResolved)
                .build();
    }
}
