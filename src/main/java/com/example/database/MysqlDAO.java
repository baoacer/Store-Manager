package com.example.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.usecase.DatabaseBoundary;

public class MysqlDAO implements DatabaseBoundary {

    @Override
    public List<HangHoa> getAllProductList() {
        return getProductListFromDatabase();
    }

    private List<HangHoa> getProductListFromDatabase() {
        List<HangHoa> productDB = new ArrayList<>();
        String sqlQueryHangDienMay = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, d.thoiGianBaoHanh, d.congSuat "
                +
                "FROM hanghoa h " +
                "INNER JOIN hangdienmay d ON h.maHang = d.maHang";
        ;
        String sqlQueryHangSanhsu = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, s.nhaSanXuat, s.ngayNhapKho " +
                "FROM hanghoa h " +
                "INNER JOIN hangsanhsu s ON h.maHang = s.maHang";
        String sqlQueryHangThucPham = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, t.ngaySanXuat, t.ngayHetHan, t.nhaCungCap "
                +
                "FROM hanghoa h " +
                "INNER JOIN hangthucpham t ON h.maHang = t.maHang";

        try (Connection connection = MysqlConnection.getConnection();
                Statement statement = connection.createStatement()) {

            // Lấy hàng điện máy
            try (ResultSet resultSet1 = statement.executeQuery(sqlQueryHangDienMay)) {
                while (resultSet1.next()) {
                    HangHoa hangHoa = new HangDienMay(
                            resultSet1.getDouble("congSuat"),
                            resultSet1.getInt("thoiGianBaoHanh"),
                            resultSet1.getString("maHang"),
                            resultSet1.getString("tenHang"),
                            resultSet1.getInt("soLuongTon"),
                            resultSet1.getDouble("donGia"));
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng sành sứ
            try (ResultSet resultSet2 = statement.executeQuery(sqlQueryHangSanhsu)) {
                while (resultSet2.next()) {
                    LocalDate ngayNhapKho = resultSet2.getDate("ngayNhapKho").toLocalDate();
                    HangHoa hangHoa = new HangSanhSu(
                            ngayNhapKho,
                            resultSet2.getString("nhaSanXuat"),
                            resultSet2.getString("maHang"),
                            resultSet2.getString("tenHang"),
                            resultSet2.getInt("soLuongTon"),
                            resultSet2.getDouble("donGia"));
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng thực phẩm
            try (ResultSet resultSet3 = statement.executeQuery(sqlQueryHangThucPham)) {
                while (resultSet3.next()) {
                    LocalDate ngaySanXuat = resultSet3.getDate("ngaySanXuat").toLocalDate();
                    LocalDate ngayHetHan = resultSet3.getDate("ngayHetHan").toLocalDate();
                    HangHoa hangHoa = new HangThucPham(
                            ngayHetHan,
                            ngaySanXuat,
                            resultSet3.getString("nhaCungCap"),
                            resultSet3.getString("maHang"),
                            resultSet3.getString("tenHang"),
                            resultSet3.getInt("soLuongTon"),
                            resultSet3.getDouble("donGia"));
                    productDB.add(hangHoa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDB; // Trả danh sách đối tượng HangHoa
    }
}
