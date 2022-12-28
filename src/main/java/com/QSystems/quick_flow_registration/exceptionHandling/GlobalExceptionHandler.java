package com.QSystems.quick_flow_registration.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DataInLicenceTableIncorrectData> handleException(
            NoSuchLicenceException exception
    ) {
        DataInLicenceTableIncorrectData data = new DataInLicenceTableIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<DataInLicenceTableIncorrectData> handleException(
            Exception exception
    ) {
        DataInLicenceTableIncorrectData data = new DataInLicenceTableIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
