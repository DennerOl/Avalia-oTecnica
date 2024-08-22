package com.example.ProcessosJuridicos.controller.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ProcessosJuridicos.dto.CustonError;
import com.example.ProcessosJuridicos.service.exception.NotFoundException;
import com.example.ProcessosJuridicos.service.exception.ProcessoDuplicadoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ContrExeptionHandler {

  @ExceptionHandler(ProcessoDuplicadoException.class)
  public ResponseEntity<CustonError> ProcessoDuplicadoException(ProcessoDuplicadoException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.CONFLICT;
    CustonError err = new CustonError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<CustonError> notFoundException(NotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustonError err = new CustonError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

}
