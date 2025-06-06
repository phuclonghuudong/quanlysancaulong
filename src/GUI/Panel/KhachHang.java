package GUI.Panel;

import BUS.DatSanBUS;
import BUS.KhachHangBUS;
import DTO.DatSanDTO;
import DTO.KhachHangDTO;
import GUI.Component.CustomScrollBar;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableModel;
import GUI.Component.itemTaskbar;
import GUI.Dialog.KhachHangDialog;
import GUI.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import style.StyleColor;
import style.StyleTable;
import utils.JTableExporter;

/**
 *
 * @author phucp
 */
public final class KhachHang extends JPanel implements ActionListener, ItemListener {

    StyleColor colorStyle = new StyleColor();
    StyleTable tableStyle = new StyleTable();
    PanelBorderRadius main, functionBar;
    MainFunction mainFunction;
    Main m;
    JTable tableContent;
    JPanel contentCenter, right;
    JScrollPane scrollPane;

    public KhachHangBUS khBUS = new KhachHangBUS();
    public DatSanBUS datsanBUS = new DatSanBUS();
    public ArrayList<KhachHangDTO> listDS = khBUS.getAll();
    private TableModel<KhachHangDTO> tableModel;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;

    String[] header = new String[]{"Mã KH", "Họ tên", "Số điện thoại", "Địa chỉ", "Trạng thái"};
    String[] headerSearch = new String[]{"Tất cả", "Mã khách hàng", "Họ tên", "Số điện thoại", "Trạng thái"};

    public KhachHang(Main main) {
        this.m = main;
        initComponent();

        tableContent.setDefaultEditor(Object.class, null);
        loadDataTable(listDS);
    }

    public void initComponent() {
        this.setSize(new Dimension(1030, 670));
        this.setBackground(colorStyle.mainBackgroundColor());
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] methodNames = {"getMakhachhang", "getHoten", "getSodienthoai", "getDiachi", "getTrangthai"};

        tableModel = new TableModel<>(listDS, header, methodNames);
        tableContent = new JTable(tableModel);
        tableStyle.customizeTable(tableContent);

        JScrollPane scrollTable = new JScrollPane(tableContent);
        this.add(scrollTable, BorderLayout.CENTER);
        scrollTable.setBorder(null);
        scrollTable.getViewport().setBorder(null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableContent.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableContent.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableContent.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableContent.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableContent.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tableContent.setAutoCreateRowSorter(true);

        tableContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableContent.getSelectedRow();
                if (index != -1) {
                    ArrayList<DatSanDTO> listDSDatSan = datsanBUS.getByKhachHang(listDS.get(index).getMakhachhang());
                    System.out.println("listDSDatSan 111" + listDSDatSan);
                    ListCustomersInDePot(listDSDatSan);
                }
            }
        });

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(new Color(240, 247, 250));
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "export"};
        mainFunction = new MainFunction("khachhang", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(headerSearch);
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listDS = khBUS.search(txt, type);
                loadDataTable(listDS);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            search.txtSearchForm.setText("");
            listDS = khBUS.getAll();
            loadDataTable(listDS);
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTable);

        right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setBackground(colorStyle.mainBackgroundColor());
        right.setLayout(new FlowLayout(0, 0, 10));
        right.setBorder(new EmptyBorder(0, 10, 0, 0));
        right.setPreferredSize(new Dimension(300, 800));
        JLabel tit = new JLabel("Danh sách sân khách hàng đã đặt:");
        tit.setFont(new Font("Tahoma", Font.BOLD, 14));
        right.add(tit);
        scrollPane = new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL));
        scrollPane.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL));
        contentCenter.add(scrollPane, BorderLayout.EAST);
    }

    @SuppressWarnings("empty-statement")
    public void loadDataTable(ArrayList<KhachHangDTO> result) {
        tblModel = new DefaultTableModel(header, 0);
        int size = result.size();

        for (int i = 0; i < size; i++) {
            String trangThai = result.get(i).getTrangthai() == 1 ? "Hoạt động" : "Dừng";
            tblModel.addRow(new Object[]{
                //                i + 1,
                result.get(i).getMakhachhang(),
                result.get(i).getHoten(),
                result.get(i).getSodienthoai(),
                result.get(i).getDiachi(),
                trangThai
            });
        };

        tableContent.setModel(tblModel);
        tableStyle.customizeTable(tableContent);
    }

    public int getRowSelected() {
        int index = tableContent.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu!");
        }
        return index;
    }

    public void ListCustomersInDePot(ArrayList<DatSanDTO> result) {
        right.removeAll();
        JLabel tit = new JLabel("Danh sách sân khách hàng đã đặt:");
        tit.setFont(new Font("Arial", Font.BOLD, 14));
        right.add(tit);
        itemTaskbar listItem[] = new itemTaskbar[result.size()];
        int i = 0;
        System.out.println("sp 215" + result);
        for (DatSanDTO sp : result) {
            listItem[i] = new itemTaskbar(sp, "success");
            right.add(listItem[i]);
            i++;
        }

        if (i == 0) {
            if (result.isEmpty()) {
                JLabel lblIcon = new JLabel("Khách hàng chưa đặt sân nào.");
                lblIcon.setFont(new Font("Arial", Font.BOLD, 13));
                right.add(lblIcon);
            }
        }
        right.repaint();
        right.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Thêm khách hàng", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Chỉnh sửa khách hàng", true, "update", listDS.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa khách hàng này?", "Xóa khách hàng",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    boolean deleted = khBUS.delete(listDS.get(index));
                    if (deleted) {
                        listDS = khBUS.getAll();
                        loadDataTable(listDS);
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Xem khách hàng", true, "view", listDS.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableContent);
            } catch (IOException ex) {
                Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listDS = khBUS.search(txt, type);
        loadDataTable(listDS);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
