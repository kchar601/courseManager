package webapp.course;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import webapp.enrollment.Enrollment;
import webapp.exception.CourseNotFoundException;
import webapp.exception.RoomTakenException;
import webapp.exception.StudentEnrolledInCourseException;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Optional<Course> getCourseById(Long courseId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (courseOptional.isEmpty()) {
			throw new CourseNotFoundException("Course with id: " + courseId + " could not be found");
		}
		return courseOptional;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public void createCourse(Course course) {
		Optional<Course> courseOptional = courseRepository.findCourseByRoom(course.getRoom());
		if (courseOptional.isPresent()) {
			throw new RoomTakenException("A course is already using room " + course.getRoom()
					+ ". Please try again with a new room number.");
		}
		courseRepository.save(course);
	}

	public void deleteCourse(Long courseId) {
		Optional<Enrollment> recordOptional = courseRepository.findEnrollmentByCourseId(courseId);
		if (recordOptional.isPresent()) {
			throw new StudentEnrolledInCourseException("There are currently students enrolled in course: " + courseId
					+ ". Please remove the enrollment records before deleting course.");
		}
		courseRepository.deleteById(courseId);
	}

	public void updateCourse(Long courseId, String title, String subject, Integer room) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("course with id: " + courseId + " does not exist"));
		if (title != null && title.length() > 0 && !Objects.equals(course.getTitle(), title)) {
			course.setTitle(title);
		}
		if (subject != null && subject.length() > 0 && !Objects.equals(course.getSubject(), subject)) {
			course.setSubject(subject);
		}
		// Check for existing email before allowing email change
		if (room != null && room > 0 && !Objects.equals(course.getRoom(), room)) {
			Optional<Course> courseOptional = courseRepository.findCourseByRoom(room);
			if (courseOptional.isPresent()) {
				throw new RoomTakenException("room is already in use by another course");
			}
			course.setRoom(room);
		}
		courseRepository.save(course);
	}

	public List<Course> getAllCoursesByUserId(Long userId) {
		List<Course> courseList = courseRepository.findAllByUserId(userId);
		return courseList;
	}

	public List<Course> getAllCoursesByTeacherId(Long userId) {
		return courseRepository.findAllByTeacherId(userId);
	}

}
