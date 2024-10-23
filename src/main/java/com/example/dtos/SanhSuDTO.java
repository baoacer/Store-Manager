package com.example.dtos;

import java.time.LocalDate;

public class SanhSuDTO extends HangHoaDTO{
    private String nhaSanXuat;    // Nhà sản xuất
    private LocalDate ngayNhapKho;

    public SanhSuDTO(String maHang, String tenHang, int soLuong, double donGia, String loaiSanPham, String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuong, donGia, loaiSanPham);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }
}
