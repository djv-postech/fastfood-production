package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
