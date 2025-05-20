package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author phucp
 */
public final class HeaderTitle extends JPanel {

    private JLabel lblTitle;

    public void initComponent(String title) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(22, 122, 198));
        this.setPreferredSize(new Dimension(400, 60));

        lblTitle = new JLabel();
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setText(title);
        lblTitle.setPreferredSize(new Dimension(400, 50));
        this.add(lblTitle, BorderLayout.CENTER);
    }

    public HeaderTitle(String title) {
        initComponent(title);
    }
}
