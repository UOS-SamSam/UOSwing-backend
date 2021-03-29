package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportUpdateRequestDto {

    private Boolean isResolved;

    @Builder
    public ReportUpdateRequestDto(Boolean isResolved) {
        this.isResolved = isResolved;
    }
}
