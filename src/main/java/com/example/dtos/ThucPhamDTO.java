package com.example.dtos;

import com.example.dtos.HangHoaDTO;

import java.time.LocalDate;

public class ThucPhamDTO extends HangHoaDTO {
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public ThucPhamDTO(LocalDate ngayHetHan, LocalDate ngaySanXuat, String nhaCungCap, String maHang, String tenHang,
                       int soLuongTon, double donGia) {
        super(maHang, tenHang, soLuongTon, donGia);
    }

    public ThucPhamDTO() {
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
