package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PadBoxUpdateRequestDto
 * 생리대함 수정 api로 전달되는 http request DTO입니다.
 */
@ApiModel(value = "PadBoxUpdateRequest(생리대함 수정 정보)", description = "생리대함 수정에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class PadBoxUpdateRequestDto {

    @ApiModelProperty(value = "수정된 위도")
    private Double latitude;
    @ApiModelProperty(value = "수정된 경도")
    private Double longitude;
    @ApiModelProperty(value = "수정된 주소")
    private String address;
    @ApiModelProperty(value = "수정된 생리대함 이름")
    private String name;

    @Builder
    public PadBoxUpdateRequestDto(Double latitude, Double longitude, String address, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
    }
}
