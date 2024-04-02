package com.seanycarol.passin.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seanycarol.passin.domain.attendee.Attendee;
import com.seanycarol.passin.domain.event.Event;
import com.seanycarol.passin.dto.event.EventResponseDTO;
import com.seanycarol.passin.repositories.AttendeeRepository;
import com.seanycarol.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not foun with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }
}
