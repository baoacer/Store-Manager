package com.example.usecase;

import com.example.entity.HangHoa;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseBoundary {
    List<HangHoa> getAllProductList();
    boolean saveProduct(HangHoa hangHoa) throws SQLException;
    boolean removeProduct(String maHang) throws SQLException;

}
