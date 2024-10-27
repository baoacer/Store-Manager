package com.example.usecase.addProduct;

import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.addProductDTOs.HangHoaDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.dtos.addProductDTOs.ThucPhamDTO;
import com.example.usecase.InputBoundary;
import com.example.dtos.*;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.usecase.DatabaseBoundary;
import com.example.usecase.OutputBoundary;
import com.example.usecase.RequestData;

import java.sql.SQLException;

public class AddProductUseCase implements InputBoundary {

    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public AddProductUseCase(OutputBoundary outputBoundary,
                             DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        ResponseDTO addResponse = new ResponseDTO();
        HangHoaDTO dto = (HangHoaDTO) requestData;

        if(validate(dto)){
            HangHoa hangHoa = null;

            if(requestData instanceof ThucPhamDTO){
                hangHoa = new HangThucPham(
                        dto.getMaHang(),
                        dto.getTenHang(),
                        dto.getSoLuong(),
                        dto.getDonGia(),
                        ((ThucPhamDTO) dto).getNgaySanXuat(),
                        ((ThucPhamDTO) dto).getNgayHetHan(),
                        ((ThucPhamDTO) dto).getNhaCungCap()
                );
            } else if (requestData instanceof DienMayDTO) {
                hangHoa = new HangDienMay(
                        dto.getMaHang(),
                        dto.getTenHang(),
                        dto.getSoLuong(),
                        dto.getDonGia(),
                        ((DienMayDTO)dto).getCongSuat(),
                        ((DienMayDTO)dto).getThoiGianBaoHanh());
            }else{
                hangHoa = new HangSanhSu(
                        dto.getMaHang(),
                        dto.getTenHang(),
                        dto.getSoLuong(),
                        dto.getDonGia(),
                        ((SanhSuDTO)dto).getNgayNhapKho(),
                        ((SanhSuDTO)dto).getNhaSanXuat());
            }

            if (hangHoa != null) {
                boolean isSave = databaseBoundary.saveProduct(hangHoa);
                if (isSave) {
                    addResponse.setContent("Success::Thêm sản phẩm thành công");
                    outputBoundary.exportResult(addResponse);
//                    outputBoundary.addProductPresenter(addResponse);
                }
            }
        }else {
            addResponse.setContent("Error::Kiểm tra lại dữ liệu");
            outputBoundary.exportResult(addResponse);
//            outputBoundary.addProductPresenter(addResponse);
        }
    }



    public boolean validate(HangHoaDTO data) {
        // Kiểm tra các thuộc tính chung
        if (data.getMaHang() == null || data.getMaHang().isEmpty()) {
            return false;
        }
        if (data.getTenHang() == null || data.getTenHang().isEmpty()) {
            return false;
        }
        if (data.getSoLuong() < 0) {
            return false;
        }
        if (data.getDonGia() <= 0) {
            return false;
        }

        // Kiểm tra thuộc tính đặc thù dựa vào loại sản phẩm
        if(data instanceof ThucPhamDTO){
            ThucPhamDTO thucPham = (ThucPhamDTO)data;
            if (thucPham.getNgaySanXuat() == null) {
                return false;
            }
            if (thucPham.getNgayHetHan() == null) {
                return false;
            }
            if (!thucPham.getNgayHetHan().isAfter(thucPham.getNgaySanXuat())) {
                return false;
            }
            if (thucPham.getNhaCungCap() == null || thucPham.getNhaCungCap().isEmpty()) {
                return false;
            }
        } else if (data instanceof DienMayDTO) {
            DienMayDTO dienMay = (DienMayDTO)data;
            if (dienMay.getThoiGianBaoHanh() < 0) {
                return false;
            }
            if (dienMay.getCongSuat() <= 0) {
                return false;
            }
        }else{
            SanhSuDTO sanhSu = (SanhSuDTO) data;
            if (sanhSu.getNhaSanXuat() == null || sanhSu.getNhaSanXuat().isEmpty()) {
                return false;
            }
            if (sanhSu.getNgayNhapKho() == null) {
                return false;
            }
        }
        // Nếu tất cả kiểm tra đều hợp lệ
        return true;
    }
}
