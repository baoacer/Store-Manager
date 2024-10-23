package com.example.usecase.AddProduct;

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

        String loaiSanPham = dto.getLoaiSanPham();

        if(validate(dto, loaiSanPham)){
            HangHoa hangHoa = null;

            switch (loaiSanPham) {
                case "Thực phẩm":
                    hangHoa = new HangThucPham(dto.getMaHang(), dto.getTenHang(), dto.getSoLuong(), dto.getDonGia(), ((ThucPhamDTO)dto).getNgayHetHan(), ((ThucPhamDTO)dto).getNgaySanXuat(), ((ThucPhamDTO)dto).getNhaCungCap());
                    break;
                case "Điện máy":
                    hangHoa = new HangDienMay(dto.getMaHang(), dto.getTenHang(), dto.getSoLuong(), dto.getDonGia(), ((DienMayDTO)dto).getCongSuat(), ((DienMayDTO)dto).getThoiGianBaoHanh());
                    break;
                case "Sành sứ":
                    hangHoa = new HangSanhSu(dto.getMaHang(), dto.getTenHang(), dto.getSoLuong(), dto.getDonGia(), ((SanhSuDTO)dto).getNgayNhapKho(), ((SanhSuDTO)dto).getNhaSanXuat());
                    break;
                default:
                    break;
            }

            if (hangHoa != null) {
                boolean isSave = databaseBoundary.saveProduct(hangHoa);
                if (isSave) {
                    addResponse.setContent("Success::Thêm sản phẩm thành công");
                    outputBoundary.exportResult(addResponse);
                }
            }
        }else {
            addResponse.setContent("Error::Kiểm tra lại dữ liệu");
            outputBoundary.exportResult(addResponse);
        }
    }



    public boolean validate(HangHoaDTO data, String loaiSanPham) {
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
        switch (loaiSanPham.toLowerCase()) {
            case "thực phẩm":
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
                break;

            case "điện máy":
                DienMayDTO dienMay = (DienMayDTO)data;
                if (dienMay.getThoiGianBaoHanh() < 0) {
                    return false;
                }
                if (dienMay.getCongSuat() <= 0) {
                    return false;
                }
                break;

            case "sành sứ":
                SanhSuDTO sanhSu = (SanhSuDTO) data;
                if (sanhSu.getNhaSanXuat() == null || sanhSu.getNhaSanXuat().isEmpty()) {
                    return false;
                }
                if (sanhSu.getNgayNhapKho() == null) {
                    return false;
                }
                break;

            default:
                return false;
        }

        // Nếu tất cả kiểm tra đều hợp lệ
        return true;
    }
}
