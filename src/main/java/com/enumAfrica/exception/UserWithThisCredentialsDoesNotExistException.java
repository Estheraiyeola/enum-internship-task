package com.enumAfrica.exception;

public class UserWithThisCredentialsDoesNotExistException extends EnumException {
    public UserWithThisCredentialsDoesNotExistException(String message) {
        super(message);
    }
}
