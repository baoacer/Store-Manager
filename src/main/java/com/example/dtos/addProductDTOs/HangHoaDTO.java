package com.example.dtos.addProductDTOs;

import com.example.usecase.RequestData;

public class HangHoaDTO implements RequestData {

    // Chung
    protected String maHang;
    protected String tenHang;
    protected int soLuong;
    protected double donGia;

    public HangHoaDTO(String maHang, String tenHang, int soLuong, double donGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HangHoaDTO(){}

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

}
