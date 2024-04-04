package com.seanycarol.passin.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.seanycarol.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.seanycarol.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.seanycarol.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.seanycarol.passin.domain.event.exceptions.EventFullException;
import com.seanycarol.passin.domain.event.exceptions.EventNotFoundException;
import com.seanycarol.passin.dto.general.ErrorResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> handleEventNotFound(EventNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), status.value(), LocalDateTime.now());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<Object> handleEventFull(EventFullException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), status.value(), LocalDateTime.now());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
    
    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<Object> handleAttendeeNotFound(AttendeeNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), status.value(), LocalDateTime.now());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity<Object> handleAttendeeAlreadyExists(AttendeeAlreadyExistException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), status.value(), LocalDateTime.now());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity<Object> handleCheckInAlreadyExists(CheckInAlreadyExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), status.value(), LocalDateTime.now());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
