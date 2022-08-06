package appointmentsService.repository;

import appointmentsService.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Integer> {
}
