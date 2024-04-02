package rocketseat.com.passin.dtos.event;

import rocketseat.com.passin.domain.event.Event;

public class EventResponseDTO {
    EventDetailDTO eventDetailDTO;

    public EventResponseDTO(Event event, Integer maximumAttendees) {
        this.eventDetailDTO = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), maximumAttendees);
    }
}
