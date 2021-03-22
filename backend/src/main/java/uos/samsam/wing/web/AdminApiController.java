package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uos.samsam.wing.service.admin.AdminService;
import uos.samsam.wing.web.dto.AdminLoginRequestDto;

@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/api/v1/login")
    public Boolean login(@RequestBody AdminLoginRequestDto requestDto) {
        return adminService.login(requestDto);
    }
}
