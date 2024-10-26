package com.example.dtos.totalQuantityDTOs;

import com.example.usecase.ResponseData;

public class TotalQuantitySanhSuOutputDTO extends HangHoaTotalQuantityDTO implements ResponseData {

    public TotalQuantitySanhSuOutputDTO(int totalQuantityDienMay) {
        super(totalQuantityDienMay);
    }

}
