package com.example.usecase;

import com.example.usecase.ResponseData;

public interface OutputBoundary {

    void exportResult(ResponseData responseData);

    void TotalQuantityPresenter(ResponseData responseData);
    void updateProductPresenter(ResponseData responseData);
    void addProductPresenter(ResponseData responseData);
    void removeProductPresenter(ResponseData responseData);

}
