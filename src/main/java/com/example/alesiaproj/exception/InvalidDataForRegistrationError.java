package com.example.alesiaproj.exception;

public class InvalidDataForRegistrationError extends Error{
    public InvalidDataForRegistrationError() {
    }

    public InvalidDataForRegistrationError(String message) {
        super(message);
    }

    public InvalidDataForRegistrationError(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataForRegistrationError(Throwable cause) {
        super(cause);
    }

    public InvalidDataForRegistrationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
