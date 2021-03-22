package uos.samsam.wing.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

@Getter
@NoArgsConstructor
public class ReportResponseDto {

    private ReportTag tag;
    private String content;

    public ReportResponseDto(Report entity) {
        this.tag = entity.getTag();
        this.content = entity.getContent();
    }
}
