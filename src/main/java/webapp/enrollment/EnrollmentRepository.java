package webapp.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import webapp.course.Course;
import webapp.user.User;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	@Query("SELECT e FROM Enrollment e WHERE e.user = ?1 AND e.course = ?2")
	Optional<Enrollment> checkForEnrollment(User student, Course course);

	@Query("SELECT e FROM Enrollment e WHERE e.user.userId = ?1")
	List<Enrollment> findAllByUserId(Long userId);

}
