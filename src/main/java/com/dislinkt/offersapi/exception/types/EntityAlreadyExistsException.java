package com.dislinkt.offersapi.exception.types;

import com.dislinkt.offersapi.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(String errorMessage) {
        super(errorMessage, HttpStatus.CONFLICT);
    }
}