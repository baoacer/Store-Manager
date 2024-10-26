package com.example.usecase.getListProductExpired;

import java.util.ArrayList;
import java.util.List;

import com.example.dtos.getProductListDTOs.HangHoaOutputDTO;
import com.example.usecase.ResponseData;

public class GetProductListSevenDayExpiryOutputDTO implements ResponseData {

    protected List<HangHoaOutputDTO> list = new ArrayList<>();

    public List<HangHoaOutputDTO> getList() {
        return list;
    }

    public void setList(List<HangHoaOutputDTO> list) {
        this.list = list;
    }
}
