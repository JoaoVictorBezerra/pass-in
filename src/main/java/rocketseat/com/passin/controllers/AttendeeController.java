package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.constants.Constants;
import rocketseat.com.passin.dtos.DefaultDTO;
import rocketseat.com.passin.dtos.attendee.AttendeeBadgeResponseDTO;
import rocketseat.com.passin.services.AttendeeService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO attendeeBadge = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok().body(attendeeBadge);
    }

    @PostMapping("/{attendeeId}/checkin")
    public ResponseEntity<DefaultDTO> registerAttendeeCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        this.attendeeService.checkInAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

        DefaultDTO response = new DefaultDTO(Constants.CHECK_IN_SUCCESSFUL_CREATED, HttpStatus.CREATED.value());

        return ResponseEntity.created(uri).body(response);
    }
}
