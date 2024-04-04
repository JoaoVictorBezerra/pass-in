package rocketseat.com.passin.dtos.attendee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAttendeeRequestDTO(
        @NotBlank(message = "Name must not be blank!")
        @Size(min = 3, max = 50, message = "Name must be between 3-50 characters")
        String name,
        @Email(message = "Please, enter a valid email")
        @NotBlank(message = "E-mail must not be blank!")
        String email

) {
}
