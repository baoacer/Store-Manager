package com.example.dtos.addProductDTOs;

import java.time.LocalDate;

public class ThucPhamDTO extends HangHoaDTO{

    private LocalDate ngaySanXuat; // Ngày sản xuất (yyyy-MM-dd)
    private LocalDate ngayHetHan;      // Ngày hết hạn (yyyy-MM-dd)
    private String nhaCungCap;

    public ThucPhamDTO(String maHang, String tenHang, int soLuong, double donGia, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuong, donGia);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public ThucPhamDTO(){}

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
