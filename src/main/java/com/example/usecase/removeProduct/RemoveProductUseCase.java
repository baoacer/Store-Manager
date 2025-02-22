package com.example.usecase.removeProduct;

import com.example.usecase.InputBoundary;
import com.example.usecase.RequestData;
import com.example.dtos.removeProductDTOs.RemoveDTO;
import com.example.dtos.ResponseDTO;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.OutputBoundary;

import java.sql.SQLException;

public class RemoveProductUseCase implements InputBoundary {
    private DatabaseBoundary databaseBoundary = null;
    private OutputBoundary outputBoundary = null;

    public RemoveProductUseCase(DatabaseBoundary databaseBoundary, OutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        RemoveDTO dto = (RemoveDTO) requestData;
        ResponseDTO removeResponse = new ResponseDTO();

        if (dto.getMaHang() == null || dto.getMaHang().isEmpty()) {
            removeResponse.setContent("Error::Product ID cannot be empty.");
        } else if (!validate(dto.getMaHang())) {
            removeResponse.setContent("Error::Invalid Product ID. ID must start with HTT, HSS, or HDM.");
        } else {
            boolean isRemove = databaseBoundary.removeProduct(dto.getMaHang());
            if (isRemove) {
                removeResponse.setContent("Success::Delete Product Success");
            } else {
                removeResponse.setContent("Error::Product Not Exists");
            }
        }

        outputBoundary.exportResult(removeResponse);
    }


    public boolean validate(String maHang) {
        if (maHang == null || maHang.isEmpty()) {
            return false;
        }
        if (!maHang.startsWith("HTT") && !maHang.startsWith("HSS") && !maHang.startsWith("HDM")) {
            return false;
        }
        return true;
    }
}
