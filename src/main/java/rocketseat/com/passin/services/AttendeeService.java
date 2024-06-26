package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.constants.Constants;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeAlreadyRegisteredException;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import rocketseat.com.passin.domain.check_in.CheckIn;
import rocketseat.com.passin.dtos.attendee.AttendeeBadgeResponseDTO;
import rocketseat.com.passin.dtos.attendee.AttendeeDetailDTO;
import rocketseat.com.passin.dtos.attendee.AttendeeListResponseDTO;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.CheckInRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvents(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeeListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvents(eventId);

        List<AttendeeDetailDTO> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkInAt = checkIn.map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetailDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkInAt);
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetailsList);
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public void verifyAttendeeRegister(String email, String eventId) {
        Optional<Attendee> isRegisteredAttendee = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isRegisteredAttendee.isPresent()) {
            throw new AttendeeAlreadyRegisteredException(Constants.ATTENDEE_ALREADY_REGISTERED + email);
        }
    }

    Attendee getAttendeeById(String attendeeId) {
        return this.attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException(Constants.ATTENDEE_NOT_FOUND + attendeeId));
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.getAttendeeById(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/checkin").buildAndExpand(attendee.getEvent().getId()).toUri().toString();

        return new AttendeeBadgeResponseDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());
    }



    public void checkInAttendee(String attendeeId) {
        Attendee attendee = this.getAttendeeById(attendeeId);
        this.checkInService.registeredCheckIn(attendee);
    }
}
