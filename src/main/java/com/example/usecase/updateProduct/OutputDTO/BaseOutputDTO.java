package com.example.usecase.updateProduct.OutputDTO;

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
