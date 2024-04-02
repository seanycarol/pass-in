package com.seanycarol.passin.dto.ateendee;

import java.time.LocalDateTime;

public record AttendeeDetails(
String id, String name,
String email, LocalDateTime createdAt, LocalDateTime checkedInAt) {
}
