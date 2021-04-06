package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.samsam.wing.service.user.UserService;
import uos.samsam.wing.web.dto.UserLoginRequestDto;
import uos.samsam.wing.web.dto.UserSaveRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @PostMapping("/join")
    public Long join(@RequestBody UserSaveRequestDto requestDto) {
        return userService.join(requestDto);
    }
}
