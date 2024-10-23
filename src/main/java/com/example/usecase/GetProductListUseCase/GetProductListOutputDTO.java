package com.example.usecase.GetProductListUseCase;

import java.util.ArrayList;
import java.util.List;

import com.example.dtos.ProductDTO;
import com.example.usecase.ResponseData;

public class GetProductListOutputDTO implements ResponseData {

    protected List<ProductDTO> list = new ArrayList<>();

    public List<ProductDTO> getList() {
        return list;
    }

    public void setList(List<ProductDTO> list) {
        this.list = list;
    }

}
