___
Implementing functional requirements according to events.

## Requirements
### Functional Requirements
- [x] The organizer must be able to register a new event.
- [x] The organizer must be able to view data from an event.
- [x] The organizer must be able to view the list of participants.

### Creating organizator's functionality
- [x] Create `EventService`
	- [x] List of events.
	- [x] Create an event.
- [x] Create endpoints
	- [x] `GET` /events/{eventId}
	- [x] `POST` /events
- [x] Add Exception Handlers
- [x] Create `AttendeeService`
	- [x] List attendees from an specific event
- [x] Create endpoint
	- [x] `GET` /events/{eventId}/attendees
