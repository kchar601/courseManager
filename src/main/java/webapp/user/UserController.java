package webapp.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping(path = "{userId}")
	public Optional<User> getUser(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}

	@PostMapping(path = "login")
	public Optional<User> getUserByEmail(@RequestBody Map<String, Object> payload) {
		String email = payload.get("email").toString();

		System.out.println("Email: " + email);

		return userService.getUserByEmail(email);
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(path = "course/{courseId}")
	public List<String> getAllUsersByCourseId(@PathVariable Long courseId) {
		return userService.getAllUsersByCourseId(courseId);
	}

	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}

	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}

	@PutMapping(path = "{userId}")
	public void updateUser(@PathVariable Long userId, @RequestParam(required = false) String fName,
			@RequestParam(required = false) String lName, @RequestParam(required = false) String email) {
		userService.updateUser(userId, fName, lName, email);
	}
}
