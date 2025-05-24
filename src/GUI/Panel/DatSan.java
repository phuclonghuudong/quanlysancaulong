package GUI.Panel;

import BUS.DatSanBUS;
import BUS.KhachHangBUS;
import BUS.LoaiHangBUS;
import BUS.LoaiSanBUS;
import BUS.SanBUS;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DTO.DatSanDTO;
import DTO.KhachHangDTO;
import DTO.LoaiHangDTO;
import DTO.LoaiSanDTO;
import DTO.NhanVienDTO;
import DTO.SanDTO;
import DTO.SanPhamDTO;
import GUI.Component.CustomScrollBar;
import GUI.Component.TableModel;
import GUI.Component.itemTaskbar;
import GUI.Main;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import style.StyleButton;
import style.StyleColor;
import style.StyleTable;
import utils.Formater;

/**
 *
 * @author phucp
 */
public final class DatSan extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    StyleColor colorStyle = new StyleColor();
    StyleTable tableStyle = new StyleTable();
    StyleButton buttonStyle = new StyleButton();
    Main m;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    NhanVienDTO nvDTO;

    private DatSanBUS datsanBUS = new DatSanBUS();
    private KhachHangBUS khBUS = new KhachHangBUS();
    private ArrayList<DatSanDTO> listDatSan;
    private TableModel<DatSanDTO> tableModelDatSan;

    JTable tableSanPham;
    DefaultTableModel tblModelSanPham;
    private SanPhamBUS sanphamBUS = new SanPhamBUS();
    private ArrayList<SanPhamDTO> listSanPham = sanphamBUS.getAllLoaiSanPhamHoatDong();
    private TableModel<SanPhamDTO> tableModelSanPham;

    private ArrayList<JPanel> danhSachPanelSanPham = new ArrayList<>();

    private SanBUS sanBUS = new SanBUS();
    private LoaiSanBUS loaiSanBUS = new LoaiSanBUS();
    private ArrayList<LoaiSanDTO> listLoaiSan;
    private LoaiHangBUS loaiHangBUS = new LoaiHangBUS();
    private ArrayList<LoaiHangDTO> listLoaiHang;

    private SanDTO sanDuocChon;  // sân hiện tại đang chọn
    private ArrayList<SanPhamDTO> listSanPhamDaChon = new ArrayList<>();

    int soGioChoi = 0;
    double thanhtien = 0;

    String[] headerSanPham = new String[]{"Mã SP", "Tên sản phẩm", "Loại sản phẩm", "Giá", "Số lượng"};

    public DatSan(Main m, NhanVienDTO nvDTO) {
        this.listDatSan = datsanBUS.getAll();
        this.nvDTO = nvDTO;
        this.m = m;

        initComponents();
        initComponent();
        loadDataTableSanPham(listSanPham);
    }

    public DatSan(Main m) {
        this.listDatSan = datsanBUS.getAll();
        this.m = m;
        initComponents();
        initComponent();

        loadDataTableSanPham(listSanPham);
    }

    private void initComponent() {
        this.setSize(new Dimension(1030, 670));
        this.setBackground(colorStyle.mainBackgroundColor());
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jPanelHeader.setBackground(colorStyle.colorForm());
        jPanelThanhToan.setBackground(colorStyle.colorForm());
        jPanelMainDanhSachSan.setBackground(Color.WHITE);
        jPanelDanhSachSanPham.setBackground(Color.LIGHT_GRAY);
        jPanelButtonSan.setBackground(Color.WHITE);
        jPanelHoaDon.setBackground(Color.WHITE);
        jPanelTitle.setBackground(new Color(204, 255, 255));

        jPanelMainDanhSachSan.add(jPanelDanhSachSan, BorderLayout.CENTER);
        jPanelMainDanhSachSan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelHeader();
        panelDanhSachSan();
        panelDanhSachSanPham();
        panelButton();
        if (sanDuocChon != null) {
            hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
        }

        jPanelButtonSanPham.setBackground(Color.WHITE);
        buttonStyle.customizeButton(btnAddSanPham, "");
        buttonStyle.customizeButton(btnThemSoLuong, "");
        buttonStyle.customizeButton(btnXoaSanPham, "");
        buttonStyle.customizeButton(btnTruSoLuong, "");
        buttonStyle.customizeButton(btnDatSan, "excel");
        buttonStyle.customizeButton(btnThanhToan, "success");
        buttonStyle.customizeButton(btnHuySan, "danger");

        btnThemSoLuong.addActionListener(this);
        btnTruSoLuong.addActionListener(this);
        btnXoaSanPham.addActionListener(this);
        btnAddSanPham.addActionListener(this);
        txtLoaiSanPham.addItemListener(this);
    }

    private void panelHeader() {
        txtUsernameKH.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtSoDienThoaiKH.setBorder(new EmptyBorder(2, 10, 2, 10));
        lblSoDienThoai.setText("SĐT: (chưa nhập)");
        lblSoGioChoi.setText("Giờ chơi: (chưa có)");

        txtSoDienThoaiKH.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                capNhatLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                capNhatLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                capNhatLabel();
            }

            private void capNhatLabel() {
                String sdt = txtSoDienThoaiKH.getText();
                if (sdt.trim().isEmpty()) {
                    lblSoDienThoai.setText("SĐT: (chưa nhập)");
                } else {
                    lblSoDienThoai.setText("SĐT:" + sdt);
                }
            }
        });

//        DANH SÁCH LOẠI SÂN
        listLoaiSan = loaiSanBUS.getAllStatus();
        ArrayList<String> loaiSanNames = new ArrayList<>();
        loaiSanNames.add("Tất cả");
        loaiSanNames.addAll(
                listLoaiSan.stream()
                        .map(LoaiSanDTO::getTenloaisan)
                        .toList()
        );
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(loaiSanNames.toArray(String[]::new));
        txtLoaiSan.setModel(model);
        buttonStyle.customizeCombobox(txtLoaiSan);
        txtLoaiSan.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedLoaiSan = (String) txtLoaiSan.getSelectedItem();
                hienThiDanhSachSanTheoLoai(selectedLoaiSan);
            }
        });
        //        DANH SÁCH LOẠI SẢN PHẨM
        listLoaiHang = loaiHangBUS.getAll();
        ArrayList<String> loaiSanPhamNames = new ArrayList<>();
        loaiSanPhamNames.add("Tất cả");
        loaiSanPhamNames.addAll(
                listLoaiHang.stream()
                        .map(LoaiHangDTO::getTenloaihang)
                        .toList()
        );
        DefaultComboBoxModel<String> modelLH = new DefaultComboBoxModel<>(loaiSanPhamNames.toArray(String[]::new));
        txtLoaiSanPham.setModel(modelLH);
        buttonStyle.customizeCombobox(txtLoaiSanPham);
    }

    private void panelDanhSachSan() {
        ArrayList<SanDTO> listSan = sanBUS.getAllLoaiSanHoatDong();

        jPanelDanhSachSan.removeAll();
        jPanelDanhSachSan.setLayout(new GridLayout(0, 3, 10, 10));
        jPanelDanhSachSan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelDanhSachSan.setBackground(Color.WHITE);

        itemTaskbar[] listItem = new itemTaskbar[listSan.size()];
        int i = 0;
        for (SanDTO san : listSan) {
            listItem[i] = new itemTaskbar(san);
            listItem[i].setOnSanSelectedListener(s -> {
                sanDuocChon = s;
                hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);

            });
            jPanelDanhSachSan.add(listItem[i]);
            i++;
        }

        JScrollPane scrollPane = new JScrollPane(jPanelDanhSachSan);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
        scrollPane.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL));

        jPanelMainDanhSachSan.removeAll();
        jPanelMainDanhSachSan.setLayout(new BorderLayout());
        jPanelMainDanhSachSan.add(scrollPane, BorderLayout.CENTER);
        jPanelMainDanhSachSan.revalidate();
        jPanelMainDanhSachSan.repaint();
    }

    private void panelDanhSachSanPham() {

        String[] methodNames = {"getMasanpham", "getTensanpham", "getTenloaisanpham", "getGiaban", "getSoluong"};
        tableModelSanPham = new TableModel<>(listSanPham, headerSanPham, methodNames);
        tableSanPham = new JTable(tableModelSanPham);
        tableStyle.customizeTable(tableSanPham);
        JScrollPane scrollPane = new JScrollPane(tableSanPham);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBorder(null);
        scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
        scrollPane.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL));

        jPanelDanhSachSanPham.removeAll();
        jPanelDanhSachSanPham.setLayout(new BorderLayout());
        jPanelDanhSachSanPham.add(scrollPane, BorderLayout.CENTER);
        jPanelDanhSachSanPham.revalidate();
        jPanelDanhSachSanPham.repaint();
    }

    private void panelButton() {
        txtCheckin.removeAllItems();
        txtCheckout.removeAllItems();

        txtCheckin.addItem("Tất cả");
        txtCheckout.addItem("Tất cả");
        lblCheckin.setText("CheckIn: Tất cả");
        lblCheckout.setText("CheckOut: Tất cả");

        for (int i = 6; i <= 22; i++) {
            LocalTime time = LocalTime.of(i, 0);
            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            txtCheckin.addItem(formattedTime);
            txtCheckout.addItem(time.plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        buttonStyle.customizeCombobox(txtCheckin);
        buttonStyle.customizeCombobox(txtCheckout);

        txtCheckin.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                if (value instanceof LocalTime time) {
                    value = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        txtCheckout.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                if (value instanceof LocalTime time) {
                    value = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        buttonStyle.applyCustomScrollBar(txtCheckin);
        buttonStyle.applyCustomScrollBar(txtCheckout);

        txtCheckin.addActionListener((ActionEvent e) -> {
            String gioCheckin = txtCheckin.getSelectedItem().toString();
            String gioCheckout = txtCheckout.getSelectedItem().toString();
            lblCheckin.setText("CheckIn: " + gioCheckin);

            if (!gioCheckin.equals("Tất cả") && !gioCheckout.equals("Tất cả")) {
                try {
                    LocalTime timeCheckin = LocalTime.parse(gioCheckin);
                    LocalTime timeCheckout = LocalTime.parse(gioCheckout);

                    if (!timeCheckout.isAfter(timeCheckin)) {
                        txtCheckout.setSelectedItem("Tất cả");
                        lblCheckout.setText("CheckOut: Tất cả");
                    }
                } catch (Exception ex) {
                }
            }
        });

        txtCheckout.addActionListener((var e) -> {
            String gioCheckin = txtCheckin.getSelectedItem().toString();
            String gioCheckout = txtCheckout.getSelectedItem().toString();
            lblCheckout.setText("CheckOut: " + gioCheckout);

            if (gioCheckin.equals("Tất cả") || gioCheckout.equals("Tất cả")) {
                return;
            }

            try {
                LocalTime timeCheckin = LocalTime.parse(gioCheckin);
                LocalTime timeCheckout = LocalTime.parse(gioCheckout);

                if (!timeCheckout.isAfter(timeCheckin)) {
                    JOptionPane.showMessageDialog(null,
                            "Giờ CheckOut phải lớn hơn giờ CheckIn!", "Lỗi chọn giờ",
                            JOptionPane.WARNING_MESSAGE);

                    txtCheckout.setSelectedItem("Tất cả");
                    lblCheckout.setText("CheckOut: Tất cả");

                }
                long hoursPlayed = Duration.between(timeCheckin, timeCheckout).toHours();
                lblSoGioChoi.setText("Giờ chơi: " + hoursPlayed + "h");
                lblSoGioChoi.setFont(new Font("Arial", Font.BOLD, 14));
                lblSoGioChoi.setForeground(Color.RED);
                soGioChoi = Integer.parseInt(String.valueOf(hoursPlayed));
                hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
            } catch (HeadlessException ex) {
            }
        });
    }

    private void hienThiDanhSachSanTheoLoai(String tenLoaiSan) {
        jPanelDanhSachSan.removeAll();
        jPanelDanhSachSan.setLayout(new GridLayout(0, 3, 10, 10));
        jPanelDanhSachSan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelDanhSachSan.setBackground(Color.WHITE);

        ArrayList<SanDTO> listSan;
        if (tenLoaiSan.equals("Tất cả")) {
            listSan = sanBUS.getAllLoaiSan();
        } else {
            listSan = sanBUS.getByLoaiSan(tenLoaiSan);
        }

        for (SanDTO sp : listSan) {
            itemTaskbar item = new itemTaskbar(sp);
            item.setOnSanSelectedListener(san -> {
                sanDuocChon = san;
                hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
            });
            jPanelDanhSachSan.add(item);
        }

        jPanelDanhSachSan.revalidate();
        jPanelDanhSachSan.repaint();
    }

// Hiển thị 1 sân lên panel
    private void hienThiSanLenPanel(SanDTO san) {
        sanDuocChon = san;
        JLabel lblTenSan = new JLabel("Sân: " + san.getTensan());
        JLabel lblGiaSan = new JLabel("Giá: " + Formater.FormatVND(san.getGiasan()));

        lblTenSan.setFont(new Font("Arial", Font.BOLD, 18));
        lblGiaSan.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel pnlS = new JPanel(new GridLayout(1, 2));
        pnlS.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlS.setBackground(Color.WHITE);
        pnlS.setLayout(new BoxLayout(pnlS, BoxLayout.X_AXIS));
        pnlS.setPreferredSize(new Dimension(340, 40));
        pnlS.setMaximumSize(new Dimension(340, 40));
        pnlS.setMinimumSize(new Dimension(340, 40));
        pnlS.add(lblTenSan);
        pnlS.add(Box.createVerticalStrut(30));
        pnlS.add(lblGiaSan);
        pnlS.setAlignmentX(CENTER_ALIGNMENT);

        jPanelHoaDon.add(pnlS);
        jPanelHoaDon.add(Box.createVerticalStrut(10));
    }

// Hiển thị 1 sản phẩm lên panel
    private void hienThiSanPhamLenPanel(SanPhamDTO sp, JPanel jPanelList) {
        JLabel lblTenSP = new JLabel(sp.getTensanpham());
        JLabel lblSoLuong = new JLabel(sp.getSoluong() + "");
        JLabel lblGiaSP = new JLabel("x" + Formater.FormatVND(sp.getGiaban()));
        JLabel lblThanhTien = new JLabel(" " + Formater.FormatVND(sp.getGiaban() * sp.getSoluong()));

        Font fontBold = new Font("Arial", Font.BOLD, 13);
        Font font = new Font("Arial", Font.PLAIN, 13);
        lblTenSP.setFont(fontBold);
        lblSoLuong.setFont(font);
        lblGiaSP.setFont(font);
        lblThanhTien.setFont(fontBold);

        JPanel panelTen = new JPanel();
        panelTen.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTen.setOpaque(false);
        panelTen.add(lblTenSP);

        JPanel panelSoLuongGia = new JPanel();
        panelSoLuongGia.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSoLuongGia.setOpaque(false);
        panelSoLuongGia.add(lblSoLuong);
        panelSoLuongGia.add(lblGiaSP);

        JPanel panelThanhTien = new JPanel();
        panelThanhTien.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelThanhTien.setOpaque(false);
        panelThanhTien.add(lblThanhTien);

        JPanel pnlSP = new JPanel(new GridLayout(1, 3));
        pnlSP.setBorder(new EmptyBorder(5, 2, 5, 2));
        pnlSP.setBackground(colorStyle.colorForm());
        pnlSP.setLayout(new BoxLayout(pnlSP, BoxLayout.X_AXIS));
        pnlSP.setPreferredSize(new Dimension(290, 40));
        pnlSP.setMaximumSize(new Dimension(290, 40));
        pnlSP.setMinimumSize(new Dimension(290, 40));
        pnlSP.add(panelTen);
        pnlSP.add(panelSoLuongGia);
        pnlSP.add(panelThanhTien);
        pnlSP.setAlignmentX(CENTER_ALIGNMENT);
        pnlSP.setCursor(new Cursor(Cursor.HAND_CURSOR));

        danhSachPanelSanPham.add(pnlSP);

        pnlSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableSanPham.clearSelection();
                for (int i = 0; i < tableSanPham.getRowCount(); i++) {
                    if (tableSanPham.getValueAt(i, 0).equals(sp.getMasanpham())) {
                        tableSanPham.setRowSelectionInterval(i, i);
                        break;
                    }
                }

                for (JPanel panel : danhSachPanelSanPham) {
                    panel.setBackground(colorStyle.colorForm());
                }
                pnlSP.setBackground(new Color(200, 230, 255));
            }
        });

        jPanelList.add(pnlSP);

        jPanelList.setLayout(new BoxLayout(jPanelList, BoxLayout.Y_AXIS));
        jPanelList.add(Box.createVerticalStrut(5));
    }

// Hàm hiển thị tổng thể sân + danh sách sản phẩm
    private void hienThiVaoPanelHoaDon(SanDTO san, ArrayList<SanPhamDTO> dsSanPham) {
        jPanelHoaDon.removeAll();
        jPanelHoaDon.setLayout(new BoxLayout(jPanelHoaDon, BoxLayout.Y_AXIS));
        jPanelHoaDon.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel jpanelDanhSachSanPham = new JPanel();
        jpanelDanhSachSanPham.setLayout(new BoxLayout(jpanelDanhSachSanPham, BoxLayout.Y_AXIS));
        jpanelDanhSachSanPham.setBackground(Color.WHITE);

        if (san == null) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn sân",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        hienThiSanLenPanel(san);

        JLabel lblTieuDeSP = new JLabel("Danh sách sản phẩm:");
        lblTieuDeSP.setFont(new Font("Tahoma", Font.BOLD, 13));
        jPanelHoaDon.add(lblTieuDeSP);
        jPanelHoaDon.add(Box.createVerticalStrut(5));

        int tongTien = 0;
        for (SanPhamDTO sp : dsSanPham) {
            hienThiSanPhamLenPanel(sp, jpanelDanhSachSanPham);
            tongTien += sp.getSoluong() * sp.getGiaban();
        }

        JScrollPane scrollPaneSP = new JScrollPane(jpanelDanhSachSanPham);
        scrollPaneSP.getViewport().setPreferredSize(new Dimension(380, 300));
        scrollPaneSP.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        scrollPaneSP.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
        scrollPaneSP.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL));
        jPanelHoaDon.add(scrollPaneSP);

        JPanel panelTongTien = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTongTien.setOpaque(false);

        int gioTinhTien = (soGioChoi == 0) ? 1 : soGioChoi;
        thanhtien = tongTien + (san.getGiasan() * gioTinhTien);

        JLabel lblTongTien = new JLabel("Tổng tiền: " + Formater.FormatVND(thanhtien));
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTongTien.setForeground(Color.RED);
        panelTongTien.add(lblTongTien);

        jPanelHoaDon.add(panelTongTien);

        jPanelHoaDon.revalidate();
        jPanelHoaDon.repaint();
    }

    private SanPhamDTO cloneSanPhamWithSoLuong(SanPhamDTO sp, int soLuong) {
        SanPhamDTO clone = new SanPhamDTO();
        clone.setMasanpham(sp.getMasanpham());
        clone.setLoaisanpham(sp.getLoaisanpham());
        clone.setTensanpham(sp.getTensanpham());
        clone.setGiaban(sp.getGiaban());
        clone.setDonvi(sp.getDonvi());
        clone.setGhichu(sp.getGhichu());
        clone.setTrangthai(sp.getTrangthai());
        clone.setNgaytao(sp.getNgaytao());
        clone.setNgaycapnhat(sp.getNgaycapnhat());
        clone.setSoluong(soLuong);
        return clone;
    }

    @SuppressWarnings("empty-statement")
    public void loadDataTableSanPham(ArrayList<SanPhamDTO> result) {
        tblModelSanPham = new DefaultTableModel(headerSanPham, 0);
        int size = result.size();

        for (int i = 0; i < size; i++) {
            String trangThai = result.get(i).getTrangthai() == 1 ? "Còn hàng"
                    : result.get(i).getTrangthai() == 2 ? "Hết hàng" : "Dừng";
            tblModelSanPham.addRow(new Object[]{
                //                i + 1,
                result.get(i).getMasanpham(),
                result.get(i).getTensanpham(),
                result.get(i).getTenloaisanpham(),
                Formater.FormatVND(result.get(i).getGiaban()),
                result.get(i).getSoluong(),});
        };

        tableSanPham.setModel(tblModelSanPham);
        tableStyle.customizeTable(tableSanPham);
    }

    public KhachHangDTO getOrCreateKhachHang(String tenKH, String soDienThoai) {
        KhachHangDTO khach = khBUS.getBySoDienThoai(soDienThoai);

        if (khach == null) {
            int id = KhachHangDAO.getInstance().getAutoIncrement();
            khach = new KhachHangDTO(id, tenKH, soDienThoai, "", 1);
            khBUS.add(khach);
        }

        return khBUS.getBySoDienThoai(soDienThoai);
    }

    private void datSanAction() throws ParseException {
        if (sanDuocChon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sân để đặt!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String gioCheckinStr = txtCheckin.getSelectedItem().toString();
        String gioCheckoutStr = txtCheckout.getSelectedItem().toString();
        if (gioCheckinStr.equals("Tất cả") || gioCheckoutStr.equals("Tất cả")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giờ CheckIn và CheckOut!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalTime gioCheckin = LocalTime.parse(gioCheckinStr);
        LocalTime gioCheckout = LocalTime.parse(gioCheckoutStr);

        if (!gioCheckout.isAfter(gioCheckin)) {
            JOptionPane.showMessageDialog(this, "Giờ CheckOut phải lớn hơn CheckIn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sdt = txtSoDienThoaiKH.getText().trim();
        if (sdt.isEmpty() || !sdt.matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ (VD: 0912345678)!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nvDTO == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Nhân viên chưa đăng nhập");
            return;
        }

        KhachHangDTO khach = getOrCreateKhachHang(txtUsernameKH.getText(), txtSoDienThoaiKH.getText());

        DatSanDTO datSanMoi = new DatSanDTO();
        datSanMoi.setMasan(sanDuocChon.getMasan());
        datSanMoi.setMakhachhang(khach.getMakhachhang());
        datSanMoi.setManhanvien(nvDTO.getManhanvien());
        datSanMoi.setGiasan(sanDuocChon.getGiasan());
        datSanMoi.setTongtien(thanhtien);
        datSanMoi.setNgaydat(java.sql.Date.valueOf(LocalDate.now()));
        datSanMoi.setCheckin(gioCheckin);
        datSanMoi.setCheckout(gioCheckout);
        datSanMoi.setTrangthai(1);

        String kq = datsanBUS.datSanBUS(datSanMoi);
        JOptionPane.showMessageDialog(null, kq);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        txtUsernameKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSoDienThoaiKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLoaiSan = new javax.swing.JComboBox<>();
        txtLoaiSanPham = new javax.swing.JComboBox<>();
        jPanelHoaDon = new javax.swing.JPanel();
        jPanelMainDanhSachSan = new javax.swing.JPanel();
        jPanelDanhSachSan = new javax.swing.JPanel();
        jPanelDanhSachSanPham = new javax.swing.JPanel();
        jPanelButtonSanPham = new javax.swing.JPanel();
        btnThemSoLuong = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnTruSoLuong = new javax.swing.JButton();
        btnAddSanPham = new javax.swing.JButton();
        jPanelButtonSan = new javax.swing.JPanel();
        txtCheckin = new javax.swing.JComboBox<>();
        txtCheckout = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelTitle = new javax.swing.JPanel();
        jPanelThanhToan = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnHuySan = new javax.swing.JButton();
        btnDatSan = new javax.swing.JButton();
        lblCheckout = new javax.swing.JLabel();
        lblCheckin = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblSoGioChoi = new javax.swing.JLabel();

        jPanelHeader.setBackground(new java.awt.Color(255, 255, 255));

        txtUsernameKH.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtUsernameKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameKHFocusLost(evt);
            }
        });
        txtUsernameKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameKHActionPerformed(evt);
            }
        });
        txtUsernameKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKHKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKHKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        txtSoDienThoaiKH.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtSoDienThoaiKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiKHFocusLost(evt);
            }
        });
        txtSoDienThoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiKHActionPerformed(evt);
            }
        });
        txtSoDienThoaiKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKHKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKHKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Loại sản phẩm:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Loại sân:");

        txtLoaiSan.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtLoaiSan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtLoaiSan.setPreferredSize(new java.awt.Dimension(72, 20));

        txtLoaiSanPham.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtLoaiSanPham.setPreferredSize(new java.awt.Dimension(72, 20));

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtUsernameKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel3))
                            .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtLoaiSan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(194, 194, 194))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsernameKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLoaiSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(43, 43, 43)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanelHeaderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtLoaiSan, txtSoDienThoaiKH, txtUsernameKH});

        jPanelHoaDon.setBackground(new java.awt.Color(204, 255, 255));
        jPanelHoaDon.setMaximumSize(new java.awt.Dimension(339, 51));
        jPanelHoaDon.setMinimumSize(new java.awt.Dimension(339, 51));
        jPanelHoaDon.setPreferredSize(new java.awt.Dimension(340, 390));

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jPanelMainDanhSachSan.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanelDanhSachSanLayout = new javax.swing.GroupLayout(jPanelDanhSachSan);
        jPanelDanhSachSan.setLayout(jPanelDanhSachSanLayout);
        jPanelDanhSachSanLayout.setHorizontalGroup(
            jPanelDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelDanhSachSanLayout.setVerticalGroup(
            jPanelDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMainDanhSachSanLayout = new javax.swing.GroupLayout(jPanelMainDanhSachSan);
        jPanelMainDanhSachSan.setLayout(jPanelMainDanhSachSanLayout);
        jPanelMainDanhSachSanLayout.setHorizontalGroup(
            jPanelMainDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainDanhSachSanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDanhSachSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMainDanhSachSanLayout.setVerticalGroup(
            jPanelMainDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainDanhSachSanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDanhSachSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelDanhSachSanPham.setBackground(new java.awt.Color(255, 255, 204));
        jPanelDanhSachSanPham.setPreferredSize(new java.awt.Dimension(524, 337));

        javax.swing.GroupLayout jPanelDanhSachSanPhamLayout = new javax.swing.GroupLayout(jPanelDanhSachSanPham);
        jPanelDanhSachSanPham.setLayout(jPanelDanhSachSanPhamLayout);
        jPanelDanhSachSanPhamLayout.setHorizontalGroup(
            jPanelDanhSachSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        jPanelDanhSachSanPhamLayout.setVerticalGroup(
            jPanelDanhSachSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnThemSoLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-48.png"))); // NOI18N
        btnThemSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSoLuongActionPerformed(evt);
            }
        });

        btnXoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-48.png"))); // NOI18N
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnTruSoLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minus-48.png"))); // NOI18N
        btnTruSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruSoLuongActionPerformed(evt);
            }
        });

        btnAddSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-cart-48.png"))); // NOI18N
        btnAddSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonSanPhamLayout = new javax.swing.GroupLayout(jPanelButtonSanPham);
        jPanelButtonSanPham.setLayout(jPanelButtonSanPhamLayout);
        jPanelButtonSanPhamLayout.setHorizontalGroup(
            jPanelButtonSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTruSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelButtonSanPhamLayout.setVerticalGroup(
            jPanelButtonSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonSanPhamLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(btnAddSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnThemSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnTruSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        txtCheckin.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCheckin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtCheckin.setPreferredSize(new java.awt.Dimension(72, 20));
        txtCheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckinActionPerformed(evt);
            }
        });

        txtCheckout.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCheckout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtCheckout.setPreferredSize(new java.awt.Dimension(72, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("CheckIn:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setText("CheckOut:");

        javax.swing.GroupLayout jPanelButtonSanLayout = new javax.swing.GroupLayout(jPanelButtonSan);
        jPanelButtonSan.setLayout(jPanelButtonSanLayout);
        jPanelButtonSanLayout.setHorizontalGroup(
            jPanelButtonSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonSanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelButtonSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCheckin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanelButtonSanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelButtonSanLayout.setVerticalGroup(
            jPanelButtonSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonSanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        btnThanhToan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuySan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnHuySan.setText("HỦY SÂN");
        btnHuySan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuySanActionPerformed(evt);
            }
        });

        btnDatSan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDatSan.setText("ĐẶT SÂN");
        btnDatSan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDatSanMouseClicked(evt);
            }
        });
        btnDatSan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatSanActionPerformed(evt);
            }
        });

        lblCheckout.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblCheckout.setText("CheckOut:");

        lblCheckin.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblCheckin.setText("CheckIn:");

        lblSoDienThoai.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblSoDienThoai.setText("SĐT:");

        lblSoGioChoi.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblSoGioChoi.setText("TênKH:");

        javax.swing.GroupLayout jPanelThanhToanLayout = new javax.swing.GroupLayout(jPanelThanhToan);
        jPanelThanhToan.setLayout(jPanelThanhToanLayout);
        jPanelThanhToanLayout.setHorizontalGroup(
            jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDatSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelThanhToanLayout.createSequentialGroup()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuySan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelThanhToanLayout.createSequentialGroup()
                        .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCheckin, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCheckout, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(lblSoGioChoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelThanhToanLayout.setVerticalGroup(
            jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCheckin)
                    .addComponent(lblCheckout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoDienThoai)
                    .addComponent(lblSoGioChoi))
                .addGap(18, 18, 18)
                .addComponent(btnDatSan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHuySan, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDanhSachSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jPanelMainDanhSachSan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelButtonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelButtonSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanelThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMainDanhSachSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelButtonSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelDanhSachSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jPanelButtonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKHKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiKHKeyReleased

    private void txtSoDienThoaiKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiKHKeyPressed

    private void txtSoDienThoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiKHActionPerformed

    private void txtSoDienThoaiKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiKHFocusLost

    private void txtSoDienThoaiKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKHFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiKHFocusGained

    private void txtUsernameKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKHKeyReleased

    }//GEN-LAST:event_txtUsernameKHKeyReleased

    private void txtUsernameKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameKHKeyPressed

    private void txtUsernameKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameKHActionPerformed

    private void txtUsernameKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameKHFocusLost

    }//GEN-LAST:event_txtUsernameKHFocusLost

    private void txtUsernameKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameKHFocusGained

    }//GEN-LAST:event_txtUsernameKHFocusGained

    private void btnThemSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSoLuongActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnTruSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTruSoLuongActionPerformed

    private void btnAddSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddSanPhamActionPerformed

    private void txtCheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCheckinActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuySanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuySanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuySanActionPerformed

    private void btnDatSanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatSanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDatSanActionPerformed

    private void btnDatSanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDatSanMouseClicked
        try {
            datSanAction();
        } catch (ParseException ex) {
            Logger.getLogger(DatSan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDatSanMouseClicked

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = tableSanPham.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để thực hiện thao tác!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maSP = String.valueOf(tableSanPham.getValueAt(selectedRow, 0));
        SanPhamDTO sp = sanphamBUS.getByMaSP(maSP);

        if (sp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int soLuongTonKho = sp.getSoluong();
        boolean found = false;

        if (e.getSource() == btnAddSanPham || e.getSource() == btnThemSoLuong) {
            for (SanPhamDTO sanPhamDaChon : listSanPhamDaChon) {
                if (sanPhamDaChon.getMasanpham() == sp.getMasanpham()) {
                    int soLuongHienCo = sanPhamDaChon.getSoluong();
                    if (soLuongHienCo + 1 > soLuongTonKho) {
                        JOptionPane.showMessageDialog(this,
                                "Số lượng trong hóa đơn vượt quá số lượng tồn kho!",
                                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    sanPhamDaChon.setSoluong(soLuongHienCo + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                SanPhamDTO spClone = cloneSanPhamWithSoLuong(sp, 1);
                listSanPhamDaChon.add(spClone);
            }

            hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
        }

        if (e.getSource() == btnTruSoLuong) {
            for (Iterator<SanPhamDTO> iterator = listSanPhamDaChon.iterator(); iterator.hasNext();) {
                SanPhamDTO sanPhamDaChon = iterator.next();
                if (sanPhamDaChon.getMasanpham() == sp.getMasanpham()) {
                    int soLuongHienCo = sanPhamDaChon.getSoluong();
                    if (soLuongHienCo <= 1) {
                        iterator.remove(); // Xóa khỏi danh sách nếu còn 1 và người dùng bấm giảm
                    } else {
                        sanPhamDaChon.setSoluong(soLuongHienCo - 1);
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this,
                        "Sản phẩm chưa có trong hóa đơn!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
        }

        if (e.getSource() == btnXoaSanPham) {
            listSanPhamDaChon.removeIf(spInList -> spInList.getMasanpham() == sp.getMasanpham());
            hienThiVaoPanelHoaDon(sanDuocChon, listSanPhamDaChon);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == txtLoaiSanPham && e.getStateChange() == ItemEvent.SELECTED) {
            String selectedLoai = txtLoaiSanPham.getSelectedItem().toString();

            ArrayList<SanPhamDTO> filteredList;

            if (selectedLoai.equals("Tất cả")) {
                filteredList = listSanPham;
            } else {
                filteredList = new ArrayList<>();
                for (SanPhamDTO sp : listSanPham) {
                    if (sp.getTenloaisanpham().equalsIgnoreCase(selectedLoai)) {
                        filteredList.add(sp);
                    }
                }
            }

            loadDataTableSanPham(filteredList);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSanPham;
    private javax.swing.JButton btnDatSan;
    private javax.swing.JButton btnHuySan;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSoLuong;
    private javax.swing.JButton btnTruSoLuong;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelButtonSan;
    private javax.swing.JPanel jPanelButtonSanPham;
    private javax.swing.JPanel jPanelDanhSachSan;
    private javax.swing.JPanel jPanelDanhSachSanPham;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JPanel jPanelMainDanhSachSan;
    private javax.swing.JPanel jPanelThanhToan;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JLabel lblCheckin;
    private javax.swing.JLabel lblCheckout;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblSoGioChoi;
    private javax.swing.JComboBox<String> txtCheckin;
    private javax.swing.JComboBox<String> txtCheckout;
    private javax.swing.JComboBox<String> txtLoaiSan;
    private javax.swing.JComboBox<String> txtLoaiSanPham;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtUsernameKH;
    // End of variables declaration//GEN-END:variables
}
