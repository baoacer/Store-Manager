package com.example.usecase.updateProduct;

<<<<<<< HEAD
import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.dtos.addProductDTOs.ThucPhamDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.usecase.*;
import com.example.dtos.updateOutputDTO.UpdateDienMayOutPutDTO;
import com.example.dtos.updateOutputDTO.UpdateSanhSuOutputDTO;
import com.example.dtos.updateOutputDTO.UpdateThucPhamOutputDTO;
=======
import com.example.dtos.DienMayDTO;
import com.example.dtos.SanhSuDTO;
import com.example.dtos.ThucPhamDTO;
import com.example.usecase.*;
import com.example.usecase.updateProduct.OutputDTO.UpdateDienMayOutPutDTO;
import com.example.usecase.updateProduct.OutputDTO.UpdateSanhSuOutputDTO;
import com.example.usecase.updateProduct.OutputDTO.UpdateThucPhamOutputDTO;
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3

import java.sql.SQLException;

public class UpdateProductUseCase implements InputBoundary {

    private DatabaseBoundary databaseBoundary = null;
    private OutputBoundary outputBoundary = null;

    public UpdateProductUseCase(DatabaseBoundary databaseBoundary, OutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        if (requestData instanceof DienMayDTO) {
<<<<<<< HEAD
            DienMayDTO dienMayDto = (DienMayDTO)requestData;

            if(dienMayDto.getMaHang().isEmpty() || dienMayDto.getTenHang().isEmpty() || dienMayDto.getSoLuong() < 0 || dienMayDto.getDonGia() <= 0 || dienMayDto.getCongSuat() <= 0 || dienMayDto.getThoiGianBaoHanh() < 0){
                ResponseData responseData = new UpdateDienMayOutPutDTO("Thieu thong tin can thiet");
                outputBoundary.exportResult(responseData);
                return;
            }

            HangDienMay hangDienMay = new HangDienMay(dienMayDto.getMaHang(),dienMayDto.getTenHang(), dienMayDto.getSoLuong(), dienMayDto.getDonGia(),dienMayDto.getCongSuat(),dienMayDto.getThoiGianBaoHanh());
            String message = databaseBoundary.updateProductDienMay(hangDienMay);

            ResponseData responseData = new UpdateDienMayOutPutDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof SanhSuDTO) {
            SanhSuDTO sanhSuDTO = (SanhSuDTO)requestData;

            if(sanhSuDTO.getMaHang().isEmpty() || sanhSuDTO.getTenHang().isEmpty() || sanhSuDTO.getSoLuong() < 0 || sanhSuDTO.getDonGia() <= 0 || sanhSuDTO.getNgayNhapKho() == null || sanhSuDTO.getNhaSanXuat().isEmpty()){
                ResponseData responseData = new UpdateSanhSuOutputDTO("Thieu thong tin can thiet");
                outputBoundary.exportResult(responseData);
                return;
            }

            HangSanhSu hangSanhSu = new HangSanhSu(sanhSuDTO.getMaHang(), sanhSuDTO.getTenHang(), sanhSuDTO.getSoLuong(), sanhSuDTO.getDonGia(), sanhSuDTO.getNgayNhapKho(),sanhSuDTO.getNhaSanXuat());
           String message = databaseBoundary.updateProductSanhSu(hangSanhSu);
            ResponseData responseData = new UpdateSanhSuOutputDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof ThucPhamDTO) {
            ThucPhamDTO thucPhamDTO = (ThucPhamDTO)requestData;

            if(thucPhamDTO.getMaHang().isEmpty() || thucPhamDTO.getTenHang().isEmpty() || thucPhamDTO.getSoLuong() < 0 || thucPhamDTO.getDonGia() <= 0 || thucPhamDTO.getNgaySanXuat() == null || thucPhamDTO.getNhaCungCap().isEmpty()){
                ResponseData responseData = new UpdateThucPhamOutputDTO("Thieu thong tin can thiet");
                outputBoundary.exportResult(responseData);
                return;
            }

            HangThucPham hangThucPham = new HangThucPham(thucPhamDTO.getMaHang(), thucPhamDTO.getTenHang(),thucPhamDTO.getSoLuong(), thucPhamDTO.getDonGia(), thucPhamDTO.getNgaySanXuat(), thucPhamDTO.getNgayHetHan(),thucPhamDTO.getNhaCungCap());
            String message = databaseBoundary.updateProductThucPham(hangThucPham);
=======
            String message = databaseBoundary.updateProductDienMay(requestData);
            ResponseData responseData = new UpdateDienMayOutPutDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof SanhSuDTO) {
           String message = databaseBoundary.updateProductSanhSu(requestData);
            ResponseData responseData = new UpdateSanhSuOutputDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof ThucPhamDTO) {
            String message = databaseBoundary.updateProductThucPham(requestData);
>>>>>>> 5ad7ea67ce9d350718af88c48a0fd930d97851f3
            ResponseData responseData = new UpdateThucPhamOutputDTO(message);
            outputBoundary.exportResult(responseData);
        }
    }
}
