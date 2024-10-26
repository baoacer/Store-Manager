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
//        Controller controller = new Controller();
//        View view = new View(controller);
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);
//        controller.setInputBoundary(input);

        RemoveDTO maHang = new RemoveDTO("HDM003");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();
        assertEquals("Success::Delete Product Success", response.getContent() );
    }

    @Test
    public void productNotExists() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
//        Controller controller = new Controller();
//        View view = new View(controller);
//        Presenter presenter = new Presenter(view);
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);
//        controller.setInputBoundary(input);

        RemoveDTO maHang = new RemoveDTO("HSS001");

        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Product Not Exists", response.getContent() );
    }

    @Test
    public void removeProductFailed() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
//        Controller controller = new Controller();
//        View view = new View(controller);
//        Presenter presenter = new Presenter(view);
        Presenter presenter = new Presenter();
        InputBoundary removeUseCase = new RemoveProductUseCase(database, presenter);
//        controller.setInputBoundary(input);

        RemoveDTO maHang = new RemoveDTO("");

//        controller.removeProduct(maHang);
        removeUseCase.execute(maHang);

        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Invalid Product ID", response.getContent() );
    }

}
