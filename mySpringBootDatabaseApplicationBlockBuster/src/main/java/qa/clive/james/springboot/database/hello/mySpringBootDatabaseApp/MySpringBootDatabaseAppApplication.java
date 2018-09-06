package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MySpringBootDatabaseAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MySpringBootDatabaseAppApplication.class,  args);
	}

}
