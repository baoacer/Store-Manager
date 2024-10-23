package com.example.dtos;

import com.example.usecase.RequestData;

public class RemoveDTO implements RequestData {
    private String maHang;

    public RemoveDTO(String maHang) {
        this.maHang = maHang;
    }

    public RemoveDTO() {
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMaHang() {
        return maHang;
    }
}
