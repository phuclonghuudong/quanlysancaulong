package GUI.Component;

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
public class ButtonToolBar extends JButton {

    String permisson;
    private ImageCustome imgCustom = new ImageCustome();

    public ButtonToolBar(String text, String icon, String permisson) {
        this.permisson = permisson;
        this.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        this.setForeground(new Color(1, 88, 155));
        this.setIcon(imgCustom.loadAndResizeImage("./src/icon/" + icon, 40, 40));
        this.setText(text);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.putClientProperty("JButton.buttonType", "toolBarButton");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.BLACK);
                setBackground(new Color(204, 214, 219));
                setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(1, 88, 155));
                setBackground(Color.WHITE);
                setOpaque(true);
            }
        });
    }

    public String getPermisson() {
        return this.permisson;
    }
}
