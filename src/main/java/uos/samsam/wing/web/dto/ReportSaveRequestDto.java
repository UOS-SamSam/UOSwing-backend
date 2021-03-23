package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    private ReportTag tag;
    private String content;

    @Builder
    public ReportSaveRequestDto(ReportTag tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    public Report toEntity() {
        return Report.builder()
                .tag(tag)
                .content(content)
                .build();
    }
}
