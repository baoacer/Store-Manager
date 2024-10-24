package com.example.usecase.GetProductListUseCase;

import java.util.ArrayList;
import java.util.List;

import com.example.dtos.ProductDTO;
import com.example.entity.HangHoa;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.InputBoundary;
import com.example.usecase.OutputBoundary;
import com.example.usecase.RequestData;

public class GetProductListUseCase implements InputBoundary {
    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetProductListUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) {
        // gọi hàm lấy dữ liệu trong database
        List<HangHoa> listHangHoa = databaseBoundary.getAllProductList();

        // tạo 1 mảng chứa dữ liệu kiểu ProductDTO
        List<ProductDTO> listProductDTO = new ArrayList<>();

        for (HangHoa hangHoa : listHangHoa) {

            ProductDTO productDTO = new ProductDTO(hangHoa.getMaHang(), hangHoa.getTenHang(), hangHoa.getSoLuongTon(),
                    hangHoa.getDonGia(), hangHoa.tinhVat());
            listProductDTO.add(productDTO);
        }
        GetProductListOutputDTO getProductListOutputDTO = new GetProductListOutputDTO();
        getProductListOutputDTO.setList(listProductDTO);
        outputBoundary.exportResult(getProductListOutputDTO);
    }

}
