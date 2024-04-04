___
## About project

> The _pass-in_ is an software to management attendees on presential events
- The tool allows the organizer to register an event and open a public registration page.
- Registered participants can generate a credential for check-in on the day of the event.
- The system will scan the participant's credential to allow entry to the event.

### Requirements

#### Functional Requirements
- The organizer must be able to register a new event;
- The organizer must be able to view data from an event;
- The organizer must be able to view the list of participants;
- The participant must be able to register for an event;
- The participant must be able to view their registration badge;
- The participant must be able to check-in at the event.

## Creating project

- [x] Creating project using Spring Initializr
	- Spring Web
	- Flyway
	- Dev Tools
	- Lombok
	- JPA
- [x] Create and configure database
```xml
<dependency> 
	<groupId>org.hsqldb</groupId> 
	<artifactId>hsqldb</artifactId> 
	<version>2.7.1</version> 
</dependency>
```
- [x] Create migrations according to tables.
- [x] Create entities that will be represented by tables
	- Event
	- Attendee
	- CheckIn
- [x] Create repositories
	- Event
	- Attendee
	- CheckIn
- [x] Create controllers
	- [x] **/events** 
	- [x] **/attendees** 

![[pass-in-erd.svg]]
