package uos.samsam.wing.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ReportRepository
 * 신고를 저장하는 데 필요한 메소드가 정의된 인터페이스입니다.
 * Spring Data JPA 스펙에 맞춰 작성되었습니다.
 *
 * JpaRepository<>를 상속받습니다.
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAll();
}
