package com.example.ui;
import com.example.dtos.ResponseDTO;
import com.example.usecase.ResponseData;
import com.example.usecase.OutputBoundary;

public class Presenter implements OutputBoundary{
    private ResponseDTO response = null;
    private ResponseData list = null; 

    @Override
    public void exportResult(ResponseData responseData) {
        this.response = (ResponseDTO) responseData;
    }
  
    @Override
    public void exportData(ResponseData responseData) {
        this.list = responseData;
    }

    public ResponseDTO getResponse() {
        return response;
    }
  
   public ResponseData getList() {
        return list;
    }
}
