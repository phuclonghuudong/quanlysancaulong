package GUI.Component;

import DTO.DatSanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import style.StyleFont;
import utils.Formater;

/**
 *
 * @author phucp
 */
public class itemTaskbar extends javax.swing.JPanel implements MouseListener {

    StyleFont fontStyle = new StyleFont();
    ImageCustome imgCustome = new ImageCustome();
    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, pnlContent, pnlSoLuong, pnlGia;
    JPanel right;
    JLabel img;
    public boolean isSelected;

    private OnSanSelectedListener onSanSelectedListener;

    public interface OnSanSelectedListener {

        void sanSelected(DatSanDTO san);
    }

    public void setOnSanSelectedListener(OnSanSelectedListener listener) {
        this.onSanSelectedListener = listener;
    }

    public itemTaskbar(String linkIcon, String content) {
        fontStyle.setUIFont16();
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 45));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);

        lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblIcon.setPreferredSize(new Dimension(45, 30));
        lblIcon.setIcon(imgCustome.loadAndResizeImage("./src/image/" + linkIcon, 30, 30));
        lblIcon.setHorizontalAlignment(JLabel.CENTER);
        lblIcon.setVerticalAlignment(JLabel.CENTER);
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(155, 30));
        pnlContent.setForeground(ColorBlack);
        this.add(pnlContent);
    }

    public itemTaskbar(String tenSP, double giaSan) {
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new JLabel("");
        img.setBorder(new EmptyBorder(2, 2, 2, 2));
        img.setIcon(new ImageIcon("./src/image/field-50-green.png"));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new FlowLayout(0, 0, 0));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        pnlContent = new JLabel("Tên: " + tenSP);
        pnlContent.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlContent.setForeground(Color.BLACK);
        right.add(pnlContent);

        String giaFormat = Formater.FormatVND(giaSan);
        pnlGia = new JLabel("Giá: " + giaFormat);
        pnlGia.setPreferredSize(new Dimension(350, 25));
        pnlGia.setFont(new Font("Tahoma", Font.BOLD, 11));
        pnlGia.setForeground(Color.GRAY);
        right.add(pnlGia);
    }

    public itemTaskbar(String tenSP, double giaSan, int soLuong) {
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new JLabel("");
        img.setBorder(new EmptyBorder(2, 2, 2, 2));
        img.setIcon(new ImageIcon("./src/image/field-50-green.png"));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setLayout(new FlowLayout(0, 0, 0));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        JPanel topRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topRowPanel.setOpaque(false);

        pnlContent = new JLabel("Tên: " + tenSP);
        pnlContent.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlContent.setForeground(Color.BLACK);
        topRowPanel.add(pnlContent);

        pnlSoLuong = new JLabel("Số lượng: " + soLuong);
        pnlSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlSoLuong.setForeground(Color.GRAY);
        topRowPanel.add(pnlSoLuong);

        right.add(topRowPanel);

        JPanel bottomRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bottomRowPanel.setOpaque(false);

        String giaFormat = Formater.FormatVND(giaSan);
        pnlGia = new JLabel("Giá: " + giaFormat);
        pnlGia.setPreferredSize(new Dimension(350, 25));
        pnlGia.setFont(new Font("Tahoma", Font.BOLD, 11));
        pnlGia.setForeground(Color.GRAY);
        bottomRowPanel.add(pnlGia);

        right.add(bottomRowPanel);
    }

    public itemTaskbar(DatSanDTO san, int type) {
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(100, 60));
        this.setBackground(new Color(255, 248, 230));
        this.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (onSanSelectedListener != null) {
                    onSanSelectedListener.sanSelected(san);
                }
            }
        });

        img = new JLabel("");
        img = new JLabel("");
        img.setBorder(new EmptyBorder(2, 2, 2, 2));
        String imageIcon = san.getTrangthai() == 5 ? "./src/image/field-50-blue.png"
                : san.getTrangthai() == 4 ? "./src/image/field-50-gray.png"
                : san.getTrangthai() == 3 ? "./src/image/field-50-green.png"
                : "./src/image/field-50-yellow.png";
        img.setIcon(new ImageIcon(imageIcon));
        img.setPreferredSize(new Dimension(45, 60));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        JPanel topRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topRowPanel.setOpaque(false);

        String giaFormat = Formater.FormatVND(san.getGiasan());
        pnlContent = new JLabel(san.getTensan() + " - " + giaFormat);
        pnlContent.setFont(new Font("Tahoma", Font.BOLD, 10));
        pnlContent.setForeground(Color.BLACK);
        topRowPanel.add(pnlContent);

        right.add(topRowPanel);

        JPanel bottomRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bottomRowPanel.setOpaque(false);

        String stt = san.getTrangthai() == 5 ? "Đã thanh toán"
                : (san.getTrangthai() == 3) ? "Đã đặt"
                : (san.getTrangthai() == 4) ? "Đã hủy" : "Trống";
        JLabel trangthai = new JLabel("CC:" + stt);
        trangthai.setFont(new Font("Arial", Font.BOLD, 10));
        bottomRowPanel.add(trangthai);
        right.add(bottomRowPanel);
    }

    public itemTaskbar(DatSanDTO ds, String type) {
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(340, 80));
        this.setBackground(new Color(255, 248, 230));
        this.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Font fontSize = new Font("Arial", Font.BOLD, 11);
        img = new JLabel("");
        img.setBorder(new EmptyBorder(2, 2, 2, 2));
        String imageIcon = ds.getTrangthai() == 5 ? "./src/image/field-50-blue.png"
                : ds.getTrangthai() == 4 ? "./src/image/field-50-gray.png"
                : ds.getTrangthai() == 3 ? "./src/image/field-50-green.png"
                : "./src/image/field-50-yellow.png";

        img.setIcon(new ImageIcon(imageIcon));
        img.setPreferredSize(new Dimension(45, 60));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        JPanel topRowPanel = new JPanel(new GridLayout(2, 2));
        topRowPanel.setOpaque(false);
        topRowPanel.setPreferredSize(new Dimension(340, 20));

        pnlContent = new JLabel(ds.getTensan());
        pnlContent.setFont(new Font("Arial", Font.BOLD, 11));
        pnlContent.setForeground(Color.BLACK);
        pnlContent.setPreferredSize(new Dimension(50, 20));
        topRowPanel.add(pnlContent);

// Giá sân
        String giaFormat = Formater.FormatVND(ds.getGiasan());
        JLabel pnlGia = new JLabel(giaFormat);
        pnlGia.setFont(fontSize);
        pnlGia.setForeground(Color.GRAY);

// Checkin
        JLabel checkin = new JLabel("In: " + ds.getCheckin());
        checkin.setFont(fontSize);
        checkin.setForeground(Color.BLACK);
// Checkout
        JLabel checkout = new JLabel("Out: " + ds.getCheckout());
        checkout.setFont(fontSize);
        checkout.setForeground(Color.BLACK);

        topRowPanel.add(pnlGia);
        topRowPanel.add(checkin);
        topRowPanel.add(checkout);

        JPanel bottomRowPanel = new JPanel(new GridLayout(2, 2));
        bottomRowPanel.setOpaque(false);
        // Tông tiền
        String thanhTienFormat = Formater.FormatVND(ds.getTongtien());
        JLabel pnlThanhTien = new JLabel("Tổng tiền: " + thanhTienFormat);
        pnlThanhTien.setFont(new Font("Arial", Font.BOLD, 12));
        pnlThanhTien.setForeground(Color.RED);
        bottomRowPanel.add(pnlThanhTien);
// Ngày đặt

        JLabel ngayDat = new JLabel(ds.getNgaydat() + "");
        ngayDat.setFont(new Font("Arial", Font.BOLD, 10));
        bottomRowPanel.add(ngayDat);
// Trang thái
        String stt = ds.getTrangthai() == 5 ? "Đã thanh toán" : (ds.getTrangthai() == 4) ? "Đã hủy" : "Đã đặt";
        JLabel trangthai = new JLabel("Trạng thái: " + stt);
        trangthai.setFont(new Font("Arial", Font.BOLD, 10));
        bottomRowPanel.add(trangthai);

        right.add(topRowPanel);
        right.add(bottomRowPanel);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(235, 237, 240));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(255, 255, 255));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
