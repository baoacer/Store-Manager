package com.example.dtos;

import com.example.usecase.ResponseData;

public class ResponseDTO implements ResponseData {
    private String content;

    public ResponseDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
