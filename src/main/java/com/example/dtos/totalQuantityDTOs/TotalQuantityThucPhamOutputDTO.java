package com.example.dtos.totalQuantityDTOs;

import com.example.usecase.ResponseData;

public class TotalQuantityThucPhamOutputDTO extends HangHoaTotalQuantityDTO implements ResponseData {

    public TotalQuantityThucPhamOutputDTO(int totalQuantityDienMay) {
        super(totalQuantityDienMay);
    }

}
