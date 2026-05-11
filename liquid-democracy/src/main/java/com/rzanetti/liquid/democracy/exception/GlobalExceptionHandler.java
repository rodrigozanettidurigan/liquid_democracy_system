package com.rzanetti.liquid.democracy.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFound(
//            ResourceNotFoundException exception,
//            HttpServletRequest request) {
//        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request, null);
//    }
//
//    @ExceptionHandler(BusinessRuleException.class)
//    public ResponseEntity<ErrorResponse> handleBusinessRule(
//            BusinessRuleException exception,
//            HttpServletRequest request) {
//        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request, null);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidation(
//            MethodArgumentNotValidException exception,
//            HttpServletRequest request) {
//        Map<String, String> fieldErrors = new HashMap<>();
//        exception.getBindingResult().getFieldErrors()
//                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
//
//        return buildResponse(HttpStatus.BAD_REQUEST, "Requisicao invalida", request, fieldErrors);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponse> handleUnreadableMessage(
//            HttpMessageNotReadableException exception,
//            HttpServletRequest request) {
//        return buildResponse(HttpStatus.BAD_REQUEST, "Corpo da requisicao invalido", request, null);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleDataIntegrity(
//            DataIntegrityViolationException exception,
//            HttpServletRequest request) {
//        return buildResponse(HttpStatus.CONFLICT, "Operacao viola uma restricao de dados", request, null);
//    }
//
//    private ResponseEntity<ErrorResponse> buildResponse(
//            HttpStatus status,
//            String message,
//            HttpServletRequest request,
//            Map<String, String> fieldErrors) {
//        ErrorResponse response = new ErrorResponse(
//                LocalDateTime.now(),
//                status.value(),
//                status.getReasonPhrase(),
//                message,
//                request.getRequestURI(),
//                fieldErrors
//        );
//
//        return ResponseEntity.status(status).body(response);
//    }
//}
