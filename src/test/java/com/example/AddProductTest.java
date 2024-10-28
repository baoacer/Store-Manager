package com.example;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.dtos.ResponseDTO;
import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.addProductDTOs.ThucPhamDTO;
import com.example.ui.Controller;
import com.example.ui.View;
import com.example.usecase.addProduct.AddProductUseCase;
import com.example.dtos.addProductDTOs.HangHoaDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.usecase.*;
import org.junit.Test;

import com.example.database.MysqlDAO;
import com.example.ui.Presenter;

import static junit.framework.TestCase.assertEquals;

public class AddProductTest {
    @Test
    public void addProductOk() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                LocalDate.parse("2029-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Success::Thêm sản phẩm thành công", response.getContent() );
    }

    @Test
    public void addProductFail() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO)presenter.getResponse();

        assertEquals("Error::Lưu sản phẩm thất bại", response.getContent() );
    }

    @Test
    public void addProductFailMaHang() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Mã hàng không được để trống.", response.getContent());
    }

    @Test
    public void addProductFailTenHang() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "",
                50,
                200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Tên hàng không được để trống.", response.getContent());
    }

    @Test
    public void addProductFailSoLuong() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                -10,
                200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Số lượng không được âm.", response.getContent());
    }

    @Test
    public void addProductFailDonGia() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                -200000,
                "Sành sứ",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Đơn giá phải lớn hơn 0.", response.getContent());
    }


    @Test
    public void addProductFailNhaSanXuat() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                200000,
                "",
                LocalDate.parse("2024-10-25"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Nhà sản xuất không được để trống.", response.getContent());
    }


    @Test
    public void addProductFailNgayNhapKho() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "HSS1111",
                "Sanh Su Test",
                50,
                200000,
                "Sành sứ",
                null);

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Ngày nhập kho không được để trống.", response.getContent());
    }

    // TEST thuoc tinh rieng cua ThucPham
    @Test
    public void addProductFailNgaySanXuat() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new ThucPhamDTO(
                "TP1111",
                "Thực Phẩm Test",
                100,
                50000,
                null,
                LocalDate.parse("2024-12-01"),
                "Nhà Cung Cấp Test");

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Ngày sản xuất không được để trống.", response.getContent());
    }

    @Test
    public void addProductFailNgayHetHan() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new ThucPhamDTO(
                "TP1111",
                "Thực Phẩm Test",
                100,
                50000,
                LocalDate.parse("2024-10-01"),
                null,
                "Nhà Cung Cấp Test");

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Ngày hết hạn không được để trống.", response.getContent());
    }

    @Test
    public void addProductFailNgayHetHanBeforeNgaySanXuat() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new ThucPhamDTO(
                "TP1111",
                "Thực Phẩm Test",
                100,
                50000,
                LocalDate.parse("2024-12-01"),
                LocalDate.parse("2024-11-01"),
                "Nhà Cung Cấp Test");

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Ngày hết hạn phải sau ngày sản xuất.", response.getContent());
    }

    @Test
    public void addProductFailNhaCungCap() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new ThucPhamDTO(
                "TP1111",
                "Thực Phẩm Test",
                100,
                50000,
                LocalDate.parse("2024-10-01"),
                LocalDate.parse("2024-12-01"),
                "");

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Nhà cung cấp không được để trống.", response.getContent());
    }

    @Test
    public void addProductFailThoiGianBaoHanh() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new DienMayDTO(
                "DM1111",
                "Điện Máy Test",
                20,
                3000000,
                -12,
                1500);

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Thời gian bảo hành không được âm.", response.getContent());
    }

    @Test
    public void addProductFailCongSuat() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new DienMayDTO(
                "DM1111",
                "Điện Máy Test",
                20,
                3000000,
                12,
                0);

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Công suất phải lớn hơn 0.", response.getContent());
    }


    @Test
    public void addProductFailNhaSanXuatSanhSu() throws SQLException {
        DatabaseBoundary database = new MysqlDAO();
        Presenter presenter = new Presenter();
        AddProductUseCase addUseCase = new AddProductUseCase(presenter, database);

        // Data Test
        HangHoaDTO hangHoaDTO = new SanhSuDTO(
                "SS1111",
                "Sành Sứ Test",
                30,
                150000,
                "",
                LocalDate.parse("2024-10-01"));

        addUseCase.execute(hangHoaDTO);
        ResponseDTO response = (ResponseDTO) presenter.getResponse();

        assertEquals("Error::Nhà sản xuất không được để trống.", response.getContent());
    }


}
