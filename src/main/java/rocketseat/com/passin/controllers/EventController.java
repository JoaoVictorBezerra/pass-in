package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dtos.attendee.AttendeeListResponseDTO;
import rocketseat.com.passin.dtos.event.CreateEventRequestDTO;
import rocketseat.com.passin.dtos.event.EventIdDTO;
import rocketseat.com.passin.dtos.event.EventResponseDTO;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId) {
        EventResponseDTO event = eventService.getEventDetails(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody CreateEventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{eventId}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String eventId) {
        AttendeeListResponseDTO event = attendeeService.getEventsAttendee(eventId);
        return ResponseEntity.ok(event);
    }
}
