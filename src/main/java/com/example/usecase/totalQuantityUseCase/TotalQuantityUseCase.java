package com.example.usecase.totalQuantityUseCase;

import java.sql.SQLException;

import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.dtos.addProductDTOs.ThucPhamDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityDienMayOutputDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantitySanhSuOutputDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityThucPhamOutputDTO;
import com.example.usecase.*;

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
