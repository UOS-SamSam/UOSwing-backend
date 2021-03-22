package uos.samsam.wing.domain.administrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdministratorRepositoryTest {

    @Autowired
    AdministratorRepository administratorRepository;

    @AfterEach
    void cleanup() {
        administratorRepository.deleteAll();
    }

    @Test
    void notice_저장_조회() {
        //given
        String key = "테스트 키";
        administratorRepository.save(Administrator.builder()
                .key(key)
                .build());

        //when
        List<Administrator> administratorList = administratorRepository.findAll();

        //then
        Administrator administrator = administratorList.get(0);
        assertThat(administrator.getKey()).isEqualTo(key);
    }

}