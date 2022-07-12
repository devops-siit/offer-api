package com.dislinkt.offersapi.exception.types;

import com.dislinkt.offersapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EntityNotSavedException extends BaseException {
    private static final long serialVersionUID = 1L;

    public EntityNotSavedException(String errorMessage) {
        super(errorMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }
}