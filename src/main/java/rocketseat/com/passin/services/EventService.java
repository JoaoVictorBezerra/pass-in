package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.dtos.event.CreateEventRequestDTO;
import rocketseat.com.passin.dtos.event.EventIdDTO;
import rocketseat.com.passin.dtos.event.EventResponseDTO;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetails(String eventId) {
         Event event = this.eventRepository.findById(eventId)
                 .orElseThrow(()-> new EventNotFoundException("Event not found with ID: " + eventId));
         List<Attendee> attendeeList = attendeeRepository.findByEventId(eventId);
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
}
