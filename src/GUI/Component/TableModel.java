package GUI.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author phucp
 * @param <T>
 */
public class TableModel<T> extends AbstractTableModel {

    private ArrayList<T> data;
    private Vector<String> columnNames;
    private Vector<String> methodNames;

    public TableModel(List<T> data, String[] columnNames, String[] methodNames) {
        this.data = new ArrayList<>(data);
        this.columnNames = new Vector<>(List.of(columnNames));
        this.methodNames = new Vector<>(List.of(methodNames));

        if (this.columnNames.size() != this.methodNames.size()) {
            throw new IllegalArgumentException("Số lượng tên cột và tên phương thức không khớp.");
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T rowData = data.get(rowIndex);
        String methodName = methodNames.get(columnIndex);

        try {
            Method method = rowData.getClass().getMethod(methodName);
            Object value = method.invoke(rowData);

            // Xử lý riêng cho getGioiTinh()
            if (methodName.equals("isGioitinh") && value instanceof Boolean) {
                return ((Boolean) value) ? "Nữ" : "Nam";
            }
//            if (methodName.equals("getTrangthai") && value instanceof Integer) {
//                return ((Integer) value) == 1 ? "Hoạt động"
//                        : ((Integer) value) == 2 ? "Bảo trì" : "Dừng";
//            }
            return value;
        } catch (NoSuchMethodException | java.lang.reflect.InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    public void setData(ArrayList<T> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    public ArrayList<T> getData() {
        return this.data;
    }
}
