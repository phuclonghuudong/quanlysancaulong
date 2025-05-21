package GUI.Dialog;

import BUS.LoaiSanBUS;
import BUS.SanBUS;
import DAO.SanDAO;
import DTO.LoaiSanDTO;
import DTO.SanDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormCheckbox;
import GUI.Component.FormInput;
import GUI.Component.FormSelect;
import GUI.Component.HeaderTitle;
import GUI.Panel.San;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author phucp
 */
public final class SanDialog extends JDialog implements MouseListener {

    San jPanelS;
    SanDTO sDTO;
    SanBUS sBUS = new SanBUS();
    LoaiSanBUS loaiSanBUS = new LoaiSanBUS();
    ArrayList<LoaiSanDTO> listLoaiSan;

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private FormInput txtTenSan, txtGhiChu, txtGiaSan;
    private FormCheckbox txtTrangThai;
    private ButtonCustome btnThem, btnCapNhat, btnHuyBo;
    private JTextField txtMaSan;
    private FormSelect cbxLoaiSan;

    public SanDialog(San jpS, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jPanelS = jpS;
        listLoaiSan = loaiSanBUS.getAllStatus();
        String[] loaiSanNames = listLoaiSan.stream()
                .map(LoaiSanDTO::getTenloaisan)
                .toArray(String[]::new);
        txtTenSan = new FormInput("Tên sân");
        cbxLoaiSan = new FormSelect("Loại sân", loaiSanNames);
        txtGiaSan = new FormInput("Giá sân");
        txtGhiChu = new FormInput("Ghi chú");
        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);

        initComponents(title, type);
    }

    public SanDialog(San jpS, JFrame owner, String title, boolean modal, String type, DTO.SanDTO s) {
        super(owner, title, modal);
        this.sDTO = s;
        txtMaSan = new JTextField("");
        setMaSan(Integer.toString(s.getMasan()));

        txtTenSan = new FormInput("Tên sân");
        setTenSan(s.getTensan());

        listLoaiSan = loaiSanBUS.getAllStatus();
        String[] loaiSanNames = listLoaiSan.stream()
                .map(LoaiSanDTO::getTenloaisan)
                .toArray(String[]::new);
        cbxLoaiSan = new FormSelect("Loại sân", loaiSanNames);
        setLoaiSan(s.getLoaisan());

        txtGiaSan = new FormInput("Giá sân");
        setGiaSan(String.valueOf(s.getGiasan()));

        txtGhiChu = new FormInput("Ghi chú");
        setGhiChu(s.getGhichu());

        String[] listStatus = new String[]{"Hoạt động", "Dừng"};
        txtTrangThai = new FormCheckbox("Trạng thái", listStatus);
        setTrangThai(s.getTrangthai());

        this.jPanelS = jpS;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(5, 1, 5, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(txtTenSan);
        pnlMain.add(cbxLoaiSan);
        pnlMain.add(txtGiaSan);
        pnlMain.add(txtGhiChu);
        pnlMain.add(txtTrangThai);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustome("Thêm sân", "success", 14);
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
                txtTenSan.setDisable();
                cbxLoaiSan.setDisable();
                txtGiaSan.setDisable();
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
        String cleaned = giaSanStr.replaceAll("[^\\d.]", ""); // Xóa các ký tự không phải số hoặc dấu chấm
        return Double.parseDouble(cleaned);
    }

    private SanDTO getSanFromForm() {
        int index = cbxLoaiSan.getSelectedIndex();
        int idLoaiSan = listLoaiSan.get(index).getMaloaisan();
        int trangThai = switch (getTrangThai()) {
            case "Hoạt động" ->
                1;
            case "Bảo trì" ->
                2;
            default ->
                0;
        };
        return new SanDTO(
                (sDTO != null) ? sDTO.getMasan() : SanDAO.getInstance().getAutoIncrement(),
                idLoaiSan,
                txtTenSan.getText(),
                parseGiaSan(getGiaSan()),
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

        int currentID = (sDTO != null) ? sDTO.getMasan() : -1;
        String validationMsg = jPanelS.sanBUS.validateSan(getLoaiSan(), getTenSan(), parseGiaSan(getGiaSan()), getGhiChu(), getTrangThai(), currentID);

        if (!validationMsg.equals("valid")) {
            JOptionPane.showMessageDialog(this, validationMsg, "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (e.getSource() == btnThem) {
            int id = SanDAO.getInstance().getAutoIncrement();

            jPanelS.sanBUS.add(getSanFromForm());
            jPanelS.listDS = jPanelS.sanBUS.getAllLoaiSan();
            jPanelS.loadDataTable(jPanelS.listDS);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            jPanelS.sanBUS.update(getSanFromForm());
            jPanelS.listDS = jPanelS.sanBUS.getAllLoaiSan();
            jPanelS.loadDataTable(jPanelS.listDS);
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

    public String getMaSan() {
        return txtMaSan.getText();
    }

    public void setMaSan(String id) {
        this.txtMaSan.setText(id);
    }

    public String getGiaSan() {
        return txtGiaSan.getText();
    }

    public void setGiaSan(String text) {
        txtGiaSan.setText(text);
    }

    public String getTenSan() {
        return txtTenSan.getText();
    }

    public void setTenSan(String text) {
        txtTenSan.setText(text);
    }

    public int getLoaiSan() {
        int index = cbxLoaiSan.getSelectedIndex();
        return listLoaiSan.get(index).getMaloaisan();
    }

    public void setLoaiSan(int ID) {
        for (int i = 0; i < listLoaiSan.size(); i++) {
            if (listLoaiSan.get(i).getMaloaisan() == ID) {
                cbxLoaiSan.setSelectedIndex(i);
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
        String[] value = new String[]{giatri == 1 ? "Hoạt động" : "Dừng"};
        txtTrangThai.setSelectedValues(value);
    }
}
