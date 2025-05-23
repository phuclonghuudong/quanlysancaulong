package style;

import GUI.Component.CustomScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author phucp
 */
public class StyleButton {

    public void ButtonCreate(JButton btn) {
        btn.setForeground(new Color(250, 241, 230));
        btn.setBackground(new Color(50, 142, 110));

        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(Color.BLACK);
                btn.setBackground(new Color(103, 174, 110));
                btn.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(new Color(250, 241, 230));
                btn.setBackground(new Color(50, 142, 110));
                btn.setOpaque(true);
            }

        });
    }

    public void customizeButton(JButton btn, String type) {
        Color colorKey = switch (type) {
            case "success" ->
                new Color(19, 62, 135);
            case "danger" ->
                new Color(220, 53, 69);
            case "warning" ->
                new Color(255, 193, 7);
            case "excel" ->
                new Color(40, 167, 69);
            case "return" ->
                new Color(255, 133, 27);
            case "ok" ->
                new Color(33, 37, 41);
            default ->
                Color.WHITE;
        };
        btn.setBackground(colorKey);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(Color.BLACK);
                btn.setBackground(new Color(204, 214, 219));
                btn.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorKey);
                btn.setForeground(Color.WHITE);
                btn.setOpaque(true);
            }
        });
    }

    public void customizeCombobox(JComboBox btn) {
        btn.setBorder(new EmptyBorder(2, 10, 2, 10));
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setBorder(null);
                return arrowButton;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(Color.WHITE);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
        btn.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    label.setBackground(Color.WHITE);  // Màu nền khi chọn
                    label.setForeground(Color.BLACK);                // Màu chữ khi chọn
                } else {
                    label.setBackground(Color.WHITE);               // Màu nền khi không chọn
                    label.setForeground(Color.BLACK);               // Màu chữ khi không chọn
                }
                return label;
            }
        });
    }

    public void applyCustomScrollBar(JComboBox<?> comboBox) {
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                Object child = comboBox.getUI().getAccessibleChild(comboBox, 0);
                if (child instanceof BasicComboPopup popup) {
                    JScrollPane scrollPane = (JScrollPane) popup.getComponents()[0];
                    scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }
}
