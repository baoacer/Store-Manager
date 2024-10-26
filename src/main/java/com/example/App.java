package com.example;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.database.MysqlDAO;
import com.example.dtos.addProductDTOs.HangHoaDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.ui.Controller;
import com.example.ui.Presenter;
import com.example.ui.View;
import com.example.usecase.addProduct.AddProductUseCase;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.InputBoundary;

public class App {
    public static void main(String[] args) throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Controller controller = new Controller();
        View view = new View(controller);
        Presenter presenter = new Presenter(view);
        InputBoundary useCase = new AddProductUseCase(presenter, database);
        controller.setInputBoundary(useCase);
    }
}
