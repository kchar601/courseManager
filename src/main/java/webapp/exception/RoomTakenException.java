package webapp.exception;

public class RoomTakenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoomTakenException(String message) {
		super(message);
	}
}
