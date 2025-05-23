package GUI.Component;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author phucp
 */
public class ComboBoxCustomScrollBar {

    public static void applyCustomScrollBar(JComboBox<?> comboBox) {
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                Object child = comboBox.getUI().getAccessibleChild(comboBox, 0);
                if (child instanceof BasicComboPopup popup) {
                    JScrollPane scrollPane = (JScrollPane) popup.getComponents()[0];
                    scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }
}
