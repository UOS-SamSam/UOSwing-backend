package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PadBoxUpdateRequestDto {
    private Double latitude;    // 위도
    private Double longitude;   // 경도
    private String address;     // 주소
    private String name;        // 이름

    @Builder
    public PadBoxUpdateRequestDto(Double latitude, Double longitude, String address, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
    }
}
