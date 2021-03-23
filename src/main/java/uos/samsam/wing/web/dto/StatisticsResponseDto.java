package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatisticsResponseDto {
    private String padBoxName;
    private Integer amount;

    @Builder
    public StatisticsResponseDto(String padBoxName, Integer amount) {
        this.padBoxName = padBoxName;
        this.amount = amount;
    }
}
