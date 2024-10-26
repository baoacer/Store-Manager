package com.example.dtos.getProductListDTOs;

import java.time.LocalDate;

public class HangThucPhamOutputDTO extends HangHoaOutputDTO {
    private LocalDate ngaySanXuat; // Ngày sản xuất (yyyy-MM-dd)
    private LocalDate ngayHetHan; // Ngày hết hạn (yyyy-MM-dd)
    private String nhaCungCap;

    public HangThucPhamOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double vAT,
            LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia, vAT);
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

    @Override
    public String toString() {
        return "{ \"maHang\": \"" + maHang + "\", \"tenHang\": \"" + tenHang + "\", \"soLuongTon\": " + soLuongTon
                + ", \"donGia\": " + donGia + ", \"VAT\": " + VAT + ", \"ngaySanXuat\": \"" + ngaySanXuat
                + "\", \"ngayHetHan\": \"" + ngayHetHan + "\", \"nhaCungCap\": \"" + nhaCungCap + "\" }";
    }

}
