package GUI.Dialog;

import BUS.LoaiHangBUS;
import DAO.LoaiHangDAO;
import DTO.LoaiHangDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormInput;
import GUI.Component.HeaderTitle;
import GUI.Panel.LoaiSanPham;
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
public final class LoaiSanPhamDialog extends JDialog implements MouseListener {

    LoaiSanPham jPanelLSP;
    LoaiHangDTO loaiHangDTO;
    LoaiHangBUS loaiHangBUS = new LoaiHangBUS();

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtTenLoai, txtGhiChu;
    private FormCheckbox txtTrangThai;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaLoaiHang;

    public LoaiSanPhamDialog(LoaiSanPham jpLS, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelLSP = jpLS;
        txtTenLoai = new FormInput("Tên loại sản phẩm");
        txtGhiChu = new FormInput("Ghi chú");
        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        initComponents(title, type);
    }

    public LoaiSanPhamDialog(LoaiSanPham jpLS, JFrame owner, String title, boolean modal, String type, DTO.LoaiHangDTO ls) {
        super(owner, title, modal);
        this.loaiHangDTO = ls;
        txtMaLoaiHang = new JTextField("");
        setMaloaihang(Integer.toString(ls.getMaloaihang()));
        txtTenLoai = new FormInput("Tên loại sân");
        setTenLoaiHang(ls.getTenloaihang());

        txtGhiChu = new FormInput("Ghi chú");
        setGhiChu(ls.getGhichu());

        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangThai(ls.getTrangthai());

        this.jPanelLSP = jpLS;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 400));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(3, 1, 5, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(txtTenLoai);
        pnlMain.add(txtGhiChu);
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
                txtTenLoai.setDisable();
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

    public String getMaloaihang() {
        return txtMaLoaiHang.getText();
    }

    public void setMaloaihang(String id) {
        this.txtMaLoaiHang.setText(id);
    }

    public String getTenLoaiHang() {
        return txtTenLoai.getText();
    }

    public void setTenLoaiHang(String text) {
        txtTenLoai.setText(text);
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
        String[] value = new String[]{giatri == 1 ? "Hoạt động" : "Dừng"};
        txtTrangThai.setSelectedValues(value);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currentID = (loaiHangDTO != null) ? loaiHangDTO.getMaloaihang() : -1;
        String validationMsg = jPanelLSP.loaiHangBUS.validateLoaiHang(txtTenLoai.getText(), getTrangThai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            int id = LoaiHangDAO.getInstance().getAutoIncrement();
            jPanelLSP.loaiHangBUS.add(new DTO.LoaiHangDTO(id,
                    txtTenLoai.getText(),
                    txtGhiChu.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelLSP.loadDataTable(jPanelLSP.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelLSP.loaiHangBUS.update(new LoaiHangDTO(
                    loaiHangDTO.getMaloaihang(),
                    txtTenLoai.getText(),
                    txtGhiChu.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelLSP.loadDataTable(jPanelLSP.listDS);
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

}
