package GUI.Panel;

import BUS.DatSanBUS;
import BUS.LoaiSanBUS;
import BUS.SanPhamBUS;
import DTO.DatSanDTO;
import DTO.LoaiSanDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.Component.CustomScrollBar;
import GUI.Component.TableModel;
import GUI.Main;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import style.StyleButton;
import style.StyleColor;
import style.StyleFont;
import style.StyleTable;
import utils.Formater;

/**
 *
 * @author phucp
 */
public final class DatSan extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    StyleFont fontStyle = new StyleFont();
    StyleColor colorStyle = new StyleColor();
    StyleTable tableStyle = new StyleTable();
    StyleButton buttonStyle = new StyleButton();
    Main m;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    NhanVienDTO nvDTO;

    private DatSanBUS datsanBUS = new DatSanBUS();
    private ArrayList<DatSanDTO> listDatSan;
    private TableModel<DatSanDTO> tableModelDatSan;

    JTable tableSanPham;
    DefaultTableModel tblModelSanPham;
    private SanPhamBUS sanphamBUS = new SanPhamBUS();
    private ArrayList<SanPhamDTO> listSanPham = sanphamBUS.getAllLoaiSanPhamHoatDong();
    private TableModel<SanPhamDTO> tableModelSanPham;

    private LoaiSanBUS loaiSanBUS = new LoaiSanBUS();
    private ArrayList<LoaiSanDTO> listLoaiSan;

    String[] headerSanPham = new String[]{"Mã SP", "Tên sản phẩm", "Loại sản phẩm", "Giá", "Số lượng"};
    String[] headerSan = new String[]{"Mã Sân", "Tên sân", "Giá", "Số lượng"};

    public DatSan(Main m) {
        this.listDatSan = datsanBUS.getAll();
//        this.loaiSanBUS = new LoaiSanBUS();
        this.m = m;
        initComponents();
        initComponent();

        loadDataTableSanPham(listSanPham);

    }

    private void initComponent() {
        fontStyle.setUIFont14();
        this.setSize(new Dimension(1030, 670));
        this.setBackground(colorStyle.mainBackgroundColor());
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelHeader();
        panelDanhSachSan();
        panelDanhSachSanPham();

        jPanelButtonSanPham.setBackground(Color.WHITE);
        buttonStyle.customizeButton(btnAddSanPham, "");
        buttonStyle.customizeButton(btnThemSoLuong, "");
        buttonStyle.customizeButton(btnXoaSanPham, "");
        buttonStyle.customizeButton(btnTruSoLuong, "");
    }

    private void panelHeader() {
        jPanelHeader.setBackground(colorStyle.backgroundColorOrange());
        txtUsernameKH.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtPhoneKH.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtDiaChiKH.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtGhiChu.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtTenSan.setBorder(new EmptyBorder(2, 10, 2, 10));

        listLoaiSan = loaiSanBUS.getAll();
        ArrayList<String> loaiSanNames = new ArrayList<>();
        loaiSanNames.add("Tất cả");
        loaiSanNames.addAll(
                listLoaiSan.stream()
                        .map(LoaiSanDTO::getTenloaisan)
                        .toList()
        );
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(loaiSanNames.toArray(String[]::new));
        txtLoaiSan.setModel(model);
        txtLoaiSan.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtLoaiSan.setBackground(Color.WHITE);
        txtLoaiSan.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(Color.WHITE);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
    }

    private void panelDanhSachSan() {
        jPanelDanhSachSan.setBackground(Color.WHITE);
    }

    private void panelDanhSachSanPham() {
//        jPanelDanhSachSanPham.setPreferredSize(new Dimension(1030, 337));
        jPanelDanhSachSanPham.setBackground(Color.LIGHT_GRAY);

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
        txtPhoneKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        txtDiaChiKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenSan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLoaiSan = new javax.swing.JComboBox<>();
        jPanelHoaDon = new javax.swing.JPanel();
        jPanelDanhSachSan = new javax.swing.JPanel();
        jPanelDanhSachSanPham = new javax.swing.JPanel();
        jPanelButtonSanPham = new javax.swing.JPanel();
        btnThemSoLuong = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnTruSoLuong = new javax.swing.JButton();
        btnAddSanPham = new javax.swing.JButton();

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

        txtPhoneKH.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtPhoneKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPhoneKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneKHFocusLost(evt);
            }
        });
        txtPhoneKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneKHActionPerformed(evt);
            }
        });
        txtPhoneKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKHKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneKHKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Ghi Chú:");

        txtGhiChu.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtGhiChu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGhiChuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGhiChuFocusLost(evt);
            }
        });
        txtGhiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuActionPerformed(evt);
            }
        });
        txtGhiChu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGhiChuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGhiChuKeyReleased(evt);
            }
        });

        txtDiaChiKH.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtDiaChiKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiaChiKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiKHFocusLost(evt);
            }
        });
        txtDiaChiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiKHActionPerformed(evt);
            }
        });
        txtDiaChiKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiaChiKHKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaChiKHKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        txtTenSan.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtTenSan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenSanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenSanFocusLost(evt);
            }
        });
        txtTenSan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanActionPerformed(evt);
            }
        });
        txtTenSan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenSanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSanKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Tên sân:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Loại sân:");

        txtLoaiSan.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtLoaiSan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtLoaiSan.setPreferredSize(new java.awt.Dimension(72, 20));

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
                            .addComponent(txtPhoneKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5))
                            .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel6))
                            .addComponent(txtTenSan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel7))
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtLoaiSan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4))
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(478, 478, 478))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsernameKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(txtLoaiSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanelHeaderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChiKH, txtGhiChu, txtLoaiSan, txtPhoneKH, txtTenSan, txtUsernameKH});

        jPanelHoaDon.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        jPanelDanhSachSan.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanelDanhSachSanLayout = new javax.swing.GroupLayout(jPanelDanhSachSan);
        jPanelDanhSachSan.setLayout(jPanelDanhSachSanLayout);
        jPanelDanhSachSanLayout.setHorizontalGroup(
            jPanelDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelDanhSachSanLayout.setVerticalGroup(
            jPanelDanhSachSanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        jPanelDanhSachSanPham.setBackground(new java.awt.Color(255, 255, 204));
        jPanelDanhSachSanPham.setPreferredSize(new java.awt.Dimension(524, 337));

        javax.swing.GroupLayout jPanelDanhSachSanPhamLayout = new javax.swing.GroupLayout(jPanelDanhSachSanPham);
        jPanelDanhSachSanPham.setLayout(jPanelDanhSachSanPhamLayout);
        jPanelDanhSachSanPhamLayout.setHorizontalGroup(
            jPanelDanhSachSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
                .addGap(22, 22, 22)
                .addComponent(btnAddSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTruSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDanhSachSan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelDanhSachSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelButtonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelDanhSachSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelDanhSachSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                .addContainerGap())
                            .addComponent(jPanelButtonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGhiChuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGhiChuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuKeyReleased

    private void txtGhiChuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGhiChuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuKeyPressed

    private void txtGhiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuActionPerformed

    private void txtGhiChuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGhiChuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuFocusLost

    private void txtGhiChuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGhiChuFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuFocusGained

    private void txtDiaChiKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKHKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKHKeyReleased

    private void txtDiaChiKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKHKeyPressed

    private void txtDiaChiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKHActionPerformed

    private void txtDiaChiKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKHFocusLost

    private void txtDiaChiKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiKHFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKHFocusGained

    private void txtPhoneKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKHKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneKHKeyReleased

    private void txtPhoneKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneKHKeyPressed

    private void txtPhoneKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneKHActionPerformed

    private void txtPhoneKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneKHFocusLost

    private void txtPhoneKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneKHFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneKHFocusGained

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

    private void txtTenSanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanKeyReleased

    private void txtTenSanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanKeyPressed

    private void txtTenSanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanActionPerformed

    private void txtTenSanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSanFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanFocusLost

    private void txtTenSanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSanFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanFocusGained

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

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSanPham;
    private javax.swing.JButton btnThemSoLuong;
    private javax.swing.JButton btnTruSoLuong;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelButtonSanPham;
    private javax.swing.JPanel jPanelDanhSachSan;
    private javax.swing.JPanel jPanelDanhSachSanPham;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JComboBox<String> txtLoaiSan;
    private javax.swing.JTextField txtPhoneKH;
    private javax.swing.JTextField txtTenSan;
    private javax.swing.JTextField txtUsernameKH;
    // End of variables declaration//GEN-END:variables
}
