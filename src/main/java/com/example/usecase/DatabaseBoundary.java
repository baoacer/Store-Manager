package com.example.usecase;

import com.example.entity.HangHoa;

import java.sql.SQLException;

public interface DatabaseBoundary {
   List<HangHoa> getAllProductList();
    boolean saveProduct(HangHoa hangHoa) throws SQLException;

    boolean removeProduct(String maHang) throws SQLException;

}
