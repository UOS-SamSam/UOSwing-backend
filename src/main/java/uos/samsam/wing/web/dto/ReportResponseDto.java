package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

import java.time.LocalDateTime;

/**
 * ReportResponseDto
 * 신고 엔티티를 나타내는 DTO입니다.
 */
@ApiModel(value = "ReportResponse(신고 조회 정보)", description = "신고 조회에 대한 응답입니다.")
@Getter
@NoArgsConstructor
public class ReportResponseDto {

    @ApiModelProperty(value = "신고 id")
    private Long id;
    @ApiModelProperty(value = "신고된 생리대함 조회 정보")
    private Long padBoxId;
    @ApiModelProperty(value = "신고 분류")
    private ReportTag tag;
    @ApiModelProperty(value = "신고 내용")
    private String content;
    @ApiModelProperty(value = "해결 여부")
    private Boolean isResolved;
    @ApiModelProperty(value = "신고 생성일")
    private LocalDateTime createdDate;

    public ReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.padBoxId = entity.getPadBox().getId();
        this.tag = entity.getTag();
        this.content = entity.getContent();
        this.isResolved = entity.getIsResolved();
        this.createdDate = entity.getCreatedDate();
    }
}
