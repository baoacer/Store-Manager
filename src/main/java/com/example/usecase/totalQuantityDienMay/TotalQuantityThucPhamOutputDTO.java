package com.example.usecase.totalQuantityDienMay;

import com.example.usecase.ResponseData;

public class TotalQuantityThucPhamOutputDTO extends HangHoaTotalQuantityDTO implements ResponseData {

    public TotalQuantityThucPhamOutputDTO(int totalQuantityDienMay) {
        super(totalQuantityDienMay);
    }

}
