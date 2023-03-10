package com.fabrickSB.controller;

import java.util.List;

import com.fabrickSB.errors.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabrickSB.exceptions.BadRequestException;
import com.fabrickSB.exceptions.ForbiddenException;
import com.fabrickSB.errors.ErrorResponse;
import com.fabrickSB.errors.ErrorResponseList;

@ControllerAdvice
public class AdviceController {
    //Per la validation dei dati @Valid e @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseList> exception(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErrorResponse(ErrorMessages.INVALID_MESSAGE, err.getDefaultMessage(), err.getField())).toList();

        ErrorResponseList responseList = ErrorResponseList
                .builder()
                .status(ErrorMessages.KO_MESSAGE)
                .errors(errors)
                .build();
        responseList
                .setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseList);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseList> exception(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(ex.getError());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseList> exception(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ex.getError());
    }
    //GENERIC
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseList> exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(ErrorResponseList
                        .builder()
                        .status(ErrorMessages.KO_MESSAGE)
                        .errors(List.of(ErrorResponse
                                .builder()
                                .description(ex.getMessage())
                                .build()))
                        .build());
    }
}
