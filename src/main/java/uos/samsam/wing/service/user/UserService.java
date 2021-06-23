package uos.samsam.wing.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.auth.JwtTokenProvider;
import uos.samsam.wing.domain.user.User;
import uos.samsam.wing.domain.user.UserRepository;
import uos.samsam.wing.web.dto.UserLoginRequestDto;
import uos.samsam.wing.web.dto.UserSaveRequestDto;

import java.util.Collections;

/**
 * UserService
 * 유저에 대한 비즈니스 로직을 정의하는 서비스 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 유저 생성
     * @param requestDto http request dto
     * @return 생성된 유저의 id
     */
    @Deprecated
    @Transactional
    public Long join(UserSaveRequestDto requestDto) {
        String encodedKey = passwordEncoder.encode(requestDto.getPassword());
        return userRepository.save(User.builder()
                .email(requestDto.getEmail())
                .password(encodedKey)
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build()).getId();
    }

    /**
     * 유저 삭제
     * @param id 삭제하고자 하는 유저의 id
     * 현재 서비스에서는 사용을 권장하지 않는 함수입니다.
     */
    @Deprecated
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 어드민이 없습니다. id=" + id));

        userRepository.delete(user);
    }

    /**
     * 유저 로그인
     * @param requestDto 로그인 http request dto
     * @return 생성된 JWT 토큰
     */
    @Transactional
    public String login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. username=" + requestDto.getEmail()));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
       }

        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }
}
