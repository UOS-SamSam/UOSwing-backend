package uos.samsam.wing;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JpaConfig
 * JPA를 설정하기 위한 클래스입니다.
 */
@Configuration
@EnableJpaAuditing  //JPA Auditing 활성화
public class JpaConfig {
}
