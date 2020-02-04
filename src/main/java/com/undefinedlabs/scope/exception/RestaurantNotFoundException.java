package com.undefinedlabs.scope.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super();
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Throwable cause) {
        super(cause);
    }
}
