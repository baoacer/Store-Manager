package com.example.entity;

import java.time.LocalDate;

public class HangSanhSu extends HangHoa {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public HangSanhSu(LocalDate ngayNhapKho, String nhaSanXuat, String maHang, String tenHang, int soLuongTon,
            double donGia) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.ngayNhapKho = ngayNhapKho;
        this.nhaSanXuat = nhaSanXuat;
    }

    @Override
    public double tinhVat() {
        return getDonGia() * 0.1;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(LocalDate ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }
}
