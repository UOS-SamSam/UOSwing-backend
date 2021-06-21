package uos.samsam.wing.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * BastTimeEntity
 * 생성 시간을 자동으로 추가할 수 있도록 지원하는 클래스입니다.
 * EntityListeners를 활용했습니다.
 * BaseTimeEntity를 상속받으면 해당 엔티티의 생성 시간이 자동으로 기록됩니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;  //엔티티 생성일
}
