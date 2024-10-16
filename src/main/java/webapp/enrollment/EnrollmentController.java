package webapp.enrollment;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.course.Course;
import webapp.course.CourseService;
import webapp.exception.CourseNotFoundException;
import webapp.exception.EnrollmentNotFoundException;
import webapp.exception.UserNotFoundException;
import webapp.user.User;
import webapp.user.UserService;

@RestController
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;
	private final UserService userService;
	private final CourseService courseService;

	public EnrollmentController(EnrollmentService enrollmentService, UserService userService,
			CourseService courseService) {
		this.enrollmentService = enrollmentService;
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping
	public List<Enrollment> getAllEnrollments() {
		return enrollmentService.getAllEnrollments();
	}

	@GetMapping(path = "user/{user_id}")
	public List<Enrollment> getEnrollmentsByUserId(@PathVariable("user_id") Long userId) {
		return enrollmentService.getEnrollmentsByUserId(userId);
	}

	@PostMapping
	public void createEnrollment(@RequestBody Map<String, Long> payload) {
		Long userId = payload.get("userId");
		Long courseId = payload.get("courseId");

		User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		Course course = courseService.getCourseById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		Enrollment enrollment = new Enrollment(user, course);

		enrollmentService.createEnrollment(enrollment);
	}

	@DeleteMapping
	public void deleteEnrollment(@RequestParam Long userId, @RequestParam Long courseId) {
		User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		Course course = courseService.getCourseById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		Enrollment enrollment = enrollmentService.findByUserAndCourse(user, course)
				.orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));

		enrollmentService.deleteEnrollment(enrollment.getEnrollmentId());
	}

}
