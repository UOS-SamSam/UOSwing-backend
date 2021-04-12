package uos.samsam.wing.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "UserLoginRequest(로그인 정보)", description = "관리자 로그인에 대한 요청입니다.")
@Getter
@NoArgsConstructor
public class UserLoginRequestDto {

    @ApiModelProperty("이메일 주소")
    private String email;
    @ApiModelProperty("비밀번호")
    private String password;

    @Builder
    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
