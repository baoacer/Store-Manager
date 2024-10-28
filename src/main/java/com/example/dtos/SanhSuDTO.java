package com.example.dtos;

import com.example.dtos.HangHoaDTO;

import java.time.LocalDate;

public class SanhSuDTO extends HangHoaDTO {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public SanhSuDTO(String maHang, String tenHang, int soLuongTon, double donGia) {
        super(maHang, tenHang, soLuongTon, donGia);
    }

    public SanhSuDTO() {
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

}
