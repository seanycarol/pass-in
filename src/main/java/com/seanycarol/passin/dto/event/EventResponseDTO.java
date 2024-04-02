package com.seanycarol.passin.dto.event;

import com.seanycarol.passin.domain.event.Event;

import lombok.Getter;

@Getter
public class EventResponseDTO {
    private EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}
