package webapp.enrollment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import webapp.course.Course;
import webapp.user.User;

@Entity
public class Enrollment {

	@Id
	@SequenceGenerator(name = "enrollment_sequence", sequenceName = "enrollment_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_sequence")
	private Long enrollmentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable = false)
	@JsonManagedReference
	private Course course;

	public Enrollment() {
	}

	public Enrollment(Long enrollmentId, User user, Course course) {
		super();
		this.enrollmentId = enrollmentId;
		this.user = user;
		this.course = course;
	}

	public Enrollment(User user, Course course) {
		super();
		this.user = user;
		this.course = course;
	}

	public Long getEnrollmentId() {
		return enrollmentId;
	}

	@JsonIgnore
	public Long getUserId() {
		return user.getUserId();
	}

	@JsonIgnore
	public Long getCourseId() {
		return course.getCourseId();
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", userId=" + user.toString() + ", courseId="
				+ course.toString() + "]";
	}

}
