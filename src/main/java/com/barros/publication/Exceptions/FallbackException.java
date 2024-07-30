package com.barros.publication.Exceptions;

public class FallbackException extends RuntimeException {

    public FallbackException(Throwable cause) {
        super("We're unavailable right now, please try again later.", cause);
    }
}
