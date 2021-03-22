package uos.samsam.wing.web.dto;

import lombok.Getter;
import uos.samsam.wing.domain.padbox.PadBox;

@Getter
public class PadBoxResponseDto {
    private Long id;
    private Long boxId;
    private Double latitude;    // 위도
    private Double longitude;   // 경도
    private String address;     // 주소
    private String name;        // 이름
    private int padAmount;      // 남은 수량
    private Double temperature; // 온도
    private Double humidity;    // 습도

    public PadBoxResponseDto(PadBox entity) {
        this.id = entity.getId();
        this.boxId = entity.getBoxId();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.name = entity.getName();
        this.padAmount = entity.getPadAmount();
        this.temperature = entity.getTemperature();
        this.humidity = entity.getHumidity();
    }
}
