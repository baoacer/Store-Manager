package com.example.database;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.usecase.DatabaseBoundary;

public class MysqlDAO implements DatabaseBoundary {

    public MysqlDAO() {
    }

    @Override
    public boolean saveProduct(HangHoa hangHoa) throws SQLException {
        try (Connection conn = MysqlConnection.getConnection()) {
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
        try (Connection conn = MysqlConnection.getConnection()) {
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
                            resultSet1.getString("maHang"),
                            resultSet1.getString("tenHang"),
                            resultSet1.getInt("soLuongTon"),
                            resultSet1.getDouble("donGia"),
                            resultSet1.getDouble("congSuat"),
                            resultSet1.getInt("thoiGianBaoHanh"));
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng sành sứ
            try (ResultSet resultSet2 = statement.executeQuery(sqlQueryHangSanhsu)) {
                while (resultSet2.next()) {
                    LocalDate ngayNhapKho = resultSet2.getDate("ngayNhapKho").toLocalDate();
                    HangHoa hangHoa = new HangSanhSu(
                            resultSet2.getString("tenHang"),
                            resultSet2.getString("maHang"),
                            resultSet2.getInt("soLuongTon"),
                            resultSet2.getDouble("donGia"),
                            ngayNhapKho,
                            resultSet2.getString("nhaSanXuat"));
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng thực phẩm
            try (ResultSet resultSet3 = statement.executeQuery(sqlQueryHangThucPham)) {
                while (resultSet3.next()) {
                    LocalDate ngaySanXuat = resultSet3.getDate("ngaySanXuat").toLocalDate();
                    LocalDate ngayHetHan = resultSet3.getDate("ngayHetHan").toLocalDate();
                    HangHoa hangHoa = new HangThucPham(
                            resultSet3.getString("maHang"),
                            resultSet3.getString("tenHang"),
                            resultSet3.getInt("soLuongTon"),
                            resultSet3.getDouble("donGia"),
                            ngaySanXuat,
                            ngayHetHan,
                            resultSet3.getString("nhaCungCap"));
                    productDB.add(hangHoa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDB; // Trả danh sách đối tượng HangHoa
    }
}
