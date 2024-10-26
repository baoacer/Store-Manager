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
        Controller controller = new Controller();
        View view = new View(controller);
        Presenter presenter = new Presenter(view);
        TotalQuantityUseCase totalQuantityUseCase = new TotalQuantityUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO(
                null,
                null,
                100,
                100000,
                8,
                30);
        totalQuantityUseCase.execute(requestData);
        TotalQuantityDienMayOutputDTO totalQuantityDienMayOutputDTO = (TotalQuantityDienMayOutputDTO) presenter.getResponse();
        assertEquals(33, totalQuantityDienMayOutputDTO.getTotalQuantity());
    }

    @Test
    public void updateProduct() throws SQLException {
        DatabaseBoundary databaseBoundary = new MysqlDAO();
        Controller controller = new Controller();
        View view = new View(controller);
        Presenter presenter = new Presenter(view);
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "Television", 10, 200000, 2, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponse();
        assertEquals("Dien may cap nhat thanh cong!", updateDienMayOutPutDTO.getMessage());
    }
}
