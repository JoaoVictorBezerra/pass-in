___

## Requirements
### Functional Requirements
- [x] The attendee must be able to register for an event
- [x] The attendee must be able to view their registration badge
- [x] The attendee must be able to check in at the event.

### Business Rule
- [x] The attendee should register for the event only once.
- [x] The attendee can only register for events with available spots.
- [x] The attendee can check in only once.

## Creating organizer's functionality
- [x] Implement new features on `AttendeeService`
	- [x] Registration of attendee for an event.
		- The attendee should send `name` and `email`
	- [x] Show registration badge
		-  The return of registration badge must be  `name`, `email`, `checkInUrl` and `eventTitle`
- [x] Create endpoints
	- [x] `POST` /events/{eventId}/attendees
	- [x] `GET` /attendees/{attendeeId}/badge
- [x] Create `CheckInService`
	- [x] Created an method to check in
- [x] Create endpoints
	- [x] `POST` /attendees/{attendeeId}/check-in
- [x] Create Exception Handlers.