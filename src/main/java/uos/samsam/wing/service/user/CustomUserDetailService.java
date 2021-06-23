package uos.samsam.wing.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uos.samsam.wing.domain.user.UserRepository;

/**
 * CustomUserDetailService
 * Spring Security 스펙에 맞춰 작성된 유저 서비스 클래스입니다.
 * UserDetailService를 구현합니다.
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 유저 이름으로 유저 조회
     * @param username 찾고자 하는 유저 이름
     * @return 유저 정보
     * @throws UsernameNotFoundException 해당하는 유저 이름을 찾을 수 없음.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. username="+username));
    }
}
