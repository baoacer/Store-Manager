package com.example.usecase;

public interface OutputBoundary {

    void exportResult(ResponseData responseData);

    void TotalQuantityPresenter(ResponseData responseData);
    void updateProductPresenter(ResponseData responseData);
    void addProductPresenter(ResponseData responseData);
    void removeProductPresenter(ResponseData responseData);

}
