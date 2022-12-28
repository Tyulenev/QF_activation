package com.QSystems.quick_flow_registration.exceptionHandling;

public class NoSuchLicenceException extends RuntimeException{
    public NoSuchLicenceException(String message) {
        super(message);
    }
}
