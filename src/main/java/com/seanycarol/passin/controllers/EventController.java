package com.seanycarol.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.seanycarol.passin.dto.ateendee.AttendeeIdDTO;
import com.seanycarol.passin.dto.ateendee.AttendeeRequestDTO;
import com.seanycarol.passin.dto.ateendee.AttendeesListResponseDTO;
import com.seanycarol.passin.dto.event.EventIdDTO;
import com.seanycarol.passin.dto.event.EventRequestDTO;
import com.seanycarol.passin.dto.event.EventResponseDTO;
import com.seanycarol.passin.services.AttendeeService;
import com.seanycarol.passin.services.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Everything about events")
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @Operation(summary = "Get event", description = "Retrieve details of a specific event")
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId) {
        EventResponseDTO event = this.eventService.getEventDetail(eventId);
        return ResponseEntity.ok(event);
    }   
    
    @Operation(summary = "Post event", description = "Register a new event")
    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{eventId}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @Operation(summary = "Post attendee on event", description = "Register a new attendee to a specific event")
    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerAttendee(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder) {
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @Operation(summary = "Get event attendees", description = "Retrieve a list of all attendees of a specific event")
    @GetMapping("/attendees/{eventId}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String eventId) {
        AttendeesListResponseDTO attendeesListResponse = this.attendeeService.getEventsAttendee(eventId);
        return ResponseEntity.ok(attendeesListResponse);
    }
}
