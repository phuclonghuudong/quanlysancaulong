package GUI.Dialog;

import GUI.Component.ButtonCustome;
import GUI.Component.HeaderTitle;
import GUI.Component.MenuTaskbar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author phucp
 */
public class MyAccount extends JDialog implements ActionListener {

    CardLayout card;
    ButtonCustome save, cancel;
    MenuTaskbar menuTaskbar;
    HeaderTitle title;
    JPanel top, center, top_center, main_center, bottom;
    JRadioButton[] jbr;
    JPanel[] panel;

    public MyAccount(JFrame owner, MenuTaskbar menutaskbar, String title, boolean modal) {
        super(owner, title, modal);
        initComponent(menutaskbar);
        this.setLocationRelativeTo(null);
    }

    public void initComponent(MenuTaskbar menutaskbar) {
        this.menuTaskbar = menutaskbar;
        this.setSize(400, 300);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setLayout(new FlowLayout(0, 0, 0));
        title = new HeaderTitle("CHỈNH SỬA THÔNG TIN");
        top.add(title);
        this.add(top, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        }
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == jbr[i]) {
                if (i == 2) {
                    this.setSize(new Dimension(400, 500));
                    this.setLocationRelativeTo(null);
                } else {
                    this.setSize(400, 300);
                }
                main_center.removeAll();
                main_center.add(panel[i]);
                main_center.repaint();
                main_center.validate();
            }
        }
    }

}
