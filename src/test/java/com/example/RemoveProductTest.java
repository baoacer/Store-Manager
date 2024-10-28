package com.example;

import com.example.dtos.ResponseDTO;
import com.example.ui.Controller;
import com.example.ui.View;
import com.example.usecase.removeProduct.RemoveProductUseCase;
import com.example.database.MysqlDAO;
import com.example.dtos.removeProductDTOs.RemoveDTO;
import com.example.ui.Presenter;
import com.example.usecase.*;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class RemoveProductTest {
    @Test
    public void removeProductOk() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("HDM003");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();
        assertEquals("Success::Delete Product Success", response.getContent() );
    }

    @Test
    public void productNotExists() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("HSS001");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Product Not Exists", response.getContent() );
    }

    @Test
    public void removeProductNullId() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);

        // Data Test
        RemoveDTO maHang = new RemoveDTO("");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Product ID cannot be empty.", response.getContent());
    }


    @Test
    public void removeProductInvalidPrefix() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);

        // Data Test
        RemoveDTO maHang = new RemoveDTO("XYZ123");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Invalid Product ID. ID must start with HTT, HSS, or HDM.", response.getContent());
    }
}
