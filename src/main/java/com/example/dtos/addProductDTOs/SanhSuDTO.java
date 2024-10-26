package com.example.dtos.addProductDTOs;

import java.time.LocalDate;

public class SanhSuDTO extends HangHoaDTO{
    private String nhaSanXuat;    // Nhà sản xuất
    private LocalDate ngayNhapKho;

    public SanhSuDTO(String maHang, String tenHang, int soLuong, double donGia, String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuong, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public SanhSuDTO(){}

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }
}
