package com.example;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.dtos.ResponseDTO;
import com.example.ui.Controller;
import com.example.ui.View;
import com.example.usecase.addProduct.AddProductUseCase;
import com.example.dtos.addProductDTOs.HangHoaDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.usecase.*;
import org.junit.Test;

import com.example.database.MysqlDAO;
import com.example.ui.Presenter;

import static junit.framework.TestCase.assertEquals;

public class AddProductTest {
    @Test
    public void addProductOk() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
//        Controller controller = new Controller();
//        View view = new View(controller);
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Success::Thêm sản phẩm thành công", response.getContent() );
    }

     @Test
    public void addProductFailed() throws SQLException {
         DatabaseBoundary database = new MysqlDAO();
//         Controller controller = new Controller();
//         View view = new View(controller);
         Presenter presenter = new Presenter();
         AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

         // Data Test
         HangHoaDTO hangHoaDTO = new SanhSuDTO(
                 "",
                 "Sanh Su Test",
                 50,
                 200000,
                 "Sành sứ",
                 LocalDate.parse("2024-10-25"));

         addUseCase.execute(hangHoaDTO);
         ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Kiểm tra lại dữ liệu", response.getContent());
    }




}
