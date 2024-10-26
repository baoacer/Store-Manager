package com.example.ui;
import com.example.dtos.ResponseDTO;
import com.example.dtos.getProductListDTOs.HangHoaOutputDTO;
import com.example.usecase.ResponseData;
import com.example.usecase.OutputBoundary;
import com.example.dtos.totalQuantityDTOs.TotalQuantityDienMayOutputDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantitySanhSuOutputDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityThucPhamOutputDTO;
import com.example.usecase.getProductListUseCase.GetProductListOutputDTO;

import java.util.List;

public class Presenter implements OutputBoundary{
    private ResponseData response = null;
    private ResponseDTO responseDTO = null;
    private View view = null;
    private List<HangHoaOutputDTO> listHangHoaOutputDTO = null;
    private List<ViewModel> listViewModel = null;
    @Override
    public void exportResult(ResponseData responseData) {
        this.response = responseData;
    }

    public Presenter(View view) {
        this.view = view;
    }

    public Presenter() {
    }

    public void present(List<HangHoaOutputDTO> listHangHoaOutputDTO){
        this.listHangHoaOutputDTO = listHangHoaOutputDTO;

        for(HangHoaOutputDTO hangHoaOutputDTO: listHangHoaOutputDTO){
            ViewModel viewModel = new ViewModel
                    (hangHoaOutputDTO.getMaHang(),hangHoaOutputDTO.getSoLuongTon(),hangHoaOutputDTO.getTenHang(), hangHoaOutputDTO.getDonGia());
            listViewModel.add(viewModel);

        }
    }
   public List<GetProductListOutputDTO> getProductListOutputDTOS(){
//        return listProductOutputDTO;
       return null;
   }
    @Override
    public void TotalQuantityPresenter(ResponseData responseData) {
        if(responseData instanceof TotalQuantityDienMayOutputDTO) {
            TotalQuantityDienMayOutputDTO totalQuantityDienMayOutputDTO = (TotalQuantityDienMayOutputDTO) responseData;
            view.setTotalQuantityDienMay(totalQuantityDienMayOutputDTO.getTotalQuantity());
        } else if(responseData instanceof TotalQuantitySanhSuOutputDTO) {
            TotalQuantitySanhSuOutputDTO totalQuantitySanhSuOutputDTO = (TotalQuantitySanhSuOutputDTO) responseData;
            view.setTotalQuantitySanhSu(totalQuantitySanhSuOutputDTO.getTotalQuantity());
        } else if(responseData instanceof TotalQuantityThucPhamOutputDTO) {
            TotalQuantityThucPhamOutputDTO totalQuantityThucPhamOutputDTO = (TotalQuantityThucPhamOutputDTO) responseData;
            view.setTotalQuantityThucPham(totalQuantityThucPhamOutputDTO.getTotalQuantity());
        }
    }

    @Override
    public void updateProductPresenter(ResponseData responseData) {

    }

    @Override
    public void addProductPresenter(ResponseData responseData) {
        ResponseDTO response = (ResponseDTO)responseData;
        view.showMessage(response.getContent());
    }

    @Override
    public void removeProductPresenter(ResponseData responseData) {
        ResponseDTO response = (ResponseDTO)responseData;
        view.showMessage(response.getContent());
    }

    public ResponseData getResponse() {
        return response;
    }
}
