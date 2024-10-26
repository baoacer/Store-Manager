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
        if(validate(dto.getMaHang())){
            boolean isRemove = databaseBoundary.removeProduct(dto.getMaHang());
            if(isRemove){
                removeResponse.setContent("Success::Delete Product Success");
            }else{
                removeResponse.setContent("Error::Product Not Exists");
            }
        }else{
            removeResponse.setContent("Error::Invalid Product ID");
        }
        outputBoundary.exportResult(removeResponse);
//        outputBoundary.removeProductPresenter(removeResponse);
    }

    public boolean validate(String maHang){
        if(maHang == null || maHang.equals("")){
            return false;
        }
        return true;
    }
}
