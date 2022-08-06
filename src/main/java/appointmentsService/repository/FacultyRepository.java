package appointmentsService.repository;

import appointmentsService.model.Course;
import appointmentsService.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository <Faculty, Integer> {

}
