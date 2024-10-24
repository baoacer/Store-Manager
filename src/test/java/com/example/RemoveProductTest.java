package com.example;

import com.example.dtos.ResponseDTO;
import com.example.usecase.RemoveProduct.RemoveProductUseCase;
import com.example.database.MysqlDAO;
import com.example.dtos.RemoveDTO;
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
        InputBoundary input = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("HSS001");
        input.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();
        assertEquals("Success::Delete Product Success", response.getContent() );
    }

    @Test
    public void productNotExists() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary input = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("HSS001");
        input.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Product Not Exists", response.getContent() );
    }

    @Test
    public void removeProductFailed() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary input = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("");

        input.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Invalid Product ID", response.getContent() );
    }

}
