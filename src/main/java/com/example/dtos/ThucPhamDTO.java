package com.example.dtos;

import java.time.LocalDate;

public class ThucPhamDTO extends HangHoaDTO{

    private LocalDate ngaySanXuat; // Ngày sản xuất (yyyy-MM-dd)
    private LocalDate ngayHetHan;      // Ngày hết hạn (yyyy-MM-dd)
    private String nhaCungCap;

    public ThucPhamDTO(String maHang, String tenHang, int soLuong, double donGia, String loaiSanPham, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuong, donGia, loaiSanPham);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }
}
