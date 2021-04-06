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

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long join(UserSaveRequestDto requestDto) {
        String encodedKey = passwordEncoder.encode(requestDto.getPassword());
        return userRepository.save(User.builder()
                .email(requestDto.getEmail())
                .password(encodedKey)
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build()).getId();
    }

/*
    @Transactional
    public void deleteAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 어드민이 없습니다. id=" + id));

        userRepository.delete(user);
    }
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
