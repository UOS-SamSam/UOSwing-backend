package uos.samsam.wing.domain.admin;

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
    AdminRepository administratorRepository;

    @AfterEach
    void cleanup() {
        administratorRepository.deleteAll();
    }

    @Test
    void admin_저장_조회() {
        //given
        String key = "테스트 키";
        administratorRepository.save(Admin.builder()
                .key(key)
                .build());

        //when
        List<Admin> administratorList = administratorRepository.findAll();

        //then
        Admin administrator = administratorList.get(0);
        assertThat(administrator.getKey()).isEqualTo(key);
    }

    @Test
    void admin_키로_찾기() {
        //given
        String key = "테스트 키";
        administratorRepository.save(Admin.builder()
            .key(key)
            .build());

        //when
        Admin admin = administratorRepository.findByKey(key);

        //then
        assertThat(admin.getKey()).isEqualTo(key);
    }
}