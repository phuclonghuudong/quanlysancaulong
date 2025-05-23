package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import style.StyleColor;

/**
 *
 * @author phucp
 */
public class FormInput extends JPanel {

    private JLabel lblTitle;
    private JTextField txtForm;
    private JPasswordField txtPass;
    StyleColor StyleColor = new StyleColor();

    public FormInput(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(5, 10, 5, 10));

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));

        txtForm = new JTextField();
        txtForm.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtForm.setBackground(StyleColor.colorForm());
        txtForm.setOpaque(true);
        txtForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(lblTitle);
        this.add(txtForm);
    }

    public FormInput(String title, String check, String check1) {
        this.setLayout(new GridLayout(2, 1));
        this.setBorder(new EmptyBorder(0, 10, 2, 10));
        this.setBackground(Color.white);

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));

        txtForm = new JTextField();
//        txtForm.setBorder(new EmptyBorder(2, 10, 2, 10));
        txtForm.setBackground(StyleColor.colorForm());
        txtForm.setOpaque(true);
        txtForm.setSize(50, 37);
        txtForm.setFont(new Font("Arial", Font.PLAIN, 13));
        this.add(lblTitle);
        this.add(txtForm);
    }

    public FormInput(String title, String style) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(lblTitle);
        if (style.equals("password")) {
            txtPass = new JPasswordField();
            txtPass.setBorder(new EmptyBorder(2, 10, 2, 10));
            txtPass.setBackground(StyleColor.colorForm());
            this.add(txtPass);
        }
    }

    public FormInput() {
    }

    public void setTitle(String title) {
        this.lblTitle.setText(title);
    }

    public String getPass() {
        return txtPass.getText();
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JTextField getTxtForm() {
        return txtForm;
    }

    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public void setPass(String s) {
        txtPass.setText(s);
    }

    public String getText() {
        return txtForm.getText();
    }

    public void setText(String content) {
        txtForm.setText(content);
    }

    public void setDisablePass() {
        txtPass.setEnabled(false);
    }

    public void setDisable() {
        txtForm.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        txtForm.setEditable(false);
        txtForm.setBackground(StyleColor.mainBackgroundColor());
        txtForm.setForeground(Color.BLACK);
    }

    public void setEditable(boolean value) {
        txtForm.setEditable(value);
    }

    public String getDocument() {
        return txtForm.getText();
    }
}
