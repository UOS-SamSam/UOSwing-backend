package uos.samsam.wing.service.admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.domain.admin.Admin;
import uos.samsam.wing.domain.admin.AdminRepository;
import uos.samsam.wing.web.dto.AdminLoginRequestDto;
import uos.samsam.wing.web.dto.AdminSaveRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdminServiceTest {

    @Autowired AdminRepository adminRepository;
    @Autowired AdminService adminService;
    @Autowired PasswordEncoder passwordEncoder;

    @AfterEach
    void cleanup() {
        adminRepository.deleteAll();
    }

    @Test
    void admin_생성() {
        //given
        String key = "thisistestkey";
        AdminSaveRequestDto requestDto = AdminSaveRequestDto.builder()
                .key(key)
                .build();

        //when
        adminService.createAdmin(requestDto);
        List<Admin> adminList = adminRepository.findAll();

        //then
        assertThat(adminList.size()).isGreaterThan(0);
        assertThat(passwordEncoder.matches(key, adminList.get(0).getKey())).isTrue();
    }

    @Test
    void 로그인() {
        //given
        String key = "thisistestkey";
        adminService.createAdmin(AdminSaveRequestDto.builder()
                                                    .key(key)
                                                    .build());
        AdminLoginRequestDto requestDto = AdminLoginRequestDto.builder()
                                                                .key(key)
                                                                .build();
        //when
        Boolean ok = adminService.login(requestDto);

        //then
        assertThat(ok).isTrue();
    }

    @Test
    void 로그인_실패() {
        //given
        String key = "thisistestkey";
        String wrongKey = "thisiswrongkey";
        adminService.createAdmin(AdminSaveRequestDto.builder()
                .key(key)
                .build());
        AdminLoginRequestDto requestDto = AdminLoginRequestDto.builder()
                .key(wrongKey)
                .build();
        //when
        Boolean ok = adminService.login(requestDto);

        //then
        assertThat(ok).isFalse();
    }
}