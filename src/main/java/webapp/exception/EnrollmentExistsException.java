package webapp.exception;

public class EnrollmentExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnrollmentExistsException(String message) {
		super(message);
	}
}
