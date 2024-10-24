package com.example.usecase.GetProductListSevenDaysExpiryUseCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.dtos.ProductDTO;
import com.example.entity.HangHoa;
import com.example.entity.HangThucPham;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.InputBoundary;
import com.example.usecase.OutputBoundary;
import com.example.usecase.RequestData;

public class GetProductListSevenDayExpiryUseCase implements InputBoundary {
    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetProductListSevenDayExpiryUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) {
        List<HangHoa> listHangHoa = databaseBoundary.getAllProductList();
        List<ProductDTO> listProductDTO = findProduct(listHangHoa);
        GetProductListSevenDayExpiryOutputDTO getProductListSevenDayExpiryOutputDTO = new GetProductListSevenDayExpiryOutputDTO();
        getProductListSevenDayExpiryOutputDTO.setList(listProductDTO);
        outputBoundary.exportResult(getProductListSevenDayExpiryOutputDTO);
    }

    private List<ProductDTO> findProduct(List<HangHoa> listProduct) {
        LocalDate dateNow = LocalDate.now();
        List<ProductDTO> list = new ArrayList<>();

        for (HangHoa hangHoa : listProduct) {
            // Chỉ xử lý các sản phẩm thuộc lớp HangThucPham
            if (hangHoa instanceof HangThucPham thucPham) {
                LocalDate ngayHetHan = thucPham.getNgayHetHan();

                // Tính toán số ngày còn lại
                long daysUntilExpiry = java.time.temporal.ChronoUnit.DAYS.between(dateNow, ngayHetHan);

                // Kiểm tra nếu ngày hết hạn trong khoảng từ 1 đến 7 ngày
                if (daysUntilExpiry >= 0 && daysUntilExpiry <= 7) {
                    ProductDTO productSevenDaysExpiry = new ProductDTO(thucPham.getMaHang(),
                            thucPham.getTenHang(), thucPham.getSoLuongTon(), thucPham.getDonGia(), thucPham.tinhVat());
                    list.add(productSevenDaysExpiry);
                }
            }
        }

        return list;
    }

}
