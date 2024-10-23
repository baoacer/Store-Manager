package com.example.dtos;

import com.example.usecase.RequestData;

public class HangHoaDTO implements RequestData {

    // Chung
    protected String maHang;
    protected String tenHang;
    protected int soLuong;
    protected double donGia;
    protected String loaiSanPham; // Loại sản phẩm: "thực phẩm", "điện máy", "sành sứ"

    public HangHoaDTO(String maHang, String tenHang, int soLuong, double donGia, String loaiSanPham) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.loaiSanPham = loaiSanPham;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }
}
