package com.example.entity;

import java.time.LocalDate;

public class HangThucPham extends HangHoa {
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public HangThucPham(LocalDate ngayHetHan, LocalDate ngaySanXuat, String nhaCungCap, String maHang, String tenHang,
            int soLuongTon, double donGia) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.ngayHetHan = ngayHetHan;
        this.ngaySanXuat = ngaySanXuat;
        this.nhaCungCap = nhaCungCap;
    }

    public HangThucPham() {

    }

    @Override
    public double tinhVat() {
        return getDonGia() * 0.05;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

}