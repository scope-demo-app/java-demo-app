package com.undefinedlabs.scope.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongArgumentException extends RuntimeException {

    public WrongArgumentException() {
        super();
    }

    public WrongArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongArgumentException(String message) {
        super(message);
    }

    public WrongArgumentException(Throwable cause) {
        super(cause);
    }
}
