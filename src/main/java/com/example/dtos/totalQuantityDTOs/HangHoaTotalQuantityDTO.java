package com.example.dtos.totalQuantityDTOs;

public class HangHoaTotalQuantityDTO {
    private int totalQuantity;

    public HangHoaTotalQuantityDTO(int totalQuantityDienMay) {
        this.totalQuantity = totalQuantityDienMay;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}
