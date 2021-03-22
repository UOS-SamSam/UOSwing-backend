package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PadBoxUpdateStateRequestDto {
    private Integer padAmount;      // 남은 수량
    private Double temperature; // 온도
    private Double humidity;    // 습도

    @Builder
    public PadBoxUpdateStateRequestDto(Integer padAmount, Double temperature, Double humidity) {
        this.padAmount = padAmount;
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
