package GUI.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author phucp
 */
public class IntegratedSearch extends JPanel {

    public JComboBox<String> cbxChoose;
    public JButton btnReset;
    public JTextField txtSearchForm;
    JScrollPane scrollPane;
    private ImageCustome imgCustom = new ImageCustome();

    private void initComponent(String str[]) {

        this.setBackground(Color.WHITE);
        BoxLayout bx = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(bx);

        JPanel jpSearch = new JPanel(new BorderLayout(5, 10));
        jpSearch.setBorder(new EmptyBorder(20, 15, 20, 15));
        jpSearch.setBackground(Color.WHITE);
        cbxChoose = new JComboBox();
        cbxChoose.setModel(new DefaultComboBoxModel<>(str));
        cbxChoose.setPreferredSize(new Dimension(120, 20));
        cbxChoose.setFont(new Font("Tahoma", Font.BOLD, 13));
        cbxChoose.setBackground(new Color(255, 204, 153));
        cbxChoose.setForeground(new Color(50, 50, 50));
        cbxChoose.setBorder(BorderFactory.createEmptyBorder());
        cbxChoose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbxChoose.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(new Color(250, 241, 230));
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(new Color(250, 241, 230));
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
        cbxChoose.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    label.setBackground(new Color(255, 204, 153));  // Màu nền khi chọn
                    label.setForeground(Color.BLACK);                // Màu chữ khi chọn
                } else {
                    label.setBackground(Color.WHITE);               // Màu nền khi không chọn
                    label.setForeground(Color.BLACK);               // Màu chữ khi không chọn
                }
                return label;
            }
        });

        jpSearch.add(cbxChoose, BorderLayout.WEST);

        txtSearchForm = new JTextField();
        txtSearchForm.setPreferredSize(new Dimension(120, 20));
        txtSearchForm.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSearchForm.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtSearchForm.setBackground(new Color(238, 241, 218));
        txtSearchForm.putClientProperty("JTextField.placeholderText", "Nhập nội dung tìm kiếm...");
//        txtSearchForm.putClientProperty("JTextField.showClearButton", true);
        jpSearch.add(txtSearchForm);

        btnReset = new JButton("Làm mới");
        btnReset.setBackground(new Color(255, 204, 153));
        btnReset.setForeground(Color.BLACK);
        btnReset.setPreferredSize(new Dimension(100, 20));
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReset.setBorderPainted(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        btnReset.setIcon(imgCustom.loadAndResizeImage("./src/icon/refresh.png", 30, 30));

        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnReset.setForeground(Color.BLACK);
                btnReset.setBackground(new Color(204, 214, 219));
                btnReset.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReset.setForeground(Color.BLACK);
                btnReset.setBackground(new Color(255, 204, 153));
                btnReset.setOpaque(true);
            }
        });
        jpSearch.add(btnReset, BorderLayout.EAST);
        this.add(jpSearch);

    }

    public IntegratedSearch(String str[]) {
        initComponent(str);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent e) {
        txtSearchForm.setText("");
        cbxChoose.setSelectedIndex(0);
    }

}
