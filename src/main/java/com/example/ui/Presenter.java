package com.example.ui;
import com.example.dtos.ResponseDTO;
import com.example.usecase.ResponseData;
import com.example.usecase.OutputBoundary;

public class Presenter implements OutputBoundary{
    private ResponseData response = null;

    @Override
    public void exportResult(ResponseData responseData) {
        this.response = responseData;
    }

    public ResponseData getResponse() {
        return response;
    }
}
