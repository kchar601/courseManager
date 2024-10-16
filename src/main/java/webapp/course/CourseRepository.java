package webapp.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webapp.enrollment.Enrollment;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	// Query to find course by room number
	@Query("SELECT c FROM Course c WHERE c.room = ?1")
	Optional<Course> findCourseByRoom(Integer room);

	// Query to find enrollment records for a specific course
	@Query("SELECT e FROM Enrollment e WHERE e.course.courseId = ?1")
	Optional<Enrollment> findEnrollmentByCourseId(Long courseId);

	// Query to find all courses a student is enrolled in
	@Query("SELECT c FROM Course c JOIN Enrollment e ON c.courseId = e.course.courseId WHERE e.user.userId = ?1")
	List<Course> findAllByUserId(@Param("userId") Long userId);

	@Query("SELECT c FROM Course c WHERE c.teacher.userId = :userId")
	List<Course> findAllByTeacherId(@Param("userId") Long userId);

}
