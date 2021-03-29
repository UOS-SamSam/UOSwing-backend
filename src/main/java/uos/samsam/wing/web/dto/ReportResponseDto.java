package uos.samsam.wing.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

@Getter
@NoArgsConstructor
public class ReportResponseDto {

    private Long id;
    private PadBox padBox;
    private ReportTag tag;
    private String content;
    private Boolean isResolved;

    public ReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.padBox = entity.getPadBox();
        this.tag = entity.getTag();
        this.content = entity.getContent();
        this.isResolved = entity.getIsResolved();
    }
}
