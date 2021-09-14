package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PadBoxUpdateStateRequestDto
 * 생리대함 상태 갱신 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "PadBoxUpdateStateRequest(생리대함 상태 갱신 정보, 아두이노 전용)", description = "생리대함 상태 갱신에 대한 요청입니다. 아두이노 전용입니다.")
@Getter
@NoArgsConstructor
public class PadBoxUpdateStateRequestDto {

    @ApiModelProperty(value = "남은 생리대 수량")
    private Integer padAmount;

    @Deprecated
    @ApiModelProperty(value = "현재 온도")
    private Double temperature;

    @Deprecated
    @ApiModelProperty(value = "현재 습도")
    private Double humidity;

    @Deprecated
    @Builder
    public PadBoxUpdateStateRequestDto(Integer padAmount, Double temperature, Double humidity) {
        this.padAmount = padAmount;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Builder
    public PadBoxUpdateStateRequestDto(Integer padAmount) {
        this.padAmount = padAmount;
    }
}
