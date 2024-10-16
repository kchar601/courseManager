package webapp.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import webapp.enrollment.Enrollment;
import webapp.exception.EmailInUseException;
import webapp.exception.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Optional<User> getUserById(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("A user with that Id cannot be found");
		}
		return userOptional;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void createUser(User user) {
		// Check for existing email before adding new user
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("A user with that email already exists.");
		}
		userRepository.save(user);
	}

	public void deleteUser(Long userId) {
		// Check if user exists in enrollment before deleting
		Optional<Enrollment> recordOptional = userRepository.checkEnrollmentByUserId(userId);
		if (recordOptional.isPresent()) {
			throw new IllegalStateException("Cannot delete user. That user is currently enrolled in a course.");
		}
		userRepository.deleteById(userId);
	}

	public void updateUser(Long userId, String fName, String lName, String email) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user with id: " + userId + " does not exist"));
		if (fName != null && fName.length() > 0 && !Objects.equals(user.getFName(), fName)) {
			user.setFName(fName);
		}
		if (lName != null && lName.length() > 0 && !Objects.equals(user.getLName(), lName)) {
			user.setLName(lName);
		}
		// Check for existing email before allowing email change
		if (email != null && email.length() > 0 && email.contains("@") && !Objects.equals(user.getEmail(), email)) {
			Optional<User> studentOptional = userRepository.findUserByEmail(email);
			if (studentOptional.isPresent()) {
				throw new EmailInUseException("email is already in use by another user");
			}
			user.setEmail(email);
		}
		userRepository.save(user);
	}

	public List<String> getAllUsersByCourseId(Long courseId) {
		List<User> userList = userRepository.findAllByCourseId(courseId);
		List<String> nameList = userList.stream().map(user -> user.getFName() + " " + user.getLName())
				.collect(Collectors.toList());
		return nameList;
	}

	public Optional<User> getUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findUserByEmail(email);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("A user with that email could not be found");
		}
		return userOptional;
	}

}
