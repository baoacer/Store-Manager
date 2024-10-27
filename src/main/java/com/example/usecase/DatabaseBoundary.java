package com.example.usecase;

<<<<<<< HEAD
import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;

import java.sql.SQLException;
import java.util.List;
=======
import java.sql.SQLException;
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3

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


    String updateProductDienMay(RequestData requestData) throws SQLException;

    String updateProductThucPham(RequestData requestData) throws SQLException;

    String updateProductSanhSu(RequestData requestData) throws SQLException;

    int getTotalQuantityDienMay() throws SQLException;

    int getTotalQuantitySanhSu() throws SQLException;

    int getTotalQuantityThucPham() throws SQLException;
}
