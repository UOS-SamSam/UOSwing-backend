package uos.samsam.wing.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * NoticeRepository
 * 공지를 저장하는 데 필요한 메소드가 정의된 인터페이스입니다.
 * Spring Data JPA 스펙에 맞춰 작성되었습니다.
 *
 * JpaRepository<>를 상속받습니다.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    /**
     * 등록되어 있는 공지를 작성일 역순으로 출력합니다.
     * @return 작성일 역순으로 정렬된 Notice 리스트
     */
    @Query("SELECT n FROM Notice n ORDER BY n.id DESC")
    List<Notice> findAllDesc();
}
