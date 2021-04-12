package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "UserSaveRequest(회원가입 정보, 테스트용)", description = "회원가입에 대한 요청입니다. 테스트용입니다.")
@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    @ApiModelProperty("이메일 주소")
    private String email;
    @ApiModelProperty("비밀번호")
    private String password;

    @Builder
    public UserSaveRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
