package com.example.usecase.getProductListUseCase;

import java.util.ArrayList;
import java.util.List;

import com.example.dtos.getProductListDTOs.HangHoaOutputDTO;
import com.example.usecase.ResponseData;

public class GetProductListOutputDTO implements ResponseData {

    protected List<HangHoaOutputDTO> list = new ArrayList<>();

    public List<HangHoaOutputDTO> getList() {
        return list;
    }

    public void setList(List<HangHoaOutputDTO> list) {
        this.list = list;
    }

}
