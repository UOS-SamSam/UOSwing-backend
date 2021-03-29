package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.report.Report;
import uos.samsam.wing.domain.report.ReportTag;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    private Long padBoxId;
    private ReportTag tag;
    private String content;
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
