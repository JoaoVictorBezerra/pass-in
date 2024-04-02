package rocketseat.com.passin.dtos.event;

public record CreateEventRequestDTO(
        String title,
        String details,
        Integer maximumAttendees
) {
}
