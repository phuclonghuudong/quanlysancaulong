package style;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

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
    }
}
