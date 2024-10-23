package com.example.entity;

public class HangDienMay extends HangHoa {
    private int thoiGianBaoHanh; // Số tháng
    private double congSuat; // KW


    public HangDienMay(double congSuat, int thoiGianBaoHanh, String maHang, String tenHang, int soLuongTon,
            double donGia) {

        super(maHang, tenHang, soLuongTon, donGia);
        this.congSuat = congSuat;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    @Override
    public double tinhVat() {
        return getDonGia() * 0.1;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }
}
