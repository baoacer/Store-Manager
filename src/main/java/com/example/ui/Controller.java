package com.example.ui;

import com.example.usecase.addProduct.AddProductUseCase;
import com.example.usecase.InputBoundary;
import com.example.usecase.getProductListUseCase.GetProductListUseCase;
import com.example.usecase.removeProduct.RemoveProductUseCase;
import com.example.usecase.RequestData;
import com.example.usecase.totalQuantityUseCase.TotalQuantityUseCase;

import java.sql.SQLException;

public class Controller {
    private InputBoundary inputBoundary = null;

    public Controller(InputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public Controller() {
    }

    public void getProductList(RequestData requestData) throws SQLException {
        ((GetProductListUseCase)inputBoundary).execute(requestData);
    }

    public void addProduct(RequestData request) throws SQLException {
        ((AddProductUseCase)inputBoundary).execute(request);
    }

    public void removeProduct(RequestData request) throws SQLException {
        ((RemoveProductUseCase)inputBoundary).execute(request);
    }

    public void getToTalQuantity(RequestData requestData) throws SQLException {
        ((TotalQuantityUseCase)inputBoundary).execute(requestData);
    }

    public void setInputBoundary(InputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
}
