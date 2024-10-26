package com.example.dtos.updateOutputDTO;

import com.example.usecase.ResponseData;

public class BaseOutputDTO implements ResponseData {
    private String message;

    public BaseOutputDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
