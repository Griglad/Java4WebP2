package org.java4web.controllers;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.exceptions.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = {CreateRecordException.class})
    public ResponseEntity<ErrorResponse> handleValuesExistanceException(CreateRecordException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        return new ResponseEntity<Object>(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {DoctorNotFoundException.class})
        public ResponseEntity<ErrorResponse>handleDoctorNotFoundException(DoctorNotFoundException ex)
    {
       ErrorResponse errorResponse = new ErrorResponse();
       errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
       errorResponse.setMessage(ex.getMessage());
       return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(CreateRecordException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }

}
