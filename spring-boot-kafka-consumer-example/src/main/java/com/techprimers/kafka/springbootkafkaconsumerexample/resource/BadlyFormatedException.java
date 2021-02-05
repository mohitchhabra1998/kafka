package com.techprimers.kafka.springbootkafkaconsumerexample.resource;

public class BadlyFormatedException extends RuntimeException{
    public BadlyFormatedException(String message) {
        super(message);
    }

    public BadlyFormatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadlyFormatedException(Throwable cause) {
        super(cause);
    }
}
