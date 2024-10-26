package com.example.dtos.getProductListDTOs;

public class HangDienMayOutputDTO extends HangHoaOutputDTO {
    private int thoiGianBaoHanh; // Số tháng bảo hành (>= 0)
    private double congSuat;

    public HangDienMayOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double vAT,
            int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia, vAT);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }
    //

    @Override
    public String toString() {
        return "{ \"maHang\": \"" + maHang + "\", \"tenHang\": \"" + tenHang + "\", \"soLuongTon\": " + soLuongTon
                + ", \"donGia\": " + donGia + ", \"VAT\": " + VAT + ", \"thoiGianBaoHanh\": " + thoiGianBaoHanh
                + ", \"congSuat\": " + congSuat + " }";
    }
}
