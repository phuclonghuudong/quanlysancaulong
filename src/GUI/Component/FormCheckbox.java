package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import style.StyleColor;

/**
 *
 * @author phucp
 */
public class FormCheckbox extends JPanel {

    StyleColor StyleColor = new StyleColor();
    private JLabel lblTitle;
    private ArrayList<JCheckBox> checkboxes;
    JPanel checkboxPanel;

    public FormCheckbox(String title, String[] options) {
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));

        int count = options.length;
        GridLayout grid;
        if (count == 3) {
            grid = new GridLayout(1, 3, 0, 0);
        } else if (count > 3) {
            grid = new GridLayout(2, count / 2, 0, 0);
        } else {
            grid = new GridLayout(1, 2, 0, 0);
        }

        checkboxPanel = new JPanel(grid);
        checkboxPanel.setBackground(StyleColor.colorForm());
        checkboxPanel.setBorder(new EmptyBorder(2, 10, 2, 10));

        checkboxes = new ArrayList<>();

        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setOpaque(true);
            checkBox.setBorder(new EmptyBorder(2, 10, 2, 10));
            checkBox.setBackground(StyleColor.colorForm());
            checkBox.setForeground(Color.BLACK);
            checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
//            checkBox.setMargin(new Insets(0, 10, 0, 10));
            checkBox.setFocusPainted(false);
            checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
            checkBox.setIcon(new LargeCheckBoxIcon());
            checkBox.setOpaque(true);

            checkBox.addItemListener(e -> {
                if (checkBox.isSelected()) {
                    // Bỏ chọn tất cả checkbox khác
                    for (JCheckBox cb : checkboxes) {
                        if (cb != checkBox) {
                            cb.setSelected(false);
                            cb.setOpaque(true);
                            cb.setBackground(StyleColor.colorForm());
                            cb.setPreferredSize(new Dimension(0, 20));
                        }
                    }
                    // Cập nhật màu checkbox hiện tại
//                    checkBox.setBackground(new Color(200, 230, 255));
                } else {
                    checkBox.setBackground(StyleColor.colorForm());
                }
            });

            checkboxes.add(checkBox);
            checkboxPanel.add(checkBox);
        }

        add(lblTitle, BorderLayout.NORTH);
        add(checkboxPanel, BorderLayout.CENTER);
    }

    public ArrayList<String> getSelectedValues() {
        ArrayList<String> selected = new ArrayList<>();
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                selected.add(checkbox.getText());
            }
        }
        return selected;
    }

    public void setSelectedValues(String[] values) {
        for (JCheckBox checkbox : checkboxes) {
            for (String value : values) {
                if (checkbox.getText().equalsIgnoreCase(value)) {
                    checkbox.setSelected(true);
                }
            }
        }
    }

    public void setDisabled() {
        checkboxPanel.setBackground(StyleColor.mainBackgroundColor());
        checkboxPanel.setCursor(new Cursor(Cursor.WAIT_CURSOR));

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setEnabled(false);
            checkbox.setBackground(StyleColor.mainBackgroundColor());
            checkbox.setForeground(Color.BLACK);

            checkbox.addItemListener(e -> {
                if (checkbox.isSelected()) {
                    for (JCheckBox cb : checkboxes) {
                        if (cb != checkbox) {
                            cb.setBackground(StyleColor.mainBackgroundColor());
                        }
                    }
                } else {
                    checkbox.setBackground(StyleColor.mainBackgroundColor());
                }
            });
        }

    }

    public void setEnabledAll(boolean enabled) {
        for (JCheckBox checkbox : checkboxes) {
            checkbox.setEnabled(enabled);
        }
    }

    private static class LargeCheckBoxIcon implements Icon {

        private final int size = 20;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            JCheckBox cb = (JCheckBox) c;
            g.setColor(Color.BLACK);
            g.drawRect(x, y, size - 1, size - 1);
            if (cb.isSelected()) {
                g.setColor(Color.BLACK);
                g.fillRect(x + 4, y + 4, size - 8, size - 8);
            }
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }
}
