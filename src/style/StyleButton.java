package style;

import java.awt.Color;
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
}
