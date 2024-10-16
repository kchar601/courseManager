package webapp.courseManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("webapp")
@EntityScan("webapp")
@EnableJpaRepositories("webapp") // Explicitly enabling JPA repositories

public class CourseManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CourseManagerApplication.class, args);
	}
}
