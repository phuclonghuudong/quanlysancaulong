package GUI.Component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import style.StyleColor;

/**
 *
 * @author phucp
 */
public class FormSelect extends JPanel {

    private JLabel lblTitle;
    public JComboBox cbb;
    StyleColor StyleColor = new StyleColor();

    public FormSelect(String title, String[] obj) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 100));

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbb = new JComboBox(obj);
        cbb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbb.setBackground(StyleColor.colorForm());
        cbb.setForeground(new Color(50, 50, 50));
        cbb.setBorder(BorderFactory.createEmptyBorder());
        cbb.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cbb.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(new Color(250, 241, 230));
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(StyleColor.colorForm());
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
        cbb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    label.setBackground(StyleColor.colorForm());  // Màu nền khi chọn
                    label.setForeground(Color.BLACK);                // Màu chữ khi chọn
                } else {
                    label.setBackground(Color.WHITE);               // Màu nền khi không chọn
                    label.setForeground(Color.BLACK);               // Màu chữ khi không chọn
                }
                return label;
            }
        });

        this.add(lblTitle);
        this.add(cbb);
    }

    public FormSelect(String[] obj) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 100));

        cbb = new JComboBox(obj);
        cbb.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbb.setBackground(StyleColor.colorForm());
        cbb.setForeground(new Color(50, 50, 50));
        cbb.setBorder(BorderFactory.createEmptyBorder());
        cbb.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cbb.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(new Color(250, 241, 230));
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(StyleColor.colorForm());
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
        cbb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    label.setBackground(StyleColor.colorForm());  // Màu nền khi chọn
                    label.setForeground(Color.BLACK);                // Màu chữ khi chọn
                } else {
                    label.setBackground(Color.WHITE);               // Màu nền khi không chọn
                    label.setForeground(Color.BLACK);               // Màu chữ khi không chọn
                }
                return label;
            }
        });

        this.add(lblTitle);
        this.add(cbb);
    }

    public void setArr(String[] obj) {
        this.cbb.setModel(new DefaultComboBoxModel(obj));
        this.revalidate();
        this.repaint();

    }

    public String getValue() {
        return (String) cbb.getSelectedItem();
    }

    public Object getSelectedItem() {
        return cbb.getSelectedItem();
    }

    public int getSelectedIndex() {
        return cbb.getSelectedIndex();
    }

    public void setSelectedIndex(int i) {
        cbb.setSelectedIndex(i);
    }

    public void setSelectedItem(Object a) {
        cbb.setSelectedItem(a);
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JComboBox getCbb() {
        return cbb;
    }

    public void setCbb(JComboBox cbb) {
        this.cbb = cbb;
    }

    public void setDisable() {
        cbb.setEditable(false);
        cbb.setEnabled(false);
        cbb.setBackground(StyleColor.mainBackgroundColor());
        cbb.setForeground(Color.BLACK);
        cbb.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(StyleColor.mainBackgroundColor());
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(StyleColor.mainBackgroundColor());
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });

    }
}
