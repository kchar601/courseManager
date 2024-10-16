//package webapp.enrollment;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import jakarta.persistence.EntityNotFoundException;
//import webapp.course.Course;
//import webapp.course.CourseRepository;
//import webapp.user.User;
//import webapp.user.UserRepository;
//
//@Configuration
//@DependsOn("CourseConfig") // This ensures CourseConfig loads before EnrollmentConfig
//public class EnrollmentConfig {
//
//	@Bean
//	CommandLineRunner enrollmentConfigAlt(EnrollmentRepository enrollmentRepository, UserRepository userRepository,
//			CourseRepository courseRepository) {
//		return args -> {
//			User alex = userRepository.findById(2L)
//					.orElseThrow(() -> new EntityNotFoundException("User with ID " + 2L + " not found"));
//			Course math = courseRepository.findById(1L)
//					.orElseThrow(() -> new EntityNotFoundException("Course with ID " + 1L + " not found"));
//			Course science = courseRepository.findById(2L)
//					.orElseThrow(() -> new EntityNotFoundException("Course with ID " + 2L + " not found"));
//			Enrollment alexMath = new Enrollment(alex, math);
//			Enrollment alexScience = new Enrollment(alex, science);
//
//			enrollmentRepository.saveAll(List.of(alexMath, alexScience));
//		};
//	}
//}
