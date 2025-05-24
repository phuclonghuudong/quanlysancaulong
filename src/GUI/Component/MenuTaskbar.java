package GUI.Component;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Dialog.MyAccount;
import GUI.Log_In;
import GUI.Main;
import GUI.Panel.DatSan;
import GUI.Panel.KhachHang;
import GUI.Panel.LoaiSan;
import GUI.Panel.LoaiSanPham;
import GUI.Panel.NhanVien;
import GUI.Panel.San;
import GUI.Panel.SanPham;
import GUI.Panel.TrangChu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import style.StyleColor;
import style.StyleFont;

/**
 *
 * @author phucp
 */
public final class MenuTaskbar extends javax.swing.JPanel {

    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;
    JScrollPane scrollPane;
    StyleColor colorStyle = new StyleColor();

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);

    JLabel lblUsername, lblTenNhomQuyen;

    Main main;
    TrangChu trangChu;
    KhachHang khachHang;
    San san;
    LoaiSan loaiSan;
    NhanVien nhanVien;
    DatSan datSan;
    LoaiSanPham loaiSanPham;
    SanPham sanPham;

    public NhanVienDTO nhanVienDTO;

    ImageCustome imgCustome = new ImageCustome();
    StyleFont fontStyle = new StyleFont();

    public itemTaskbar[] listitem;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    String[][] getSt = {
        {"Trang chủ", "home-240.png", "trangchu"},
        {"Đặt sân", "icons8-basketball-court-100.png", "datsan"},
        {"Khách hàng", "user1-100.png", "khachhang"},
        {"Nhân viên", "user2-100.png", "nhanvien"},
        {"QL loại sản phẩm", "icons8-opened-folder-100.png", "loaisanpham"},
        {"QL loại sân", "category-100.png", "loaisan"},
        {"QL sản phẩm", "icons8-product-100.png", "sanpham"},
        {"QL sân", "court-100.png", "san"},
        {"Đăng xuất", "shutdown-96.png", "dangxuat"},};

    public void handleMenuClick(int index) {
        switch (getSt[index][2]) {
            case "trangchu" -> {
                trangChu = new TrangChu();
                main.setPanel(trangChu);
            }
            case "datsan" -> {
                datSan = new DatSan(main);
                main.setPanel(datSan);
            }
            case "khachhang" -> {
                khachHang = new KhachHang(main);
                main.setPanel(khachHang);
            }
            case "nhanvien" -> {
                nhanVien = new NhanVien(main);
                main.setPanel(nhanVien);
            }
            case "loaisan" -> {
                loaiSan = new LoaiSan(main);
                main.setPanel(loaiSan);
            }
            case "san" -> {
                san = new San(main);
                main.setPanel(san);
            }
            case "loaisanpham" -> {
                loaiSanPham = new LoaiSanPham(main);
                main.setPanel(loaiSanPham);
            }
            case "sanpham" -> {
                sanPham = new SanPham(main);
                main.setPanel(sanPham);
            }
            case "dangxuat" -> {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    Log_In login = new Log_In();
                    main.dispose();
                    login.setVisible(true);
                }
            }
        }
    }

    public boolean checkRole(String chucNang) {
        if (nhanVienDTO == null || nhanVienDTO.getVaitro() == null) {
            return false;
        }

        String vaiTro = nhanVienDTO.getVaitro().toLowerCase();
        return switch (chucNang) {
            case "nhanvien", "loaisan", "san", "loaisanpham", "sanpham" ->
                vaiTro.equals("admin");
            default ->
                true;
        };

    }

    public MenuTaskbar(Main main) {
        this.main = main;
//        initComponents();
        initComponent();
    }

    public MenuTaskbar(Main main, NhanVienDTO user) {
        this.main = main;
        this.nhanVienDTO = user;
//        initComponents();
        initComponent();
    }

    public void initComponent() {
        listitem = new itemTaskbar[getSt.length];
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(DefaultColor);

        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 70));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);
        in4(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 600));
        pnlCenter.setBackground(DefaultColor);
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));

        bar3 = new JPanel();
        bar3.setBackground(colorStyle.mainBackgroundColor());
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
        scrollPane.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setBackground(DefaultColor);
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        for (int i = 0; i < getSt.length; i++) {
            listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);

//            CHECK ROLE
            boolean enableRoleCheck = false;
            if (enableRoleCheck && i != 0 && i != getSt.length - 1 && !checkRole(getSt[i][2])) {
                continue;
            }
//            CHECK ROLE

            if (i == getSt.length - 1) {
                pnlBottom.add(listitem[i]);
            } else {
                pnlCenter.add(listitem[i]);
            }

            int index = i;
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    handleMenuClick(index);
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }

    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {
        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }

    public void resetChange() {
        this.nhanVienDTO = new NhanVienDAO().selectById(String.valueOf(nhanVienDTO.getManhanvien()));
    }

    public void in4(JPanel info) {
        fontStyle.setUIFont16();
        JPanel pnlIcon = new JPanel(new FlowLayout());
        pnlIcon.setPreferredSize(new Dimension(60, 0));
        pnlIcon.setOpaque(false);
        pnlIcon.setBorder(new EmptyBorder(5, 10, 5, 5));
        info.add(pnlIcon, BorderLayout.WEST);

        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 70));
        lblIcon.setIcon(imgCustome.loadAndResizeImage("./src/image/icons8-man-100.png", 45, 45));
        pnlIcon.add(lblIcon);

        JPanel pnlInfo = new JPanel();
        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new GridLayout(2, 1, 0, 2));
        pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 0));
        info.add(pnlInfo, BorderLayout.CENTER);

        lblUsername = new JLabel();
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));

        lblTenNhomQuyen = new JLabel();
        lblTenNhomQuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTenNhomQuyen.setForeground(Color.GRAY);

        if (nhanVienDTO != null) {
            lblUsername.setText(nhanVienDTO.getHoten());
            lblTenNhomQuyen.setText(nhanVienDTO.getVaitro());

            lblIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    MyAccount ma = new MyAccount(owner, MenuTaskbar.this, "Chỉnh sửa thông tin tài khoản", true);
                }
            });
        } else {
            lblUsername.setText("Chưa đăng nhập");
            lblTenNhomQuyen = new JLabel("Không rõ vai trò");
        }
        pnlInfo.add(lblUsername);
        pnlInfo.add(lblTenNhomQuyen);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
