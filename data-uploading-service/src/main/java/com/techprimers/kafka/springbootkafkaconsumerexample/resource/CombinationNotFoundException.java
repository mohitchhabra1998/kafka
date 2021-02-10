package com.techprimers.kafka.springbootkafkaconsumerexample.resource;

public class CombinationNotFoundException extends RuntimeException{

    public CombinationNotFoundException(String message) {
        super(message);
    }

    public CombinationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CombinationNotFoundException(Throwable cause) {
        super(cause);
    }
}
