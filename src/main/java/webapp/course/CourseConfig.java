//package webapp.course;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import webapp.user.User;
//import webapp.user.UserRepository;
//
//@Configuration
//public class CourseConfig {
//
//	@Bean(name = "CourseConfig")
//	@Order(2)
//	CommandLineRunner courseConfig(CourseRepository courseRepository, UserRepository userRepository) {
//		return args -> {
//			User teacher = userRepository.findById(1L)
//					.orElseThrow(() -> new NoSuchElementException("User with id: " + 1L + " not found"));
//			Course math = new Course("Algebra 101", "Mathematics", 110, teacher);
//			Course science = new Course("Biology 203", "Science", 200, teacher);
//
//			courseRepository.saveAll(List.of(math, science));
//		};
//	}
//}
