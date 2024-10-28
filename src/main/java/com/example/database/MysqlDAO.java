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
import com.example.usecase.RequestData;

import java.sql.*;

public class MySqlDAO implements DatabaseBoundary {
    private Connection connection = null;


    public MysqlDAO() {
    }

    @Override
    public List<HangHoa> getAllProductList() {
        List<HangHoa> productDB = new ArrayList<>();

        String sqlQueryHangDienMay = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, d.thoiGianBaoHanh, d.congSuat " +
                "FROM hanghoa h INNER JOIN hangdienmay d ON h.maHang = d.maHang";

        String sqlQueryHangSanhsu = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, s.nhaSanXuat, s.ngayNhapKho " +
                "FROM hanghoa h INNER JOIN hangsanhsu s ON h.maHang = s.maHang";

        String sqlQueryHangThucPham = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, t.ngaySanXuat, t.ngayHetHan, t.nhaCungCap " +
                "FROM hanghoa h INNER JOIN hangthucpham t ON h.maHang = t.maHang";

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
                            resultSet1.getInt("thoiGianBaoHanh")
                    );
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng sành sứ
            try (ResultSet resultSet2 = statement.executeQuery(sqlQueryHangSanhsu)) {
                while (resultSet2.next()) {
                    LocalDate ngayNhapKho = resultSet2.getDate("ngayNhapKho") != null ?
                            resultSet2.getDate("ngayNhapKho").toLocalDate() : null;
                    HangHoa hangHoa = new HangSanhSu(
                            resultSet2.getString("maHang"),
                            resultSet2.getString("tenHang"),
                            resultSet2.getInt("soLuongTon"),
                            resultSet2.getDouble("donGia"),
                            ngayNhapKho,
                            resultSet2.getString("nhaSanXuat")
                    );
                    productDB.add(hangHoa);
                }
            }

            // Lấy hàng thực phẩm
            try (ResultSet resultSet3 = statement.executeQuery(sqlQueryHangThucPham)) {
                while (resultSet3.next()) {
                    LocalDate ngaySanXuat = resultSet3.getDate("ngaySanXuat") != null ?
                            resultSet3.getDate("ngaySanXuat").toLocalDate() : null;
                    LocalDate ngayHetHan = resultSet3.getDate("ngayHetHan") != null ?
                            resultSet3.getDate("ngayHetHan").toLocalDate() : null;
                    HangHoa hangHoa = new HangThucPham(
                            resultSet3.getString("maHang"),
                            resultSet3.getString("tenHang"),
                            resultSet3.getInt("soLuongTon"),
                            resultSet3.getDouble("donGia"),
                            ngayHetHan,
                            ngaySanXuat,
                            resultSet3.getString("nhaCungCap")
                    );
                    productDB.add(hangHoa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return productDB;
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
           return false;
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
           return false;
        }
    }

    @Override
    public int getTotalQuantityDienMay() throws SQLException {
        String query = "SELECT SUM(hh.soLuongTon) " +
                "FROM hanghoa hh " +
                "INNER JOIN hangdienmay hdm ON hh.maHang = hdm.maHang";
        return getTotalQuantity(query, "Khong the lay so luong dien may");
    }


    @Override
    public int getTotalQuantitySanhSu() throws SQLException {
        String query = "SELECT SUM(hh.soLuongTon) " +
                "FROM hanghoa hh " +
                "INNER JOIN hangsanhsu hss ON hh.maHang = hss.maHang";
        return getTotalQuantity(query, "Khong the lay so luong sanh su");
    }


    @Override
    public int getTotalQuantityThucPham() throws SQLException {
        String query = "SELECT SUM(hh.soLuongTon) " +
                "FROM hanghoa hh " +
                "INNER JOIN hangthucpham htp ON hh.maHang = htp.maHang";
        return getTotalQuantity(query, "Khong the lay so luong thuc pham");
    }


    private int getTotalQuantity(String query, String errorMessage) throws SQLException {

        connection = MysqlConnection.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        int totalQuantity = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                totalQuantity = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(errorMessage + ": " + e.getMessage());
        } finally {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
        }

        return totalQuantity;
    }


    @Override
    public String updateProductDienMay(HangDienMay hangDienMay) throws SQLException {
        String queryHangDienMay = "UPDATE hangdienmay SET thoiGianBaoHanh = ?, congSuat = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try {

            Connection connection = MysqlConnection.getConnection();



            try (PreparedStatement preparedStatementHangDienMay = connection.prepareStatement(queryHangDienMay)) {


                preparedStatementHangDienMay.setInt(1, hangDienMay.getThoiGianBaoHanh());
                preparedStatementHangDienMay.setDouble(2, hangDienMay.getCongSuat());
                preparedStatementHangDienMay.setString(3, hangDienMay.getMaHang());

                preparedStatementHangDienMay.executeUpdate();
            }

            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {

                preparedStatementHangHoa.setString(1, hangDienMay.getTenHang());
                preparedStatementHangHoa.setInt(2, hangDienMay.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangDienMay.getDonGia());
                preparedStatementHangHoa.setString(4, hangDienMay.getMaHang());

                preparedStatementHangHoa.executeUpdate();
            }

            return "Dien may cap nhat thanh cong!";

        } catch (SQLException e) {
            try (Connection connection = MysqlConnection.getConnection()) {
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("loi rollback: " + rollbackException.getMessage());
            }
            return "Loi khi cap nhat dien may";
        }
    }


    @Override
    public String updateProductThucPham(HangThucPham hangThucPham) throws SQLException {
        String queryHangThucPham = "UPDATE hangthucpham SET ngaySanXuat = ?, ngayHetHan = ?, nhaCungCap = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try {
            Connection connection = MysqlConnection.getConnection();
            try (PreparedStatement preparedStatementHangThucPham = connection.prepareStatement(queryHangThucPham)) {
                preparedStatementHangThucPham.setDate(1, java.sql.Date.valueOf(hangThucPham.getNgaySanXuat()));
                preparedStatementHangThucPham.setDate(2, java.sql.Date.valueOf(hangThucPham.getNgayHetHan()));
                preparedStatementHangThucPham.setString(3, hangThucPham.getNhaCungCap());
                preparedStatementHangThucPham.setString(4, hangThucPham.getMaHang());

                preparedStatementHangThucPham.executeUpdate();
            }


            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {



                preparedStatementHangHoa.setString(1, hangThucPham.getTenHang());
                preparedStatementHangHoa.setInt(2, hangThucPham.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangThucPham.getDonGia());
                preparedStatementHangHoa.setString(4, hangThucPham.getMaHang());

                preparedStatementHangHoa.executeUpdate();
            }

            return "Thuc pham cap nhat thanh cong!";

        } catch (SQLException e) {

            try (Connection connection = MysqlConnection.getConnection()) {
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("loi rollback: " + rollbackException.getMessage());
            }

           return "Loi cap nhat du lieu";
        }
    }


    @Override
    public String updateProductSanhSu(HangSanhSu hangSanhSu) throws SQLException {
        String queryHangSanhSu = "UPDATE hangsangsu SET nhaSanXuat = ?, ngayNhapKho = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try {
            Connection connection = MysqlConnection.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatementHangSanhSu = connection.prepareStatement(queryHangSanhSu)) {

                preparedStatementHangSanhSu.setString(1, hangSanhSu.getNhaSanXuat());
                preparedStatementHangSanhSu.setDate(2, java.sql.Date.valueOf(hangSanhSu.getNgayNhapKho()));
                preparedStatementHangSanhSu.setString(3, hangSanhSu.getMaHang());

                preparedStatementHangSanhSu.executeUpdate();
            }


            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {

                preparedStatementHangHoa.setString(1, hangSanhSu.getTenHang());
                preparedStatementHangHoa.setInt(2, hangSanhSu.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangSanhSu.getDonGia());
                preparedStatementHangHoa.setString(4, hangSanhSu.getMaHang());

                preparedStatementHangHoa.executeUpdate();
            }

            connection.commit();
            return "Sanh su cap nhat thanh cong!";

        } catch (SQLException e) {
            try (Connection connection = MysqlConnection.getConnection()) {
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.err.println("loi rollback: " + rollbackException.getMessage());
            }

            return "loi cap nhat du lieu sanh su";
        }
    }
}
