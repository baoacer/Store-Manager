package com.example.usecase.getProductListUseCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dtos.getProductListDTOs.HangDienMayOutputDTO;
import com.example.dtos.getProductListDTOs.HangHoaOutputDTO;
import com.example.dtos.getProductListDTOs.HangSanhSuOutputDTO;
import com.example.dtos.getProductListDTOs.HangThucPhamOutputDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
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
    public void execute(RequestData requestData) throws SQLException {
        // gọi hàm lấy dữ liệu trong database
        List<HangHoa> listHangHoa = databaseBoundary.getAllProductList();

        // tạo 1 mảng chứa dữ liệu kiểu ProductDTO
        List<HangHoaOutputDTO> listProductDTO = new ArrayList<>();

        for (HangHoa hangHoa : listHangHoa) {

            if (hangHoa instanceof HangThucPham hangThucPham) {
                HangHoaOutputDTO hangHoaOutputDTO = new HangThucPhamOutputDTO(hangThucPham.getMaHang(),
                        hangThucPham.getTenHang(), hangThucPham.getSoLuongTon(), hangThucPham.getDonGia(),
                        hangThucPham.tinhVat(), hangThucPham.getNgaySanXuat(), hangThucPham.getNgayHetHan(),
                        hangThucPham.getNhaCungCap());
                listProductDTO.add(hangHoaOutputDTO);
            }
            if (hangHoa instanceof HangDienMay hangDienMay) {
                HangHoaOutputDTO hangHoaOutputDTO = new HangDienMayOutputDTO(hangDienMay.getMaHang(),
                        hangDienMay.getTenHang(), hangDienMay.getSoLuongTon(), hangDienMay.getDonGia(),
                        hangDienMay.tinhVat(), hangDienMay.getThoiGianBaoHanh(), hangDienMay.getCongSuat());
                listProductDTO.add(hangHoaOutputDTO);
            }
            if (hangHoa instanceof HangSanhSu hangSanhSu) {
                HangHoaOutputDTO hangHoaOutputDTO = new HangSanhSuOutputDTO(hangSanhSu.getMaHang(),
                        hangSanhSu.getTenHang(), hangSanhSu.getSoLuongTon(), hangSanhSu.getDonGia(),
                        hangSanhSu.tinhVat(), hangSanhSu.getNhaSanXuat(), hangSanhSu.getNgayNhapKho());
                listProductDTO.add(hangHoaOutputDTO);
            }

        }
        GetProductListOutputDTO getProductListOutputDTO = new GetProductListOutputDTO();
        getProductListOutputDTO.setList(listProductDTO);
        outputBoundary.exportResult(getProductListOutputDTO);
    }

}
