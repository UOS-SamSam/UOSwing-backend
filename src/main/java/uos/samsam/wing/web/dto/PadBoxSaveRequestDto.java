package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;

/**
 * PadBoxSaveRequestDto
 * 생리대함 저장 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "PadBoxSaveRequest(생리대함 저장 정보)", description = "생리대함 저장에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class PadBoxSaveRequestDto {

    @ApiModelProperty(value = "위도")
    private Double latitude;
    @ApiModelProperty(value = "경도")
    private Double longitude;
    @ApiModelProperty(value = "주소")
    private String address;
    @ApiModelProperty(value = "생리대함 이름")
    private String name;
    @ApiModelProperty(value = "남은 생리대 수량")
    private Integer padAmount;
    @ApiModelProperty(value = "현재 온도")
    private Double temperature;
    @ApiModelProperty(value = "현재 습도")
    private Double humidity;

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
