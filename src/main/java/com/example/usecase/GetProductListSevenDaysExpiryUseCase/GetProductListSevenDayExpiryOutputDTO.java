package com.example.usecase.GetProductListSevenDaysExpiryUseCase;

import java.util.ArrayList;
import java.util.List;

import com.example.dtos.ProductDTO;
import com.example.usecase.ResponseData;

public class GetProductListSevenDayExpiryOutputDTO implements ResponseData {

    protected List<ProductDTO> list = new ArrayList<>();

    public List<ProductDTO> getList() {
        return list;
    }

    public void setList(List<ProductDTO> list) {
        this.list = list;
    }
}
