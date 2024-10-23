package com.example.ui;

import com.example.usecase.OutputBoundary;
import com.example.usecase.ResponseData;

public class Presenter implements OutputBoundary {
    private ResponseData list = null;

    @Override
    public void exportData(ResponseData responseData) {
        this.list = responseData;
    }

    public ResponseData getList() {
        return list;
    }

}
