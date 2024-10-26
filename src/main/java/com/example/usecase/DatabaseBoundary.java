package com.example.usecase;

import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseBoundary {
    List<HangHoa> getAllProductList();
    boolean saveProduct(HangHoa hangHoa) throws SQLException;
    boolean removeProduct(String maHang) throws SQLException;

    String updateProductDienMay(HangDienMay hangDienMay) throws SQLException;

    String updateProductThucPham(HangThucPham hangThucPham) throws SQLException;

    String updateProductSanhSu(HangSanhSu hangSanhSu) throws SQLException;

    int getTotalQuantityDienMay() throws SQLException;

    int getTotalQuantitySanhSu() throws SQLException;

    int getTotalQuantityThucPham() throws SQLException;

}
