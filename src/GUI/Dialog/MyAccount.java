package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustome;
import GUI.Component.FormInput;
import GUI.Component.HeaderTitle;
import GUI.Component.MenuTaskbar;
import GUI.Component.NumericDocumentFilter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

/**
 *
 * @author phucp
 */
public final class MyAccount extends JDialog implements ActionListener {

    CardLayout card;
    ButtonCustome save, cancel;
    MenuTaskbar menuTaskbar;
    HeaderTitle title;
    JPanel top, center, top_center, main_center, bottom;
    JRadioButton[] jbr;
    JPanel[] panel;
    FormInput current_pwd, phone, email, new_pwd, confirm;

    NhanVienDTO nvDTO;
    NhanVienBUS nvBUS;

    public MyAccount(JFrame owner, MenuTaskbar menutaskbar, String title, boolean modal) {
        super(owner, title, modal);
        initComponent(menutaskbar);
        this.setLocationRelativeTo(null);
    }

    public void initComponent(MenuTaskbar menutaskbar) {
        nvBUS = new NhanVienBUS();
        this.menuTaskbar = menutaskbar;
        this.setSize(400, 300);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setResizable(false);

        nvDTO = menutaskbar.nhanVienDTO;

        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setLayout(new FlowLayout(0, 0, 0));
        title = new HeaderTitle("CHỈNH SỬA THÔNG TIN");
        top.add(title);
        this.add(top, BorderLayout.NORTH);

        top_center = new JPanel(new FlowLayout(1, 40, 0));
        top_center.setBorder(new EmptyBorder(20, 0, 0, 0));
        top_center.setBackground(Color.WHITE);
        main_center = new JPanel();
        main_center.setBorder(new EmptyBorder(0, 20, 0, 20));
        main_center.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup();
        String opt[] = {"Số điện thoại", "Email", "Mật khẩu"};
        jbr = new JRadioButton[3];
        for (int i = 0; i < jbr.length; i++) {
            jbr[i] = new JRadioButton();
            jbr[i].addActionListener(this);
            jbr[i].setText(opt[i]);
            top_center.add(jbr[i]);
            bg.add(jbr[i]);
        }
        jbr[0].setSelected(true);

        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(top_center, BorderLayout.NORTH);
        center.add(main_center, BorderLayout.CENTER);

        panel = new JPanel[3];
        panel[0] = new JPanel(new GridLayout(1, 1));
        panel[0].setBackground(Color.WHITE);
        panel[0].setPreferredSize(new Dimension(400, 100));
        panel[0].setBorder(new EmptyBorder(10, 10, 10, 10));
        phone = new FormInput("Số điện thoại");
        PlainDocument phonex = (PlainDocument) phone.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        phone.setText(nvDTO.getSodienthoai());
        panel[0].add(phone);

        panel[1] = new JPanel(new GridLayout(1, 1));
        panel[1].setBackground(Color.WHITE);
        panel[1].setBorder(new EmptyBorder(10, 10, 10, 10));

        panel[1].setPreferredSize(new Dimension(400, 100));
        email = new FormInput("Email");
        email.setText(nvDTO.getEmail());
        panel[1].add(email);
        main_center.add(panel[0]);

        panel[2] = new JPanel(new GridLayout(3, 1));
        panel[2].setBackground(Color.WHITE);
        panel[2].setPreferredSize(new Dimension(400, 300));
        panel[2].setBorder(new EmptyBorder(10, 10, 10, 10));
        current_pwd = new FormInput("Mật khẩu hiện tại", "password");
        new_pwd = new FormInput("Mật khẩu mới", "password");
        confirm = new FormInput("Nhập lại mật khẩu mới", "password");
        panel[2].add(current_pwd);
        panel[2].add(new_pwd);
        panel[2].add(confirm);

        this.add(center, BorderLayout.CENTER);

        bottom = new JPanel(new FlowLayout(1, 20, 10));
        bottom.setBackground(Color.WHITE);

        cancel = new ButtonCustome("Hủy", "danger", 15);
        cancel.addActionListener(this);
        bottom.add(cancel);
        save = new ButtonCustome("Lưu", "success", 15);
        save.addActionListener(this);
        bottom.add(save);
        this.add(bottom, BorderLayout.SOUTH);
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

        if (jbr[0].isSelected()) {
            if (e.getSource() == save) {
                String result = nvBUS.updatePhone(nvDTO, phone.getText());
                if (result.equals("success")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (jbr[1].isSelected()) {
            if (e.getSource() == save) {
                String result = nvBUS.updateEmail(nvDTO, email.getText());
                if (result.equals("success")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (jbr[2].isSelected()) {
            if (e.getSource() == save) {
                String result = nvBUS.updatePassword(nvDTO, current_pwd.getPass(), new_pwd.getPass(), confirm.getPass());
                if (result.equals("success")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    current_pwd.setPass("");
                    new_pwd.setPass("");
                    confirm.setPass("");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        menuTaskbar.resetChange();
    }

}
