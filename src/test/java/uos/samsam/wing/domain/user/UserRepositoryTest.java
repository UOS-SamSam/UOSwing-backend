package uos.samsam.wing.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserRepositoryTest
 * UserRepository를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository administratorRepository;

    @AfterEach
    void cleanup() {
        administratorRepository.deleteAll();
    }

    @Test
    void admin_저장_조회() {
        //given
        String email = "samsam-uos@gmail.com";
        String key = "테스트 키";
        administratorRepository.save(User.builder()
                .email(email)
                .password(key)
                .build());

        //when
        List<User> administratorList = administratorRepository.findAll();

        //then
        User administrator = administratorList.get(0);
        assertThat(administrator.getPassword()).isEqualTo(key);
    }

    @Test
    void admin_키로_찾기() {
        //given
        String email = "samsam-uos@gmail.com";
        String key = "테스트 키";
        administratorRepository.save(User.builder()
                .email(email)
                .password(key)
                .build());

        //when
        User user = administratorRepository.findByEmail(email).get();

        //then
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(key);
    }
}