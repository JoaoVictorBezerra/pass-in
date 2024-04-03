package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.event.exceptions.EventFullException;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.dtos.attendee.AttendeeIdDTO;
import rocketseat.com.passin.dtos.attendee.CreateAttendeeRequestDTO;
import rocketseat.com.passin.dtos.event.CreateEventRequestDTO;
import rocketseat.com.passin.dtos.event.EventIdDTO;
import rocketseat.com.passin.dtos.event.EventResponseDTO;
import rocketseat.com.passin.repositories.EventRepository;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetails(String eventId) {
        Event event = this.getEventById(eventId);
         List<Attendee> attendeeList = attendeeService.getAllAttendeesFromEvents(eventId);
         return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(CreateEventRequestDTO body) {
        Event newEvent = new Event();
        newEvent.setTitle(body.title());
        newEvent.setDetails(body.details());
        newEvent.setSlug(createSlug(body.title()));
        newEvent.setMaximumAttendees(body.maximumAttendees());

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String title) {
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        return normalized
                .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}","")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, CreateAttendeeRequestDTO attendeeDTO) {
        this.attendeeService.verifyAttendeeRegister(attendeeDTO.email(), eventId);
        Event event = this.getEventById(eventId);

        List<Attendee> attendeeList = attendeeService.getAllAttendeesFromEvents(eventId);

        if(event.getMaximumAttendees() <= attendeeList.size()) {
            throw new EventFullException("Event slots are already full.");
        }

        Attendee newAttendee = new Attendee();

        newAttendee.setName(attendeeDTO.name());
        newAttendee.setEmail(attendeeDTO.email());
        newAttendee.setEvent(event);
        newAttendee.setCreatedAt(LocalDateTime.now());

        this.attendeeService.registerAttendee(newAttendee);

        return new AttendeeIdDTO(newAttendee.getId());
    }

    private Event getEventById(String eventId) {
       return this.eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException("Event not found with ID: " + eventId));
    }
}
