package webapp.course;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.exception.UserNotFoundException;
import webapp.user.User;
import webapp.user.UserService;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

	private final CourseService courseService;
	private final UserService userService;

	public CourseController(CourseService courseService, UserService userService) {
		super();
		this.courseService = courseService;
		this.userService = userService;
	}

	@GetMapping(path = "{course_id}")
	public Optional<Course> getCourseById(@PathVariable("course_id") Long courseId) {
		return courseService.getCourseById(courseId);
	}

	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping(path = "/user/{user_id}")
	public List<Course> getAllCoursesByUserId(@PathVariable("user_id") Long userId) {
		return courseService.getAllCoursesByUserId(userId);
	}

	@PostMapping
	public ResponseEntity<Void> createCourse(@RequestBody Course course, @RequestParam Long userId) {
		User teacher = userService.getUserById(userId)
				.orElseThrow(() -> new UserNotFoundException("Teacher not found with ID: " + userId));

		course.setTeacher(teacher);
		courseService.createCourse(course);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{courseId}")
	public void deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
	}

	@PutMapping(path = "{courseId}")
	public void updateCourse(@PathVariable Long courseId, @RequestBody Map<String, Object> updates) {
		String title = (String) updates.get("title");
		String subject = (String) updates.get("subject");
		Integer room = (Integer) updates.get("room");

		courseService.updateCourse(courseId, title, subject, room);
	}

	@GetMapping(path = "/teacher/{user_id}")
	public List<Course> getAllCoursesByTeacherId(@PathVariable("user_id") Long userId) {
		return courseService.getAllCoursesByTeacherId(userId);
	}

}
