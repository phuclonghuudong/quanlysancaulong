package GUI.Dialog;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormInput;
import GUI.Component.HeaderTitle;
import GUI.Panel.KhachHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author phucp
 */
public final class KhachHangDialog extends JDialog implements MouseListener {

    KhachHang jPanelKH;
    KhachHangDTO khDTO;
    KhachHangBUS khBUS = new KhachHangBUS();

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtHoTen, txtDiaChi, txtSoDienThoai;
    private FormCheckbox txtTrangThai;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaKhachHang;

    public KhachHangDialog(KhachHang jpKH, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelKH = jpKH;
        txtHoTen = new FormInput("Họ tên");
        txtSoDienThoai = new FormInput("Số điện thoại");
        txtDiaChi = new FormInput("Địa chỉ");
        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        initComponents(title, type);
    }

    public KhachHangDialog(KhachHang jpLS, JFrame owner, String title, boolean modal, String type, DTO.KhachHangDTO ls) {
        super(owner, title, modal);
        this.khDTO = ls;
        txtMaKhachHang = new JTextField("");
        setMakhachhang(Integer.toString(ls.getMakhachhang()));
        txtHoTen = new FormInput("Họ tên");
        setHoten(ls.getHoten());

        txtSoDienThoai = new FormInput("Số điện thoại");
        setSodienthoai(ls.getSodienthoai());

        txtDiaChi = new FormInput("Địa chỉ");
        setDiachi(ls.getDiachi());

        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangThai(ls.getTrangthai());

        this.jPanelKH = jpLS;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(4, 1, 5, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(txtHoTen);
        pnlMain.add(txtSoDienThoai);
        pnlMain.add(txtDiaChi);
        pnlMain.add(txtTrangThai);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustome("Thêm loại", "success", 14);
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
                txtSoDienThoai.setDisable();
                txtDiaChi.setDisable();
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

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currentID = (khDTO != null) ? khDTO.getMakhachhang() : -1;
        String validationMsg = jPanelKH.khBUS.validateKhachHang(getHoten(), getSodienthoai(), getDiachi(), getTrangThai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            int id = KhachHangDAO.getInstance().getAutoIncrement();
            jPanelKH.khBUS.add(new DTO.KhachHangDTO(id,
                    txtHoTen.getText(),
                    txtSoDienThoai.getText(),
                    txtDiaChi.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelKH.loadDataTable(jPanelKH.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelKH.khBUS.update(new KhachHangDTO(
                    khDTO.getMakhachhang(),
                    txtHoTen.getText(),
                    txtSoDienThoai.getText(),
                    txtDiaChi.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelKH.loadDataTable(jPanelKH.listDS);
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

    public String getMakhachhang() {
        return txtMaKhachHang.getText();
    }

    public void setMakhachhang(String id) {
        this.txtMaKhachHang.setText(id);
    }

    public String getHoten() {
        return txtHoTen.getText();
    }

    public void setHoten(String text) {
        txtHoTen.setText(text);
    }

    public String getDiachi() {
        return txtDiaChi.getText();
    }

    public void setDiachi(String text) {
        txtDiaChi.setText(text);
    }

    public String getSodienthoai() {
        return txtSoDienThoai.getText();
    }

    public void setSodienthoai(String text) {
        txtSoDienThoai.setText(text);
    }

    public String getTrangThai() {
        var selected = txtTrangThai.getSelectedValues();
        return selected.isEmpty() ? null : selected.get(0);

    }

    public void setTrangThai(int giatri) {
        String[] value = new String[]{giatri == 1 ? "Hoạt động" : "Dừng"};
        txtTrangThai.setSelectedValues(value);
    }
}
