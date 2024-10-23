package com.example.ui;

import com.example.dtos.ResponseDTO;
import com.example.usecase.ResponseData;
import com.example.usecase.OutputBoundary;

public class Presenter implements OutputBoundary{
    private ResponseDTO response = null;

    @Override
    public void exportResult(ResponseData responseData) {
        this.response = (ResponseDTO) responseData;
    }

    @Override
    public void exportResult() {
        
    }

    public ResponseDTO getResponse() {
        return response;
    }
}
