package webapp.exception;

public class StudentEnrolledInCourseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentEnrolledInCourseException(String message) {
		super(message);
	}

}
