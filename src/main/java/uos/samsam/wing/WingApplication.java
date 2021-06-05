package uos.samsam.wing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy	//AOP
@SpringBootApplication
public class WingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WingApplication.class, args);
	}

}
