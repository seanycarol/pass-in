package com.seanycarol.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.seanycarol.passin.domain.attendee.Attendee;
import com.seanycarol.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.seanycarol.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.seanycarol.passin.domain.checkin.CheckIn;
import com.seanycarol.passin.dto.ateendee.AttendeeBadgeResponseDTO;
import com.seanycarol.passin.dto.ateendee.AttendeeDetails;
import com.seanycarol.passin.dto.ateendee.AttendeesListResponseDTO;
import com.seanycarol.passin.dto.ateendee.AttendeeBadgeDTO;
import com.seanycarol.passin.repositories.AttendeeRepository;
import com.seanycarol.passin.repositories.CheckInRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(),
                    attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if (isAttendeeRegistered.isPresent()) {
            throw new AttendeeAlreadyExistException("Atteendee is already registered");
        }
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
        
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();

        AttendeeBadgeDTO badgeDTO = new AttendeeBadgeDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());

        return new AttendeeBadgeResponseDTO(badgeDTO);
    }
}
