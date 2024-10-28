package com.example;

import java.sql.SQLException;

<<<<<<< HEAD
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
=======
import com.example.database.MySqlDAO;
import com.example.dtos.DienMayDTO;
import com.example.ui.Presenter;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.RequestData;
import com.example.usecase.totalQuantityDienMay.TotalQuantityDienMayOutputDTO;
import com.example.usecase.totalQuantityDienMay.TotalQuantityUseCase;
import com.example.usecase.updateProduct.OutputDTO.UpdateDienMayOutPutDTO;
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3
import com.example.usecase.updateProduct.UpdateProductUseCase;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class GetTotalQuantityProductsTest {
    @Test
    public void testGetTotalQuantityProducts() throws SQLException {
<<<<<<< HEAD
        DatabaseBoundary databaseBoundary = new MysqlDAO();
=======
        DatabaseBoundary databaseBoundary = new MySqlDAO();
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3
        Presenter presenter = new Presenter();
        TotalQuantityUseCase totalQuantityUseCase = new TotalQuantityUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO();
        totalQuantityUseCase.execute(requestData);
<<<<<<< HEAD
        TotalQuantityDienMayOutputDTO totalQuantityDienMayOutputDTO = (TotalQuantityDienMayOutputDTO) presenter.getResponse();
        assertEquals(568, totalQuantityDienMayOutputDTO.getTotalQuantity());
=======
        TotalQuantityDienMayOutputDTO totalQuantityDienMayOutputDTO = (TotalQuantityDienMayOutputDTO) presenter.getResponseData();
        assertEquals(33, totalQuantityDienMayOutputDTO.getTotalQuantity());
    }

    @Test
    public void updateProduct() throws SQLException {
        DatabaseBoundary databaseBoundary = new MySqlDAO();
        Presenter presenter = new Presenter();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(databaseBoundary, presenter);
        RequestData requestData = new DienMayDTO("H001", "Television", 10, 200000, 2, 20);
        updateProductUseCase.execute(requestData);
        UpdateDienMayOutPutDTO updateDienMayOutPutDTO = (UpdateDienMayOutPutDTO) presenter.getResponseData();
        assertEquals("Dien may cap nhat thanh cong!", updateDienMayOutPutDTO.getMessage());
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3
    }
}
