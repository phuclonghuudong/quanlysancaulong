package config;

/**
 *
 * @author phucp
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseInitializer {

    public static void createTables() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "";

            sql += "CREATE TABLE IF NOT EXISTS SAN ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Ten_San VARCHAR(255),"
                    + "Loai_San VARCHAR(255),"
                    + "Mo_Ta TEXT,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS KHACH_HANG ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Ten_Khach_Hang VARCHAR(255),"
                    + "Email VARCHAR(255),"
                    + "Ngay_Sinh DATE,"
                    + "So_Dien_Thoai VARCHAR(20),"
                    + "Mat_Khau VARCHAR(255),"
                    + "Gioi_Tinh VARCHAR(10),"
                    + "Vai_Tro VARCHAR(50),"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS LOAI_SAN_PHAM ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Ten_Loai VARCHAR(255),"
                    + "Mo_Ta TEXT,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS LOAI_SAN ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Ten_Loai VARCHAR(255),"
                    + "Mo_Ta TEXT,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS DICH_VU ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Ten_Dich_Vu VARCHAR(255),"
                    + "Don_Vi_Tinh VARCHAR(50),"
                    + "Don_Gia DECIMAL(10,2),"
                    + "Mo_Ta TEXT,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS CT_DAT_SAN_DICH_VU ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Dat_San_ID INT,"
                    + "Dich_Vu_ID INT,"
                    + "So_Luong INT,"
                    + "Tong_Tien DECIMAL(10,2),"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS DAT_SAN ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Khach_Hang_ID INT,"
                    + "San_ID INT,"
                    + "Thoi_Gian_Bat_Dau DATETIME,"
                    + "Thoi_Gian_Ket_Thuc DATETIME,"
                    + "Tong_Tien DECIMAL(10,2),"
                    + "Da_Thanh_Toan BOOLEAN,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS CT_DAT_SAN_SAN_PHAM ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Dat_San_ID INT,"
                    + "San_Pham_ID INT,"
                    + "So_Luong INT,"
                    + "Tong_Tien DECIMAL(10,2),"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS SAN_PHAM ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Loai_San_Pham_ID INT,"
                    + "Ten_San_Pham VARCHAR(255),"
                    + "Gia_Ban DECIMAL(10,2),"
                    + "So_Luong INT,"
                    + "Mo_Ta TEXT,"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS KHUNG_GIA ("
                    + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "Gio_Bat_Dau TIME,"
                    + "Gio_Ket_Thuc TIME,"
                    + "Thu INT,"
                    + "Loai_Ngay VARCHAR(50),"
                    + "Don_Gia DECIMAL(10,2),"
                    + "Status INT,"
                    + "Ngay_Tao DATETIME,"
                    + "Ngay_Cap_Nhat DATETIME"
                    + ");";

            // Chạy nhiều lệnh SQL (chia nhỏ hoặc dùng batch nếu cần)
            for (String singleSql : sql.split(";")) {
                if (!singleSql.trim().isEmpty()) {
                    stmt.executeUpdate(singleSql + ";");
                }
            }

            JOptionPane.showMessageDialog(null, "Tạo tất cả các bảng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                JDBCUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
