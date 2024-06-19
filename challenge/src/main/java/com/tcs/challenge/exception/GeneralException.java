package com.tcs.challenge.exception;

public class GeneralException extends Exception {

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Object... args) {
        super(String.format(message, args));
    }

    public GeneralException(Throwable cause, String message) {
        super(message, cause);
    }

    public GeneralException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
    }

}
