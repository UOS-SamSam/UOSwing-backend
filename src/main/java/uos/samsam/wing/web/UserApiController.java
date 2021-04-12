package uos.samsam.wing.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.samsam.wing.service.user.UserService;
import uos.samsam.wing.web.dto.UserLoginRequestDto;
import uos.samsam.wing.web.dto.UserSaveRequestDto;

@Api(value = "UserApiController v1")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@RestController
public class UserApiController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "관리자로 로그인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "존재하지 않는 ID거나 PW가 틀림"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @ApiOperation(value = "회원가입(테스트용)", notes = "새로운 관리자 계정을 생성합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 생성 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 500, message = "서버 내부 에러")
    })
    @PostMapping("/join")
    public Long join(@RequestBody UserSaveRequestDto requestDto) {
        return userService.join(requestDto);
    }
}
