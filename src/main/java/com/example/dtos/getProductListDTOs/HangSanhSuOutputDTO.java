package com.example.dtos.getProductListDTOs;

import java.time.LocalDate;

public class HangSanhSuOutputDTO extends HangHoaOutputDTO {
    private String nhaSanXuat; // Nhà sản xuất
    private LocalDate ngayNhapKho;

    public HangSanhSuOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double vAT,
            String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia, vAT);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    @Override
    public String toString() {
        return "{ \"maHang\": \"" + maHang + "\", \"tenHang\": \"" + tenHang + "\", \"soLuongTon\": " + soLuongTon
                + ", \"donGia\": " + donGia + ", \"VAT\": " + VAT + ", \"nhaSanXuat\": \"" + nhaSanXuat
                + "\", \"ngayNhapKho\": \"" + ngayNhapKho + "\" }";
    }
}
