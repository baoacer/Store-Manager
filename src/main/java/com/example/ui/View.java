package com.example.ui;

import com.example.dtos.addProductDTOs.DienMayDTO;
import com.example.dtos.addProductDTOs.SanhSuDTO;
import com.example.dtos.addProductDTOs.ThucPhamDTO;
import com.example.dtos.removeProductDTOs.RemoveDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel jLabelTongSoLuongDienMay, jLabelTongSoLuongSanhSu, jLabelTongSoLuongThucPham;
    private JButton btnTinhTongSoLuongDienMay, btnTinhTongSoLuongSanhSu, btnTinhTongSoLuongThucPham, btnSua;
    private Controller controller = null;
    private String selectedProductId;
    private  JTextField txtMaHang;

    public View(Controller controller) {
        this.controller = controller;

        setTitle("Quản Lý Kho Trong Siêu Thị");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel chính chứa các thành phần
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel nhập liệu bên trên
        JPanel inputPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Mã Hàng:"));
        JTextField txtMaHang = new JTextField();
        inputPanel.add(txtMaHang);

        inputPanel.add(new JLabel("Tên Hàng:"));
        JTextField txtTenHang = new JTextField();
        inputPanel.add(txtTenHang);

        inputPanel.add(new JLabel("Ngày Nhập:"));
        JTextField txtNgayNhap = new JTextField();
        inputPanel.add(txtNgayNhap);

        inputPanel.add(new JLabel("Đơn Giá:"));
        JTextField txtDonGia = new JTextField();
        inputPanel.add(txtDonGia);

        inputPanel.add(new JLabel("Số Lượng:"));
        JTextField txtSoLuong = new JTextField();
        inputPanel.add(txtSoLuong);

        inputPanel.add(new JLabel("Loại Hàng:"));
        JComboBox<String> cmbCategory = new JComboBox<>(new String[]{"Thực Phẩm", "Điện Máy", "Sành Sứ"});
        inputPanel.add(cmbCategory);

        // Thêm các trường thông tin chi tiết (ẩn đi ban đầu)
        JLabel lblText1 = new JLabel("Text 1:");
        JTextField txtText1 = new JTextField();
        inputPanel.add(lblText1);
        inputPanel.add(txtText1);

        JLabel lblText2 = new JLabel("Text 2:");
        JTextField txtText2 = new JTextField();
        inputPanel.add(lblText2);
        inputPanel.add(txtText2);

        JLabel lblText3 = new JLabel("Text 3:");
        JTextField txtText3 = new JTextField();
        inputPanel.add(lblText3);
        inputPanel.add(txtText3);

        // Ẩn các trường Text 1, 2, 3 khi khởi động
        lblText1.setVisible(false);
        txtText1.setVisible(false);
        lblText2.setVisible(false);
        txtText2.setVisible(false);
        lblText3.setVisible(false);
        txtText3.setVisible(false);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnThem = new JButton("➕ Thêm");
        JButton btnXoa = new JButton("🗑️ Xóa");
        JButton btnSua = new JButton("🔧 Sửa");
        JButton btnSanPhamSapHetHan = new JButton("Sản Phẩm Còn 1 Tuần Là Hết Hạn");

        btnTinhTongSoLuongDienMay = new JButton("Tính Tổng Số Lượng Điện Máy");
        btnTinhTongSoLuongSanhSu = new JButton("Tính Tổng Số Lượng Sành Sứ");
        btnTinhTongSoLuongThucPham = new JButton("Tính Tổng Số Lượng Thực Phẩm");


        // Thêm các nút vào buttonPanel
        buttonPanel.add(btnTinhTongSoLuongDienMay);
        buttonPanel.add(btnTinhTongSoLuongSanhSu);
        buttonPanel.add(btnTinhTongSoLuongThucPham);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnSanPhamSapHetHan);


        // Thêm buttonPanel vào mainPanel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.add(new JLabel("Tìm Kiếm:"));
        JTextField txtTimKiem = new JTextField(20);
        searchPanel.add(txtTimKiem);
        JButton btnTimKiem = new JButton("🔍");
        searchPanel.add(btnTimKiem);

        // Thêm comboboxAttribute và nút Refresh
        JComboBox<String> comboboxAttribute = new JComboBox<>(new String[]{"Thực Phẩm", "Điện Máy", "Sành Sứ"});
        searchPanel.add(comboboxAttribute);
        JButton btnRefresh = new JButton("🔄 Refresh");
        searchPanel.add(btnRefresh);

        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        // Model cho bảng
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Thêm panel chính và bảng vào frame
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Panel tính tổng số lượng
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));


        jLabelTongSoLuongDienMay = new JLabel("Tổng Số Lượng Điện Máy:");
        jLabelTongSoLuongSanhSu = new JLabel("Tổng Số Lượng Sành Sứ: ");
        jLabelTongSoLuongThucPham = new JLabel("Tổng Số Lượng Thực Phẩm");

        JButton btnTinhTongSoLuong = new JButton("Tính Tổng Số Lượng");
        JLabel lblTongSoLuong = new JLabel("Tổng Số Lượng: 0");
        totalPanel.add(btnTinhTongSoLuong);
        totalPanel.add(lblTongSoLuong);

        // Thêm totalPanel vào dưới searchPanel
        add(totalPanel, BorderLayout.PAGE_END);

        // Sự kiện thay đổi JComboBox cho loại hàng
        cmbCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbCategory.getSelectedItem();

                // Hiển thị các trường và thay đổi nội dung của các nhãn dựa trên loại hàng
                lblText1.setVisible(true);
                txtText1.setVisible(true);
                lblText2.setVisible(true);
                txtText2.setVisible(true);

                if ("Thực Phẩm".equals(selectedCategory)) {
                    lblText1.setText("Ngày sản xuất:");
                    lblText2.setText("Ngày hết hạn:");
                    lblText3.setText("Nhà cung cấp:");
                    lblText3.setVisible(true);
                    txtText3.setVisible(true);
                } else if ("Điện Máy".equals(selectedCategory)) {
                    lblText1.setText("Thời gian bảo hành:");
                    lblText2.setText("Công suất (KW):");
                    lblText3.setVisible(false);
                    txtText3.setVisible(false);
                } else { // "Sành Sứ"
                    lblText1.setText("Ngày sản xuất:");
                    lblText2.setText("Ngày nhập kho:");
                    lblText3.setVisible(false);
                    txtText3.setVisible(false);
                }
            }
        });

        btnTinhTongSoLuongDienMay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DienMayDTO dienMayDTO = new DienMayDTO();
                try {
                    controller.getToTalQuantity(dienMayDTO);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnTinhTongSoLuongSanhSu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanhSuDTO sanhSuDTO = new SanhSuDTO();
                try {
                    controller.getToTalQuantity(sanhSuDTO);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnTinhTongSoLuongThucPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThucPhamDTO thucPhamDTO = new ThucPhamDTO();
                try {
                    controller.getToTalQuantity(thucPhamDTO);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbCategory.getSelectedItem();

                try {
                    if ("Thực Phẩm".equals(selectedCategory)) {
                        ThucPhamDTO thucPham = new ThucPhamDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                LocalDate.parse(txtText1.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                LocalDate.parse(txtText2.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                txtText3.getText()
                        );
                        controller.addProduct(thucPham);
                    } else if ("Điện Máy".equals(selectedCategory)) {
                        DienMayDTO dienMay = new DienMayDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                Integer.parseInt(txtText1.getText()),
                                Double.parseDouble(txtText2.getText())
                        );
                        controller.addProduct(dienMay);
                    } else if ("Sành Sứ".equals(selectedCategory)) {
                        SanhSuDTO sanhSu = new SanhSuDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                txtText1.getText(),
                                LocalDate.parse(txtText2.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        );
                        controller.addProduct(sanhSu);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy id của sản phẩm từ dòng được chọn
                    selectedProductId = table.getValueAt(selectedRow, 0).toString();
                }
                RemoveDTO maHang = new RemoveDTO(selectedProductId);
                try {
                    controller.removeProduct(maHang);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Thêm ListSelectionListener vào JTable để xử lý khi người dùng chọn một dòng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Lấy chỉ số của dòng được chọn
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        // Lấy id của sản phẩm từ dòng được chọn
                        selectedProductId = table.getValueAt(selectedRow, 0).toString();
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbCategory.getSelectedItem();

                try {
                    if ("Thực Phẩm".equals(selectedCategory)) {
                        ThucPhamDTO thucPham = new ThucPhamDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                LocalDate.parse(txtText1.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                LocalDate.parse(txtText2.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                txtText3.getText()
                        );
                        controller.addProduct(thucPham);
                    } else if ("Điện Máy".equals(selectedCategory)) {
                        DienMayDTO dienMay = new DienMayDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                Integer.parseInt(txtText1.getText()),
                                Double.parseDouble(txtText2.getText())
                        );
                        controller.addProduct(dienMay);
                    } else if ("Sành Sứ".equals(selectedCategory)) {
                        SanhSuDTO sanhSu = new SanhSuDTO(
                                txtMaHang.getText(),
                                txtTenHang.getText(),
                                Integer.parseInt( txtSoLuong.getText()),
                                Double.parseDouble( txtDonGia.getText()),
                                txtText1.getText(),
                                LocalDate.parse(txtText2.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        );
                        controller.addProduct(sanhSu);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveDTO removeId = new RemoveDTO(selectedProductId);
                try {
                    controller.removeProduct(removeId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Sự kiện thay đổi JComboBox cho thuộc tính
        comboboxAttribute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) comboboxAttribute.getSelectedItem();
                updateTableColumns(selectedCategory);
            }
        });

        // Thêm sự kiện cho nút Refresh
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable(); // Reset the table to its initial state
            }
        });

        // Khởi tạo bảng với các cột mặc định
        resetTable(); // Set initial columns and clear data
        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void setTotalQuantityDienMay(int totalQuantityDienMay) {
        jLabelTongSoLuongDienMay.setText(String.valueOf(totalQuantityDienMay));
    }

    public void setTotalQuantitySanhSu(int totalQuantitySanhSu) {
        jLabelTongSoLuongSanhSu.setText(String.valueOf(totalQuantitySanhSu));
    }

    public void setTotalQuantityThucPham(int totalQuantityThucPham) {
        jLabelTongSoLuongThucPham.setText(String.valueOf(totalQuantityThucPham));
    }

    // Hàm cập nhật cột của bảng dựa trên loại hàng được chọn
    private void updateTableColumns(String category) {
        // Xóa các cột hiện tại
        tableModel.setColumnCount(0);

        // Cấu trúc lại các cột dựa trên loại hàng
        if ("Thực Phẩm".equals(category)) {
            tableModel.setColumnIdentifiers(new String[]{
                    "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá",
                    "Nhà Cung Cấp", "Ngày Sản Xuất", "Ngày Hết Hạn", "VAT", "Tổng Giá"
            });
        } else if ("Điện Máy".equals(category)) {
            tableModel.setColumnIdentifiers(new String[]{
                    "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá",
                    "Bảo Hành", "Công Suất", "VAT", "Tổng Giá"
            });
        } else { // "Sành Sứ"
            tableModel.setColumnIdentifiers(new String[]{
                    "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá",
                    "Nhà Sản Xuất", "Ngày Nhập Kho", "VAT", "Tổng Giá"
            });
        }
    }

    // Hàm khôi phục lại bảng về trạng thái ban đầu
    private void resetTable() {
        // Reset table model to the default state with specific columns
        tableModel.setColumnIdentifiers(new String[]{
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá"
        });
        // Clear existing data
        tableModel.setRowCount(0);
    }
}

  
