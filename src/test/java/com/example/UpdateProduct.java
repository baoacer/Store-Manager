package com.example;

import com.example.database.MysqlDAO;
import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.updateOutputDTO.UpdateDienMayOutPutDTO;
import com.example.ui.Presenter;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.RequestData;
import com.example.usecase.updateProduct.UpdateProductUseCase;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UpdateProduct {
    @Test
    public void updateProduct_empty_tenHang() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "", 10, 200000, 2, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }

    @Test
    public void updateProductTest_soLuongAM() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "telivision",  -1, 200000, 2, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }

    @Test
    public void updateProductTest_3() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "telivision",  0, -2, 2, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }

    @Test
    public void updateProductTest_thoiGianBaoHanhAm() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "telivision",  1, 1000, -1, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }

    @Test
    public void updateProductTest_congSuatAm() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "telivision",  1, 1000, 20, -1);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }

    @Test
    public void updateProduct_empty_all() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("", "", 0, 0, 0, 0);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();

        assertEquals("Thieu thong tin can thiet", updateDienMayOutPutDTO.getMessage());
    }
}
