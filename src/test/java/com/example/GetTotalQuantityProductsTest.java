package com.example;

import java.sql.SQLException;

import com.example.database.MysqlDAO;
import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.ui.Controller;
import com.example.ui.Presenter;
import com.example.ui.View;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.RequestData;
import com.example.dtos.totalQuantityDTOs.TotalQuantityDienMayOutputDTO;
import com.example.usecase.totalQuantityUseCase.TotalQuantityUseCase;
import com.example.dtos.updateOutputDTO.UpdateDienMayOutPutDTO;
import com.example.usecase.updateProduct.UpdateProductUseCase;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class GetTotalQuantityProductsTest {
    @Test
    public void testGetTotalQuantityProducts() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Presenter presenter = new Presenter();
        TotalQuantityUseCase totalQuantityUseCase = new TotalQuantityUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO();
        totalQuantityUseCase.execute(requestData);
        TotalQuantityDienMayOutputDTO totalQuantityDienMayOutputDTO = (TotalQuantityDienMayOutputDTO) presenter.getResponse();
        assertEquals(568, totalQuantityDienMayOutputDTO.getTotalQuantity());
    }
}
