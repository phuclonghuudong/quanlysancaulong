package GUI.Component;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author phucp
 */
public final class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();

    public MainFunction(String chucnang, String[] listBtn) {
        initData();
        initComponent(chucnang, listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.png", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.png", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.png", "update"));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "close.png", "delete"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.png", "view"));
        btn.put("import", new ButtonToolBar("NHẬP EXCEL", "import-excel.png", "create"));
        btn.put("export", new ButtonToolBar("XUẤT EXCEL", "export-excel.png", "view"));

    }

    private void initComponent(String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setFloatable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setRollover(true);
        for (String btnn : listBtn) {
            this.add(btn.get(btnn));
//            btn.get(btnn).setEnabled(false);
        }
    }
}
