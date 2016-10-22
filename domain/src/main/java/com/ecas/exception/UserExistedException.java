package com.ecas.exception;

/**
 * Created by tupham on 10/15/16.
 */
public class UserExistedException extends Exception {

    public UserExistedException() {
    }

    public UserExistedException(String message) {
        super(message);
    }

    public UserExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistedException(Throwable cause) {
        super(cause);
    }

    public UserExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
