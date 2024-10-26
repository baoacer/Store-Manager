package com.example.ui;

public class ViewModel {
    public String maHang;
    public String tenHang;
    public int soLuongTon;
    public double donGia;

    public ViewModel(String maHang, int soLuongTon, String tenHang, double donGia) {
        this.maHang = maHang;
        this.soLuongTon = soLuongTon;
        this.tenHang = tenHang;
        this.donGia = donGia;
    }
}
