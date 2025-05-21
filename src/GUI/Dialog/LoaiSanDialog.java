package GUI.Dialog;

import BUS.LoaiSanBUS;
import DAO.LoaiSanDAO;
import DTO.LoaiSanDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormInput;
import GUI.Component.HeaderTitle;
import GUI.Panel.LoaiSan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public final class LoaiSanDialog extends JDialog implements MouseListener {

    LoaiSan jPanelLS;
    LoaiSanDTO loaiSanDTO;
    LoaiSanBUS loaiSanBUS = new LoaiSanBUS();

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtTenLoai, txtGhiChu;
    private FormCheckbox txtTrangThai;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaLoaiSan;

    public LoaiSanDialog(LoaiSan jpLS, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelLS = jpLS;
        txtTenLoai = new FormInput("Tên loại sân");
        txtGhiChu = new FormInput("Chi chú");
        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        initComponents(title, type);
    }

    public LoaiSanDialog(LoaiSan jpLS, JFrame owner, String title, boolean modal, String type, DTO.LoaiSanDTO ls) {
        super(owner, title, modal);
        this.loaiSanDTO = ls;
        txtMaLoaiSan = new JTextField("");
        setMaloaisan(Integer.toString(ls.getMaloaisan()));
        txtTenLoai = new FormInput("Tên loại sân");
        setTenLoaiSan(ls.getTenloaisan());

        txtGhiChu = new FormInput("Mô tả");
        setGhiChu(ls.getGhichu());

        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangThai(ls.getTrangthai());

        this.jPanelLS = jpLS;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(4, 1, 5, 0));
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

    public String getMaloaisan() {
        return txtMaLoaiSan.getText();
    }

    public void setMaloaisan(String id) {
        this.txtMaLoaiSan.setText(id);
    }

    public String getTenLoaiSan() {
        return txtTenLoai.getText();
    }

    public void setTenLoaiSan(String text) {
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
        int currentID = (loaiSanDTO != null) ? loaiSanDTO.getMaloaisan() : -1;
        String validationMsg = jPanelLS.loaiSanBUS.validateLoaiSan(txtTenLoai.getText(), getTrangThai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            int id = LoaiSanDAO.getInstance().getAutoIncrement();
            jPanelLS.loaiSanBUS.add(new DTO.LoaiSanDTO(id,
                    txtTenLoai.getText(),
                    txtGhiChu.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelLS.loadDataTable(jPanelLS.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelLS.loaiSanBUS.update(new LoaiSanDTO(
                    loaiSanDTO.getMaloaisan(),
                    txtTenLoai.getText(),
                    txtGhiChu.getText(),
                    getTrangThai().equals("Hoạt động") ? 1 : 0));
            jPanelLS.loadDataTable(jPanelLS.listDS);
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
