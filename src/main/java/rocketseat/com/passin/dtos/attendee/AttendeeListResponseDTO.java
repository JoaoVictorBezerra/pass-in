package rocketseat.com.passin.dtos.attendee;

import lombok.Getter;

import java.util.List;

public record AttendeeListResponseDTO(
        List<AttendeeDetailDTO> attendees
) {
}
