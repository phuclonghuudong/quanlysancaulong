package GUI.Component;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import style.StyleColor;

/**
 *
 * @author phucp
 */
public class FormDate extends JPanel {

    StyleColor StyleColor = new StyleColor();
    private JLabel lblTitle;
    private JDateChooser dateChooser;

    public FormDate(String title) {
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(5, 10, 5, 10));

        lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(150, 20));
        dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateChooser.setBorder(new EmptyBorder(4, 10, 4, 10));
        dateChooser.setBackground(StyleColor.colorForm());

        for (Component comp : dateChooser.getComponents()) {
            if (comp instanceof JButton) {
                JButton calendarButton = (JButton) comp;
                calendarButton.setBorder(new EmptyBorder(10, 10, 10, 10));
                calendarButton.setBackground(StyleColor.colorForm());
                calendarButton.setPreferredSize(new Dimension(40, 10));
                break;
            }
        }

        JTextField editor = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        editor.setBorder(BorderFactory.createEmptyBorder());
        editor.setBackground(StyleColor.colorForm());

        add(lblTitle, BorderLayout.NORTH);
        add(dateChooser, BorderLayout.CENTER);
    }

    public Date getSelectedDate() {
        return dateChooser.getDate();
    }

    public void setSelectedDate(Date date) {
        dateChooser.setDate(date);
    }

    public void clearDate() {
        dateChooser.setDate(null);
    }

    public String getFormattedDate() {
        Date date = getSelectedDate();
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public Date getDate() {
        return dateChooser.getDate();
    }

    public void setEnabled(boolean enabled) {
        dateChooser.setEnabled(enabled);
    }

    public void setDisabled() {
//        dateChooser.setEnabled(false);
        dateChooser.setBackground(StyleColor.mainBackgroundColor());

        for (Component comp : dateChooser.getComponents()) {
            if (comp instanceof JButton calendarButton) {
                calendarButton.setEnabled(false);
                calendarButton.setBackground(StyleColor.mainBackgroundColor());
                break;
            }
        }
        JTextField editor = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        editor.setEditable(false);
        editor.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        editor.setBackground(StyleColor.mainBackgroundColor());
        editor.setForeground(Color.BLACK);
    }
}
