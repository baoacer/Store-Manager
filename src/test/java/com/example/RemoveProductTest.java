package com.example;

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

        assertEquals("Success::Delete Product Success", presenter.getResponse().getContent() );
    }

    @Test
    public void productNotExists() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary input = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("HSS001");
        input.execute(maHang);

        assertEquals("Error::Product Not Exists", presenter.getResponse().getContent() );
    }

    @Test
    public void removeProductFailed() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        InputBoundary input = new RemoveProductUseCase(database, presenter);

        RemoveDTO maHang = new RemoveDTO("");

        input.execute(maHang);

        assertEquals("Error::Invalid Product ID", presenter.getResponse().getContent() );
    }





}
