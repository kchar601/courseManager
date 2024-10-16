package webapp.course;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import webapp.user.User;

@Entity
@Table
public class Course {

	@Id
	@SequenceGenerator(name = "enrollment_sequence", sequenceName = "enrollment_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_sequence")
	private Long courseId;

	private String title;
	private String subject;
	private Integer room;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User teacher;

	public Course() {
	}

	public Course(Long courseId, String title, String subject, Integer room, User teacher) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.subject = subject;
		this.room = room;
		this.teacher = teacher;
	}

	public Course(String title, String subject, Integer room, User teacher) {
		super();
		this.title = title;
		this.subject = subject;
		this.room = room;
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Long getCourseId() {
		return courseId;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	@JsonIgnore
	public Long getTeacherId() {
		return teacher.getUserId();
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + ", subject=" + subject + ", room=" + room
				+ ", teacherId=" + teacher.getUserId() + "]";
	}

}
