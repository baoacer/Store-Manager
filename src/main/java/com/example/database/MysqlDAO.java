package com.example.database;

import java.sql.*;
import java.util.List;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.usecase.DatabaseBoundary;

public class MysqlDAO implements DatabaseBoundary {
    private static final String URL = "jdbc:mysql://localhost:3306/products_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {

            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Error connecting to database", e);
            }
        }
        return connection;
    }


    public MysqlDAO() {
    }

    @Override
    public boolean saveProduct(HangHoa hangHoa) throws SQLException {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Bước 1: Lưu vào bảng hanghoa
            String sqlHangHoa = "INSERT INTO hanghoa (maHang, tenHang, soLuongTon, donGia) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtHangHoa = conn.prepareStatement(sqlHangHoa)) {
                stmtHangHoa.setString(1, hangHoa.getMaHang());
                stmtHangHoa.setString(2, hangHoa.getTenHang());
                stmtHangHoa.setInt(3, hangHoa.getSoLuongTon());
                stmtHangHoa.setDouble(4, hangHoa.getDonGia());

                stmtHangHoa.executeUpdate();
            }

            // Bước 2: Lưu vào bảng con tùy thuộc vào loại hàng hóa
            if (hangHoa instanceof HangThucPham) {
                HangThucPham ht = (HangThucPham) hangHoa;
                String sqlThucPham = "INSERT INTO hangthucpham (maHang, ngaySanXuat, ngayHetHan, nhaCungCap) " +
                        "VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmtThucPham = conn.prepareStatement(sqlThucPham)) {
                    stmtThucPham.setString(1, ht.getMaHang());
                    stmtThucPham.setDate(2, Date.valueOf(ht.getNgaySanXuat()));
                    stmtThucPham.setDate(3, Date.valueOf(ht.getNgayHetHan()));
                    stmtThucPham.setString(4, ht.getNhaCungCap());
                    stmtThucPham.executeUpdate();
                }

            } else if (hangHoa instanceof HangDienMay) {
                HangDienMay hd = (HangDienMay) hangHoa;
                String sqlDienMay = "INSERT INTO hangdienmay (maHang, thoiGianBaoHanh, congSuat) " +
                        "VALUES (?, ?, ?)";
                try (PreparedStatement stmtDienMay = conn.prepareStatement(sqlDienMay)) {
                    stmtDienMay.setString(1, hd.getMaHang());
                    stmtDienMay.setInt(2, hd.getThoiGianBaoHanh());
                    stmtDienMay.setDouble(3, hd.getCongSuat());
                    stmtDienMay.executeUpdate();
                }

            } else if (hangHoa instanceof HangSanhSu) {
                HangSanhSu hs = (HangSanhSu) hangHoa;
                String sqlSanhSu = "INSERT INTO hangsanhsu (maHang, nhaSanXuat, ngayNhapKho) " +
                        "VALUES (?, ?, ?)";
                try (PreparedStatement stmtSanhSu = conn.prepareStatement(sqlSanhSu)) {
                    stmtSanhSu.setString(1, hs.getMaHang());
                    stmtSanhSu.setString(2, hs.getNhaSanXuat());
                    stmtSanhSu.setDate(3, Date.valueOf(hs.getNgayNhapKho()));
                    stmtSanhSu.executeUpdate();
                }
            }

            conn.commit(); // Hoàn thành giao dịch
            return true;

        } catch (SQLException e) {
            throw new SQLException("Failed to save product", e);
        }
    }

    @Override
    public boolean removeProduct(String maHang) throws SQLException {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            String sqlDeleteLoaiHang = null;

            // Xác định loại sản phẩm dựa vào tiền tố của mã hàng
            if (maHang.startsWith("HTT")) {
                sqlDeleteLoaiHang = "DELETE FROM hangthucpham WHERE maHang = ?";
            } else if (maHang.startsWith("HDM")) {
                sqlDeleteLoaiHang = "DELETE FROM hangdienmay WHERE maHang = ?";
            } else if (maHang.startsWith("HSS")) {
                sqlDeleteLoaiHang = "DELETE FROM hangsanhsu WHERE maHang = ?";
            } else {
                throw new SQLException("Unknown product type for maHang: " + maHang);
            }

            // Xóa sản phẩm từ bảng loại hàng cụ thể
            try (PreparedStatement stmtDeleteLoaiHang = conn.prepareStatement(sqlDeleteLoaiHang)) {
                stmtDeleteLoaiHang.setString(1, maHang);
                stmtDeleteLoaiHang.executeUpdate();
            }

            // Xóa bản ghi trong bảng hanghoa
            String sqlDeleteHangHoa = "DELETE FROM hanghoa WHERE maHang = ?";
            try (PreparedStatement stmtDeleteHangHoa = conn.prepareStatement(sqlDeleteHangHoa)) {
                stmtDeleteHangHoa.setString(1, maHang);
                int rowsAffected = stmtDeleteHangHoa.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit(); // Hoàn thành giao dịch
                    return true; // Xóa thành công
                } else {
                    conn.rollback(); // Hoàn tác nếu không tìm thấy mã hàng trong bảng hanghoa
                    return false; // Không tìm thấy mã hàng để xóa
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Failed to remove product", e);
        }
    }


}
