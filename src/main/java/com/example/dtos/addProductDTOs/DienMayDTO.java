package com.example.dtos.addProductDTOs;

public class DienMayDTO extends HangHoaDTO{
    private int thoiGianBaoHanh;     // Số tháng bảo hành (>= 0)
    private double congSuat;

    public DienMayDTO(String maHang, String tenHang, int soLuong, double donGia, int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuong, donGia);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public DienMayDTO(){}

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }
}
