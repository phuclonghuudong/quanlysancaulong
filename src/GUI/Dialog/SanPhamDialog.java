package GUI.Dialog;

import BUS.LoaiHangBUS;
import BUS.SanPhamBUS;
import DAO.SanDAO;
import DAO.SanPhamDAO;
import DTO.LoaiHangDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormInput;
import GUI.Component.FormSelect;
import GUI.Component.HeaderTitle;
import GUI.Panel.SanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author phucp
 */
public final class SanPhamDialog extends JDialog implements MouseListener {

    SanPham jPanelSP;
    SanPhamDTO spDTO;
    SanPhamBUS spBUS = new SanPhamBUS();
    LoaiHangBUS loaiHangBUS = new LoaiHangBUS();
    ArrayList<LoaiHangDTO> listLH;

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtTenSP, txtGhiChu, txtGiaBan, txtSoLuong, txtDonVi;
    private FormCheckbox txtTrangThai;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaSP;
    private FormSelect cbxLoaiSP;

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelSP = jpSP;
        listLH = loaiHangBUS.getAllStatus();
        String[] loaiSPNames = listLH.stream()
                .map(LoaiHangDTO::getTenloaihang)
                .toArray(String[]::new);
        txtTenSP = new FormInput("Tên sảm phẩm");
        cbxLoaiSP = new FormSelect("Loại sản phẩm", loaiSPNames);
        txtGiaBan = new FormInput("Giá bán");
        txtSoLuong = new FormInput("Số lượng");
        txtDonVi = new FormInput("Đơn vị");
        txtGhiChu = new FormInput("Ghi chú");
        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, DTO.SanPhamDTO s) {
        super(owner, title, modal);
        this.spDTO = s;
        txtMaSP = new JTextField("");
        setMaSanPham(Integer.toString(s.getMasanpham()));

        txtTenSP = new FormInput("Tên sản phẩm");
        setTenSanPham(s.getTensanpham());

        listLH = loaiHangBUS.getAllStatus();
        String[] loaiSPNames = listLH.stream()
                .map(LoaiHangDTO::getTenloaihang)
                .toArray(String[]::new);
        cbxLoaiSP = new FormSelect("Loại sản phẩm", loaiSPNames);
        setLoaiSanPham(s.getLoaisanpham());

        txtGiaBan = new FormInput("Giá bán");
        setGiaBan(String.valueOf(s.getGiaban()));

        txtSoLuong = new FormInput("Số lượng");
        setSoLuong(String.valueOf(s.getSoluong()));

        txtDonVi = new FormInput("Đơn vị");
        setDonVi(String.valueOf(s.getDonvi()));

        txtGhiChu = new FormInput("Ghi chú");
        setGhiChu(s.getGhichu());

        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangThai(s.getTrangthai());

        this.jPanelSP = jpSP;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(5, 1, 5, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(txtTenSP);
        pnlMain.add(cbxLoaiSP);
        pnlMain.add(txtGiaBan);
        pnlMain.add(txtSoLuong);
        pnlMain.add(txtDonVi);
        pnlMain.add(txtGhiChu);
        pnlMain.add(txtTrangThai);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustome("Thêm sản phẩm", "success", 14);
        btnCapNhat = new ButtonCustome("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustome("Huỷ bỏ", "danger", 14);

        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" ->
                pnlButtom.add(btnThem);
            case "update" ->
                pnlButtom.add(btnCapNhat);
            case "view" -> {
                txtTenSP.setDisable();
                cbxLoaiSP.setDisable();
                txtGiaBan.setDisable();
                txtSoLuong.setDisable();
                txtDonVi.setDisable();
                txtGhiChu.setDisable();
                txtTrangThai.setDisabled();
            }
            default ->
                throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private double parseGiaSan(String giaSanStr) {
        String cleaned = giaSanStr.replaceAll("[^\\d.]", "");
        return Double.parseDouble(cleaned);
    }

    private int parseSoLuong(String text) {
        String cleaned = text.replaceAll("[^\\d.]", "");
        return Integer.parseInt(cleaned);
    }

    private SanPhamDTO getSanPhamFromForm() {
        int index = cbxLoaiSP.getSelectedIndex();
        int idLoaiSP = listLH.get(index).getMaloaihang();
        int trangThai = switch (getTrangThai()) {
            case "Hoạt động" ->
                1;
            case "Bảo trì" ->
                2;
            default ->
                0;
        };
        return new SanPhamDTO(
                (spDTO != null) ? spDTO.getMasanpham() : SanPhamDAO.getInstance().getAutoIncrement(),
                idLoaiSP,
                txtTenSP.getText(),
                parseGiaSan(getGiaBan()),
                parseSoLuong(txtSoLuong.getText()),
                txtDonVi.getText(),
                txtGhiChu.getText(),
                trangThai
        );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currentID = (spDTO != null) ? spDTO.getMasanpham() : -1;
        String validationMsg = jPanelSP.spBUS.validateSanPham(getLoaiSanPham(), getTenSanPham(), parseGiaSan(getGiaBan()), parseSoLuong(getSoLuong()), getDonVi(), getGhiChu(), getTrangThai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            int id = SanDAO.getInstance().getAutoIncrement();

            jPanelSP.spBUS.add(getSanPhamFromForm());
            jPanelSP.listDS = jPanelSP.spBUS.getAllLoaiSanPham();
            jPanelSP.loadDataTable(jPanelSP.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelSP.spBUS.update(getSanPhamFromForm());
            jPanelSP.listDS = jPanelSP.spBUS.getAllLoaiSanPham();
            jPanelSP.loadDataTable(jPanelSP.listDS);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaSanPham() {
        return txtMaSP.getText();
    }

    public void setMaSanPham(String id) {
        this.txtMaSP.setText(id);
    }

    public String getGiaBan() {
        return txtGiaBan.getText();
    }

    public void setGiaBan(String text) {
        txtGiaBan.setText(text);
    }

    public String getTenSanPham() {
        return txtTenSP.getText();
    }

    public void setTenSanPham(String text) {
        txtTenSP.setText(text);
    }

    public int getLoaiSanPham() {
        int index = cbxLoaiSP.getSelectedIndex();
        return listLH.get(index).getMaloaihang();
    }

    public void setLoaiSanPham(int ID) {
        for (int i = 0; i < listLH.size(); i++) {
            if (listLH.get(i).getMaloaihang() == ID) {
                cbxLoaiSP.setSelectedIndex(i);
                break;
            }
        }
    }

    public String getGhiChu() {
        return txtGhiChu.getText();
    }

    public void setGhiChu(String text) {
        txtGhiChu.setText(text);
    }

    public String getTrangThai() {
        var selected = txtTrangThai.getSelectedValues();
        return selected.isEmpty() ? null : selected.get(0);

    }

    public void setTrangThai(int giatri) {
        String[] value = new String[]{giatri == 1 ? "Hoạt động" : giatri == 2 ? "Hết hàng" : "Dừng"};
        txtTrangThai.setSelectedValues(value);
    }

    public String getSoLuong() {
        return txtSoLuong.getText();
    }

    public void setSoLuong(String text) {
        txtSoLuong.setText(text);
    }

    public String getDonVi() {
        return txtDonVi.getText();
    }

    public void setDonVi(String text) {
        txtDonVi.setText(text);
    }
}
