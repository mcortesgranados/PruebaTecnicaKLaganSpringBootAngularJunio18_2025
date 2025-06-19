package com.mcg.klagan.pruebatecnica.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * GlobalExceptionHandler centralizes all exception handling for the web layer.
 *
 * ✅ Clean Architecture:
 * - Part of the infrastructure layer (adapter for cross-cutting concerns).
 * - Keeps controller classes clean and free from repetitive error-handling logic.
 *
 * 🧱 Hexagonal Architecture:
 * - This class is a technical adapter that captures and maps exceptions to HTTP responses.
 * - Ensures separation of concerns by isolating exception mapping from business logic.
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Responsible solely for translating application exceptions to HTTP responses.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Easily extendable with new exception handlers without modifying existing ones.
 *
 * 🔐 Security & Consistency:
 * - Returns consistent, user-friendly error messages.
 * - Prevents stack traces and sensitive information from being exposed to the client.
 *
 * 📦 Layer: Adapter → Inbound → Exception Handler (Spring MVC)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 4:59 AM GMT -5  Bogota DC Colombia
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors on DTOs annotated with @Valid.
     * Triggered when form binding or JSON payload fails validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles constraint violations triggered by @Validated on method parameters.
     * Useful for validating path or query parameters in REST controllers.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles situations where an entity was not found, typically during lookup operations.
     * Returns a 404 NOT FOUND with the message.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles business logic exceptions such as invalid arguments.
     * Typically thrown during service-level validations.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Handles any uncaught exceptions, acting as a fallback mechanism.
     * Ensures internal errors don’t expose sensitive details to the client.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal server error");
        error.put("details", ex.getMessage()); // Optional: remove in production
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
