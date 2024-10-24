package com.example.ui;

import com.example.usecase.OutputBoundary;
import com.example.usecase.ResponseData;

public class Presenter implements OutputBoundary {
    private ResponseData responseData = null;

    @Override
    public void exportResult(ResponseData responseData) {
        this.responseData = responseData;
    }

    public ResponseData getResponseData() {
        return responseData;
    }
}
