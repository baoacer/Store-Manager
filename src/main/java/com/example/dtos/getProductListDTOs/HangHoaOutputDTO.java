package com.example.dtos.getProductListDTOs;

public class HangHoaOutputDTO {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;
    protected double VAT;

    public HangHoaOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double vAT) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = vAT;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getVAT() {
        return VAT;
    }

    @Override
    public String toString() {
        return "{ \"maHang\": \"" + maHang + "\", \"tenHang\": \"" + tenHang + "\", \"soLuongTon\": " + soLuongTon
                + ", \"donGia\": " + donGia + ", \"VAT\": " + VAT + " }";
    }
}
