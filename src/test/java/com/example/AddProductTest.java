package com.example;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.usecase.AddProduct.AddProductUseCase;
import com.example.dtos.HangHoaDTO;
import com.example.dtos.SanhSuDTO;
import com.example.usecase.*;
import org.junit.Test;

import com.example.database.MysqlDAO;
import com.example.ui.Presenter;

import static junit.framework.TestCase.assertEquals;

public class AddProductTest {
    @Test
    public void addProductOk() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS001",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                "Sanh Hoa Sen",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);

        assertEquals("Success::Thêm sản phẩm thành công", presenter.getResponse().getContent());
    }

     @Test
    public void addProductFailed() throws SQLException {
         DatabaseBoundary database = new MysqlDAO();
         Presenter presenter = new Presenter();
         AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

         // Data Test
         HangHoaDTO hangHoaDTO = new SanhSuDTO(
                 "",
                 "Sanh Su Test",
                 50,
                 200000,
                 "Sành sứ",
                 "Sanh Hoa Sen",
                 LocalDate.parse("2024-10-25"));
         addUseCase.execute(hangHoaDTO);


        assertEquals("Error::Kiểm tra lại dữ liệu", presenter.getResponse().getContent());
    }




}
