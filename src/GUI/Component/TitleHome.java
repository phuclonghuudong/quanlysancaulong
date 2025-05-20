package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import style.StyleColor;

/**
 *
 * @author phucp
 */
public class TitleHome extends JPanel {

    private JLabel lblTitle, lblTitleA, lblTitleB;
    private StyleColor colorStyle = new StyleColor();

    public void initComponent(String title, int size) {
        this.setLayout(new BorderLayout());
        this.setBackground(null);
        this.setPreferredSize(new Dimension(400, 10));

        lblTitle = new JLabel();
        lblTitle.setText(title);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, size));
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        this.add(lblTitle, BorderLayout.CENTER);
    }

    public void initComponent(String titleA, String titleB, int sizeA, int sizeB) {
        this.setLayout(new BorderLayout());
        this.setBackground(null);
        this.setPreferredSize(new Dimension(200, 20));

        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 5, 2));
        pnlMain.setBackground(null);
        pnlMain.setPreferredSize(new Dimension(200, 20));
        pnlMain.setBorder(new EmptyBorder(10, 250, 10, 300));

        lblTitleA = new JLabel();
        lblTitleA.setText(titleA);
        lblTitleA.setFont(new Font("Tahoma", Font.BOLD, sizeA));
        lblTitleA.setForeground(Color.DARK_GRAY);
        lblTitleA.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitleA.setVerticalAlignment(SwingConstants.CENTER);

        lblTitleB = new JLabel();
        lblTitleB.setText(titleB);
        lblTitleB.setFont(new Font("Tahoma", Font.BOLD, sizeB));
        lblTitleB.setForeground(Color.DARK_GRAY);
        lblTitleB.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTitleB.setVerticalAlignment(SwingConstants.CENTER);

        pnlMain.add(lblTitleA, BorderLayout.WEST);
        pnlMain.add(lblTitleB, BorderLayout.EAST);
        this.add(pnlMain, BorderLayout.CENTER);
    }

    public TitleHome(String title, int size) {
        initComponent(title, size);
    }

    public TitleHome(String titleA, String titleB, int sizeA, int sizeB) {
        initComponent(titleA, titleB, sizeA, sizeB);
    }
}
