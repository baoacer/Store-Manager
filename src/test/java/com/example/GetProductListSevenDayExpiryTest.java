package com.example;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryOutputDTO;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import org.junit.Test;

import com.example.database.MysqlDAO;
import com.example.ui.Presenter;

import com.example.usecase.InputBoundary;
public class GetProductListSevenDayExpiryTest {

    @Test
    public void getProductListSevenDayExpiryOK() throws SQLException {
        MysqlDAO mdao = new MysqlDAO();
        Presenter p = new Presenter();
        InputBoundary i = new GetProductListSevenDayExpiryUseCase(p, mdao);
        i.execute(null);
        GetProductListSevenDayExpiryOutputDTO outputDTO = (GetProductListSevenDayExpiryOutputDTO) p.getResponse();

        outputDTO.getList().forEach(System.out::println);

        assertEquals(3, outputDTO.getList().size());
    }

}
