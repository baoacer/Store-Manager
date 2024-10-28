package com.example.usecase.totalQuantityDienMay;

import java.sql.SQLException;

import com.example.dtos.DienMayDTO;
import com.example.dtos.SanhSuDTO;
import com.example.dtos.ThucPhamDTO;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.InputBoundary;
import com.example.usecase.OutputBoundary;
import com.example.usecase.RequestData;
import com.example.usecase.ResponseData;

public class TotalQuantityUseCase implements InputBoundary {

    private DatabaseBoundary databaseBoundary = null;

    private OutputBoundary outputBoundary = null;

    public TotalQuantityUseCase(DatabaseBoundary databaseBoundary, OutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        if (requestData instanceof DienMayDTO) {
            int totalQuantity = databaseBoundary.getTotalQuantityDienMay();
            ResponseData responseData = new TotalQuantityDienMayOutputDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof SanhSuDTO) {
            int totalQuantity = databaseBoundary.getTotalQuantitySanhSu();
            ResponseData responseData = new TotalQuantitySanhSuOutputDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof ThucPhamDTO) {
            int totalQuantity = databaseBoundary.getTotalQuantityThucPham();
            ResponseData responseData = new TotalQuantityThucPhamOutputDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        }

    }

}
