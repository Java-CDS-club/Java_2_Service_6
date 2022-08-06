package appointmentsService.repository;

import appointmentsService.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Integer> {
}
