package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.padbox.PadBox;

@ApiModel(value = "PadBoxListResponse(생리대함 전체 조회 정보)", description = "생리대함 전체 조회에 대한 응답입니다.")
@Getter
@NoArgsConstructor
public class PadBoxListResponseDto {

    @ApiModelProperty(value = "생리대함 id")
    private Long id;
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
    @ApiModelProperty(value = "신고 유무(신고가 있으면 true)")
    private Boolean isReported;

    public PadBoxListResponseDto(PadBox entity) {
        this.id = entity.getId();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.name = entity.getName();
        this.padAmount = entity.getPadAmount();
        this.temperature = entity.getTemperature();
        this.humidity = entity.getHumidity();
        this.isReported = !entity.getReportList().isEmpty();
    }
}
