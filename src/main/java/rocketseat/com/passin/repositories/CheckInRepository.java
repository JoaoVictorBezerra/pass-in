package rocketseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passin.domain.check_in.CheckIn;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
