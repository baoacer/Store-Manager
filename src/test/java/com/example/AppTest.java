package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.example.database.MysqlDAO;
import com.example.ui.Presenter;
import com.example.usecase.GetProductListUseCase.GetProductListOutputDTO;
import com.example.usecase.GetProductListUseCase.GetProductListUseCase;
import com.example.usecase.InputBoundary;

/**
 * Unit test for simple App.
 */
public class AppTest {

        /**
         * Rigorous Test :-)
         */
        @Test
        public void testGetAllProductList() {
                // 1. Tạo đối tượng MysqlDAO và truyền dữ liệu giả lập từ getData()
                MysqlDAO mdao = new MysqlDAO();
                // 2. Tạo đối tượng Presenter để lưu kết quả
                Presenter p = new Presenter();
                // 3. Tạo đối tượng UseCase và truyền Presenter và MysqlDAO
                InputBoundary i = new GetProductListUseCase(p, mdao);
                // 4. Tạo request data giả lập
                // 5. Thực thi use case
                // RequestData requestData = new RequestInputDTO();
                i.execute(null);
                // 6. Kiểm tra kết quả trong Presenter
                GetProductListOutputDTO outputDTO = (GetProductListOutputDTO) p.getList();
                // Kiểm tra số lượng sản phẩm trong danh sách
                assertEquals(30, outputDTO.getList().size());
        }

        // @Test
        // public void testFindAllProduct7DaysExpiry() {
        // MysqlDAO mdao = new MysqlDAO(getData());
        // Presenter p = new Presenter();
        // InputBoundary i = new GetProductListSevenDayExpiryUseCase(p, mdao);
        // i.execute(null);

        // GetProductListSevenDayExpiryOutputDTO outputDTO =
        // (GetProductListSevenDayExpiryOutputDTO) p.getList();

        // assertEquals(1, outputDTO.getList().size());
        // }

        // private List<HangHoa> getData() {
        // List<HangHoa> danhSachHangHoa = new ArrayList<>();

        // // Giả lập dữ liệu cho HangThucPham
        // HangThucPham thucPham1 = new HangThucPham(LocalDate.of(2025, 1, 15),
        // LocalDate.of(2023, 1, 15),
        // "Công ty ABC",
        // "TP01", "Sữa tươi", 100, 20000);

        // HangThucPham thucPham2 = new HangThucPham(
        // LocalDate.of(2024, 10, 21),
        // LocalDate.of(2023, 10, 20),
        // "Công ty XYZ",
        // "TP02",
        // "Bánh quy",
        // 150,
        // 30000);

        // HangThucPham thucPham3 = new HangThucPham(
        // LocalDate.of(2024, 7, 1),
        // LocalDate.of(2023, 6, 1),
        // "Công ty 123",
        // "TP03",
        // "Nước ngọt",
        // 50,
        // 10000);

        // HangThucPham thucPham4 = new HangThucPham(
        // LocalDate.of(2024, 12, 31),
        // LocalDate.of(2023, 12, 1),
        // "Công ty DEF",
        // "TP04",
        // "Bột mì",
        // 80,
        // 15000);

        // HangThucPham thucPham5 = new HangThucPham(
        // LocalDate.of(2025, 3, 10),
        // LocalDate.of(2024, 3, 10),
        // "Công ty GHI",
        // "TP05",
        // "Mì gói",
        // 200,
        // 5000);
        // // Thêm sản phẩm có ngày hết hạn trong vòng 7 ngày
        // HangThucPham thucPham6 = new HangThucPham(
        // LocalDate.of(2024, 10, 22), // Hết hạn trong 7 ngày
        // LocalDate.of(2023, 10, 22),
        // "Công ty JKL",
        // "TP06",
        // "Thịt heo",
        // 100,
        // 60000);

        // HangThucPham thucPham7 = new HangThucPham(
        // LocalDate.of(2024, 10, 20), // Hết hạn trong 5 ngày
        // LocalDate.of(2023, 10, 20),
        // "Công ty MNO",
        // "TP07",
        // "Cá hồi",
        // 50,
        // 120000);
        // HangDienMay dienMay1 = new HangDienMay(
        // 2.5,
        // 12,
        // "DM01",
        // "Tủ lạnh",
        // 20,
        // 5000000);

        // HangDienMay dienMay2 = new HangDienMay(
        // 1.2,
        // 24,
        // "DM02",
        // "Máy giặt",
        // 15,
        // 7000000);

        // HangDienMay dienMay3 = new HangDienMay(
        // 1.0,
        // 18,
        // "DM03",
        // "Máy điều hòa",
        // 10,
        // 12000000);

        // HangDienMay dienMay4 = new HangDienMay(
        // 0.8,
        // 36,
        // "DM04",
        // "Lò vi sóng",
        // 25,
        // 3000000);

        // HangDienMay dienMay5 = new HangDienMay(
        // 0.6,
        // 6,
        // "DM05",
        // "Quạt điện",
        // 100,
        // 500000);

        // HangSanhSu sanhSu1 = new HangSanhSu(
        // LocalDate.of(2024, 10, 5),
        // "Công ty Gốm Sứ A",
        // "SS01",
        // "Bát sứ",
        // 30,
        // 50000);

        // HangSanhSu sanhSu2 = new HangSanhSu(
        // LocalDate.of(2024, 9, 20),
        // "Công ty Gốm Sứ B",
        // "SS02",
        // "Đĩa sứ",
        // 40,
        // 60000);

        // HangSanhSu sanhSu3 = new HangSanhSu(
        // LocalDate.of(2024, 8, 15),
        // "Công ty Gốm Sứ C",
        // "SS03",
        // "Bình hoa",
        // 20,
        // 150000);

        // HangSanhSu sanhSu4 = new HangSanhSu(
        // LocalDate.of(2024, 7, 10),
        // "Công ty Gốm Sứ D",
        // "SS04",
        // "Ấm trà",
        // 15,
        // 80000);

        // HangSanhSu sanhSu5 = new HangSanhSu(LocalDate.of(2024, 6, 5), "Công ty Gốm Sứ
        // E", "SS05", "Ly sứ", 50,
        // 30000);

        // danhSachHangHoa.add(thucPham1);
        // danhSachHangHoa.add(thucPham2);
        // danhSachHangHoa.add(thucPham3);
        // danhSachHangHoa.add(thucPham4);
        // danhSachHangHoa.add(thucPham5);
        // danhSachHangHoa.add(thucPham6);
        // danhSachHangHoa.add(thucPham7);
        // danhSachHangHoa.add(dienMay1);
        // danhSachHangHoa.add(dienMay2);
        // danhSachHangHoa.add(dienMay3);
        // danhSachHangHoa.add(dienMay4);
        // danhSachHangHoa.add(dienMay5);
        // danhSachHangHoa.add(sanhSu1);
        // danhSachHangHoa.add(sanhSu2);
        // danhSachHangHoa.add(sanhSu3);
        // danhSachHangHoa.add(sanhSu4);
        // danhSachHangHoa.add(sanhSu5);
        // return danhSachHangHoa;
        // }

}
