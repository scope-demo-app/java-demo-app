package com.undefinedlabs.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class RestaurantAlreadyDeletedException extends RuntimeException {

    public RestaurantAlreadyDeletedException() {
        super();
    }

    public RestaurantAlreadyDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantAlreadyDeletedException(String message) {
        super(message);
    }

    public RestaurantAlreadyDeletedException(Throwable cause) {
        super(cause);
    }
}
