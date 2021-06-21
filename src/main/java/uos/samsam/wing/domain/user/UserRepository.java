package uos.samsam.wing.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository
 * 유저를 저장하는 데 필요한 메소드가 정의된 인터페이스입니다.
 * Spring Data JPA 스펙에 맞춰 작성되었습니다.
 *
 * JpaRepository<>를 상속받습니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 주어진 이메일과 일치하는 이메일을 가진 유저를 찾습니다.
     * @param email 이메일
     * @return 주어진 이메일과 일치하는 정보를 가진 유저 (unique)
     */
    Optional<User> findByEmail(String email);
}
