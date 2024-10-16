package webapp.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import webapp.course.Course;
import webapp.course.CourseRepository;
import webapp.exception.EnrollmentExistsException;
import webapp.exception.EnrollmentNotFoundException;
import webapp.user.User;
import webapp.user.UserRepository;

@Service
public class EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;

	public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository,
			CourseRepository courseRepository) {
		this.enrollmentRepository = enrollmentRepository;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

	public List<Enrollment> getAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	public void createEnrollment(Enrollment enrollment) {
		User user = userRepository.findById(enrollment.getUserId())
				.orElseThrow(() -> new IllegalStateException("User not found"));
		Course course = courseRepository.findById(enrollment.getCourseId())
				.orElseThrow(() -> new IllegalStateException("Course not found"));

		Optional<Enrollment> enrollmentOptional = enrollmentRepository.checkForEnrollment(user, course);
		if (enrollmentOptional.isPresent()) {
			throw new EnrollmentExistsException("That enrollment already exists!");
		}

		enrollmentRepository.save(enrollment);
	}

	public void deleteEnrollment(Long enrollmentId) {
		Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
		if (enrollmentOptional.isEmpty()) {
			throw new EnrollmentNotFoundException("An enrollment with id: " + enrollmentId + " cannot be found.");
		}
		enrollmentRepository.deleteById(enrollmentId);
	}

	public List<Enrollment> getEnrollmentsByUserId(Long userId) {
		List<Enrollment> enrollmentList = enrollmentRepository.findAllByUserId(userId);
		if (enrollmentList.isEmpty()) {
			throw new EnrollmentNotFoundException("That user is not currently enrolled in any courses.");
		}
		return enrollmentList;
	}

	public Optional<Enrollment> findByUserAndCourse(User user, Course course) {
		Optional<Enrollment> enrollmentOptional = enrollmentRepository.checkForEnrollment(user, course);
		return enrollmentOptional;
	}

}
