package com.example;
import static org.junit.Assert.assertEquals;

import com.example.database.MysqlConnection;
import com.example.entity.HangHoa;
import com.example.ui.Controller;
import com.example.ui.View;
import org.junit.Before;
import org.junit.Test;
import com.example.database.MysqlDAO;
import com.example.ui.Presenter;
import com.example.usecase.getProductListUseCase.GetProductListOutputDTO;
import com.example.usecase.getProductListUseCase.GetProductListUseCase;
import com.example.usecase.InputBoundary;

import java.sql.SQLException;
import java.util.List;

public class GetProductListTest {

    @Test
    public void testGetAllProductListOK() throws SQLException{
        // 1. Tạo đối tượng MysqlDAO và truyền dữ liệu giả lập từ getData()
        MysqlDAO mdao = new MysqlDAO();
        // 2. Tạo đối tượng Presenter để lưu kết quả
        Presenter p = new Presenter();
        // 3. Tạo đối tượng UseCase và truyền Presenter và MysqlDAO
        InputBoundary i = new GetProductListUseCase(p, mdao);
        // 4. Tạo request data giả lập
        // 5. Thực thi use case
        i.execute(null);
        // 6. Kiểm tra kết quả trong Presenter
        GetProductListOutputDTO outputDTO = (GetProductListOutputDTO) p.getResponse();
        // Kiểm tra số lượng sản phẩm trong danh sách
        assertEquals(71, outputDTO.getList().size());

        outputDTO.getList().forEach(System.out::println);
    }

    public void testGetAllProductListConnectionError() {
//        // Giả lập lỗi kết nối
//        when(mysqlDAO.getAllProductList()).thenThrow(new SQLException("Connection error"));
//
//
//        // Kiểm tra xem lỗi có được bắt hay không
//        List<HangHoa> products = mysqlDAO.getAllProductList();
//        assertEquals("Expected empty list of products due to connection error", 0, products.size());
    }
}
