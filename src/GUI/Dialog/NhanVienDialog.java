package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormDate;
import GUI.Component.FormInput;
import GUI.Component.FormSelect;
import GUI.Component.HeaderTitle;
import GUI.Panel.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.Validation;

/**
 *
 * @author phucp
 */
public final class NhanVienDialog extends JDialog implements MouseListener {

    NhanVien jPanelNV;
    NhanVienDTO nvDTO;
    NhanVienBUS nvBUS = new NhanVienBUS();

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtHoTen, txtEmail, txtSoDienThoai;
    private FormCheckbox txtTrangThai, txtGioiTinh;
    private FormDate txtNgaySinh;
    private FormSelect txtVaiTro;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaNhanVien;

    public NhanVienDialog(NhanVien jpNV, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelNV = jpNV;
        txtHoTen = new FormInput("Họ tên");

        txtEmail = new FormInput("Email:");

        txtSoDienThoai = new FormInput("Số điện thoại");

        String[] listVT = new String[]{"ADMIN", "NHANVIEN", "USER"};
        txtVaiTro = new FormSelect("Vai trò", listVT);

        String[] listStatus = new String[]{"Hoạt động", "Chờ xác nhận", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        String[] listGioiTinh = new String[]{"Nam", "Nữ"};
        txtGioiTinh = new FormCheckbox("Giới tính", listGioiTinh);

        txtNgaySinh = new FormDate("Ngày sinh:");

        initComponents(title, type);
    }

    public NhanVienDialog(NhanVien jpNV, JFrame owner, String title, boolean modal, String type, DTO.NhanVienDTO nv) {
        super(owner, title, modal);
        this.nvDTO = nv;
        txtMaNhanVien = new JTextField("");
        setManhanvien(Integer.toString(nv.getManhanvien()));

        txtHoTen = new FormInput("Họ tên");
        setHoten(nv.getHoten());

        txtEmail = new FormInput("Email");
        setEmail(nv.getEmail());

        txtSoDienThoai = new FormInput("Số điện thoại");
        setSodienthoai(nv.getSodienthoai());

        String[] listVT = new String[]{"ADMIN", "NHANVIEN", "USER"};
        txtVaiTro = new FormSelect("Vai trò", listVT);
        txtVaiTro.setSelectedItem(nv.getVaitro());

        String[] listStatus = new String[]{"Hoạt động", "Chờ xác nhận", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangthai(nv.getTrangthai());

        String[] listGioiTinh = new String[]{"Nam", "Nữ"};
        txtGioiTinh = new FormCheckbox("Giới tính", listGioiTinh);
        setGioiTinh(nv.isGioitinh());

        txtNgaySinh = new FormDate("Ngày sinh");
        setNgaysinh(nv.getNgaysinh());

        this.jPanelNV = jpNV;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(900, 500));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(4, 2, 5, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(txtHoTen);
        pnlMain.add(txtEmail);
        pnlMain.add(txtSoDienThoai);
        pnlMain.add(txtVaiTro);
        pnlMain.add(txtGioiTinh);
        pnlMain.add(txtTrangThai);
        pnlMain.add(txtNgaySinh);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustome("Thêm người dùng", "success", 14);
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
                txtHoTen.setDisable();
                txtEmail.setDisable();
                txtSoDienThoai.setDisable();
                txtVaiTro.setDisable();
                txtGioiTinh.setDisabled();
                txtTrangThai.setDisabled();
                txtNgaySinh.setDisabled();
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

    private NhanVienDTO getNhanVienFromForm() {
        int trangThai = switch (getTrangthai()) {
            case "Hoạt động" ->
                1;
            case "Chờ xác nhận" ->
                2;
            default ->
                0;
        };
        String hashedPassword = Validation.hashPassword(txtSoDienThoai.getText());

        Date utilDate = txtNgaySinh.getDate();

        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return new NhanVienDTO(
                (nvDTO != null) ? nvDTO.getManhanvien() : NhanVienDAO.getInstance().getAutoIncrement(),
                txtHoTen.getText(),
                txtEmail.getText(),
                txtSoDienThoai.getText(), (String) txtVaiTro.getSelectedItem(),
                isGioitinh().equals("Nữ"),
                sqlDate,
                hashedPassword,
                trangThai
        );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currentID = (nvDTO != null) ? nvDTO.getManhanvien() : -1;
        String validationMsg = jPanelNV.nvBUS.validateNhanVien(getHoten(), getEmail(), getSodienthoai(),
                getVaitro(), isGioitinh(), getNgaysinh(), getTrangthai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            jPanelNV.nvBUS.add(getNhanVienFromForm());
            jPanelNV.listDS = jPanelNV.nvBUS.getAll();
            jPanelNV.loadDataTable(jPanelNV.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelNV.nvBUS.update(getNhanVienFromForm());
            jPanelNV.listDS = jPanelNV.nvBUS.getAll();
            jPanelNV.loadDataTable(jPanelNV.listDS);
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

    public String getManhanvien() {
        return txtMaNhanVien.getText();
    }

    public void setManhanvien(String text) {
        this.txtMaNhanVien.setText(text);
    }

    public String getHoten() {
        return txtHoTen.getText();
    }

    public void setHoten(String text) {
        txtHoTen.setText(text);
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public void setEmail(String text) {
        txtEmail.setText(text);
    }

    public Date getNgaysinh() {
        return txtNgaySinh.getDate();
    }

    public void setNgaysinh(Date text) {
        txtNgaySinh.setSelectedDate(text);
    }

    public String getSodienthoai() {
        return txtSoDienThoai.getText();
    }

    public void setSodienthoai(String text) {
        txtSoDienThoai.setText(text);
    }

    public String getVaitro() {
        return (String) txtVaiTro.getSelectedItem();
    }

    public void setVaitro(String text) {
        txtVaiTro.setSelectedItem(text);
    }

    public String isGioitinh() {
        var selected = txtGioiTinh.getSelectedValues();
        return selected.isEmpty() ? null : selected.get(0);
    }

    public void setGioiTinh(boolean text) {
        String[] value = new String[]{text ? "Nữ" : "Nam"};
        txtGioiTinh.setSelectedValues(value);
    }

    public String getTrangthai() {
        var selected = txtTrangThai.getSelectedValues();
        return selected.isEmpty() ? null : selected.get(0);

    }

    public void setTrangthai(int giatri) {
        String[] value = new String[]{giatri == 1 ? "Hoạt động" : giatri == 2 ? "Chờ xác nhận" : "Dừng"};
        txtTrangThai.setSelectedValues(value);
    }
}
