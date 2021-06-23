package uos.samsam.wing.domain.padboxlog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PadBoxLogRepository
 * 생리대함 로그를 저장하는 데 필요한 메소드가 정의된 인터페이스입니다.
 * Spring Data JPA 스펙에 맞춰 작성되었습니다.
 *
 * JpaRepository<>를 상속받습니다.
 */
public interface PadBoxLogRepository extends JpaRepository<PadBoxLog, Long> {
}
