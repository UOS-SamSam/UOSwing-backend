package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;

@Getter
@NoArgsConstructor
public class PadBoxSaveRequestDto {
    private Double latitude;    // 위도
    private Double longitude;   // 경도
    private String address;     // 주소
    private String name;        // 이름
    private Integer padAmount;      // 남은 수량
    private Double temperature; // 온도
    private Double humidity;    // 습도

    @Builder
    public PadBoxSaveRequestDto(Double latitude, Double longitude, String address, String name, Integer padAmount, Double temperature, Double humidity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
        this.padAmount = padAmount;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public PadBox toEntity() {
        return PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
    }
}
