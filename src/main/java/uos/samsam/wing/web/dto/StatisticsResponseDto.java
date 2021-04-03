package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatisticsResponseDto {
    private Long padBoxId;
    private String padBoxName;
    private Integer amount;

    @Builder
    public StatisticsResponseDto(Long padBoxId, String padBoxName, Integer amount) {
        this.padBoxId = padBoxId;
        this.padBoxName = padBoxName;
        this.amount = amount;
    }
}
