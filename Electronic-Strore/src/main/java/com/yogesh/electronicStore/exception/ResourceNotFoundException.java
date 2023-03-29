package com.yogesh.electronicStore.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String Message) {
        super(Message);

    }
}
