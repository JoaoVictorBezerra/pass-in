package rocketseat.com.passin.dtos.event;

import jakarta.validation.constraints.*;

public record CreateEventRequestDTO(
        @NotBlank(message = "Title must not be empty!")
        @Size(min = 3, max = 50, message = "Title must be between 3-50 characters")
        String title,
        @NotBlank(message = "Details must not be empty!")
        @Size(min = 3, max = 50, message = "Details must be between 3-50 characters")
        String details,
        @Positive(message = "Maximum Attendees must be positive value")
        Integer maximumAttendees
) {
}
