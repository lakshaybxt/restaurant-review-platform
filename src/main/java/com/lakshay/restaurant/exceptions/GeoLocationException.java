package com.lakshay.restaurant.exceptions;

public class GeoLocationException extends BaseException {
    public GeoLocationException() {
        super();
    }

    public GeoLocationException(String message) {
        super(message);
    }

    public GeoLocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
