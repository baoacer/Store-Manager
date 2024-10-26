package com.example.dtos.totalQuantityDTOs;

import com.example.usecase.ResponseData;

public class TotalQuantityDienMayOutputDTO extends HangHoaTotalQuantityDTO implements ResponseData {

    public TotalQuantityDienMayOutputDTO(int totalQuantityDienMay) {
        super(totalQuantityDienMay);
    }
}
