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

        // Kiểm tra dữ liệu
        String validationError = validate(dto);
        if (validationError != null) {
            // Nếu có lỗi, trả về ngay thông báo lỗi đó
            addResponse.setContent("Error::" + validationError);
            outputBoundary.exportResult(addResponse);
            return;
        }

        // Nếu không có lỗi, tiếp tục xử lý tạo đối tượng HangHoa và lưu vào DB
        HangHoa hangHoa = null;

        if (requestData instanceof ThucPhamDTO) {
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
                    ((DienMayDTO) dto).getCongSuat(),
                    ((DienMayDTO) dto).getThoiGianBaoHanh()
            );
        } else if (requestData instanceof SanhSuDTO) {
            hangHoa = new HangSanhSu(
                    dto.getMaHang(),
                    dto.getTenHang(),
                    dto.getSoLuong(),
                    dto.getDonGia(),
                    ((SanhSuDTO) dto).getNgayNhapKho(),
                    ((SanhSuDTO) dto).getNhaSanXuat()
            );
        }

        // Kiểm tra nếu hangHoa được tạo thành công
        if (hangHoa != null) {
            boolean isSave = databaseBoundary.saveProduct(hangHoa);
            if (isSave) {
                addResponse.setContent("Success::Thêm sản phẩm thành công");
            } else {
                addResponse.setContent("Error::Lưu sản phẩm thất bại");
            }
        } else {
            addResponse.setContent("Error::Không thể tạo đối tượng sản phẩm.");
        }

        // Xuất kết quả
        outputBoundary.exportResult(addResponse);
    }


    public String validate(HangHoaDTO data) {
        // Kiểm tra các thuộc tính chung
        if (data.getMaHang() == null || data.getMaHang().isEmpty()) {
            return "Mã hàng không được để trống.";
        }
        if (data.getTenHang() == null || data.getTenHang().isEmpty()) {
            return "Tên hàng không được để trống.";
        }
        if (data.getSoLuong() < 0) {
            return "Số lượng không được âm.";
        }
        if (data.getDonGia() <= 0) {
            return "Đơn giá phải lớn hơn 0.";
        }

        // Kiểm tra thuộc tính đặc thù dựa vào loại sản phẩm
        if (data instanceof ThucPhamDTO) {
            ThucPhamDTO thucPham = (ThucPhamDTO) data;
            if (thucPham.getNgaySanXuat() == null) {
                return "Ngày sản xuất không được để trống.";
            }
            if (thucPham.getNgayHetHan() == null) {
                return "Ngày hết hạn không được để trống.";
            }
            if (!thucPham.getNgayHetHan().isAfter(thucPham.getNgaySanXuat())) {
                return "Ngày hết hạn phải sau ngày sản xuất.";
            }
            if (thucPham.getNhaCungCap() == null || thucPham.getNhaCungCap().isEmpty()) {
                return "Nhà cung cấp không được để trống.";
            }
        } else if (data instanceof DienMayDTO) {
            DienMayDTO dienMay = (DienMayDTO) data;
            if (dienMay.getThoiGianBaoHanh() < 0) {
                return "Thời gian bảo hành không được âm.";
            }
            if (dienMay.getCongSuat() <= 0) {
                return "Công suất phải lớn hơn 0.";
            }
        } else if (data instanceof SanhSuDTO) {
            SanhSuDTO sanhSu = (SanhSuDTO) data;
            if (sanhSu.getNhaSanXuat() == null || sanhSu.getNhaSanXuat().isEmpty()) {
                return "Nhà sản xuất không được để trống.";
            }
            if (sanhSu.getNgayNhapKho() == null) {
                return "Ngày nhập kho không được để trống.";
            }
        }

        // Nếu tất cả kiểm tra đều hợp lệ
        return null;
    }














}
