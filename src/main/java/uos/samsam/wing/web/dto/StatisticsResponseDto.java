package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * StatisticsResponseDto
 * 통계 조회 api의 응답 DTO입니다.
 */
@ApiModel(value = "StatisticsResponse(통계 조회 정보)", description = "통계 조회에 대한 응답입니다.")
@Getter
@NoArgsConstructor
public class StatisticsResponseDto {

    @ApiModelProperty(value = "생리대함 id")
    private Long padBoxId;
    @ApiModelProperty(value = "생리대함 이름")
    private String padBoxName;
    @ApiModelProperty(value = "기간동안 소모된 생리대 수량")
    private Integer amount;

    @Builder
    public StatisticsResponseDto(Long padBoxId, String padBoxName, Integer amount) {
        this.padBoxId = padBoxId;
        this.padBoxName = padBoxName;
        this.amount = amount;
    }
}
