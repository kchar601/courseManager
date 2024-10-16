package webapp.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import webapp.enrollment.Enrollment;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Check for existing email before adding new user
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findUserByEmail(String email);

	// Check if user exists in enrollment before deleting
	@Query("SELECT e FROM Enrollment e WHERE e.user.id = ?1")
	Optional<Enrollment> checkEnrollmentByUserId(Long userId);

	// Find all users who are associated with a specific class
	@Query("SELECT u FROM User u JOIN Enrollment e ON u = e.user WHERE e.course.id = ?1")
	List<User> findAllByCourseId(Long courseId);

}
