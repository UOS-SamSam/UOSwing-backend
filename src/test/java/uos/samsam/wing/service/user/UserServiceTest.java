package uos.samsam.wing.service.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uos.samsam.wing.auth.JwtTokenProvider;
import uos.samsam.wing.domain.user.User;
import uos.samsam.wing.domain.user.UserRepository;
import uos.samsam.wing.web.dto.UserLoginRequestDto;
import uos.samsam.wing.web.dto.UserSaveRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired JwtTokenProvider jwtTokenProvider;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void admin_생성() {
        //given
        String email = "samsam-uos@gmail.com";
        String key = "thisistestkey";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email)
                .password(key)
                .build();

        //when
        userService.join(requestDto);
        List<User> userList = userRepository.findAll();

        //then
        assertThat(userList.size()).isGreaterThan(0);
        assertThat(userList.get(0).getEmail()).isEqualTo(email);
        assertThat(passwordEncoder.matches(key, userList.get(0).getPassword())).isTrue();
    }

    @Test
    void 로그인() {
        //given
        String email = "samsam-uos@gmail.com";
        String key = "thisistestkey";
        userService.join(UserSaveRequestDto.builder()
                .email(email)
                .password(key)
                .build());
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                                                            .email(email)
                                                            .password(key)
                                                            .build();
        //when
        String token = userService.login(requestDto);

        //then
        System.out.println("token = " + token);
        assertThat(token).isNotNull();
    }

    @Test
    void 로그인_실패() {
        //given
        String email = "samsam-uos@gmail.com";
        String key = "thisistestkey";
        String wrongKey = "thisiswrongkey";
        userService.join(UserSaveRequestDto.builder()
                .email(email)
                .password(key)
                .build());
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .email(email)
                .password(wrongKey)
                .build();
        //when, then
        assertThrows(IllegalArgumentException.class,
                () -> userService.login(requestDto));
    }
}