package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.constants.Constants;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.check_in.CheckIn;
import rocketseat.com.passin.domain.check_in.exceptions.CheckInAlreadyDoneException;
import rocketseat.com.passin.repositories.CheckInRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public void registeredCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());
        CheckIn newCheckIn = buildCheckInEntity(attendee);
        this.checkInRepository.save(newCheckIn);
    }

    private static CheckIn buildCheckInEntity(Attendee attendee) {
        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());
        return newCheckIn;
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckedIn = getCheckIn(attendeeId);
        if(isCheckedIn.isPresent()) throw new CheckInAlreadyDoneException(Constants.CHECK_IN_ALREADY_DONE);
    }

    private Optional<CheckIn> getCheckIn(String attendeeId) {
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }

}
