package uos.samsam.wing;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class SecurityConfigTest {

    @Test
    void 의존관계_자동주입_확인() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SecurityConfig.class);
        PasswordEncoder bean = ac.getBean(PasswordEncoder.class);
        assertThat(bean).isInstanceOf(PasswordEncoder.class);
        System.out.println("bean = " + bean);
    }

}