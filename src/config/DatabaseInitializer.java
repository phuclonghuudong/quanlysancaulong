package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author phucp
 */
public class DatabaseInitializer {

    public static void createTables() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "";

            sql += "CREATE TABLE IF NOT EXISTS khachhang ("
                    + "makhachhang INT AUTO_INCREMENT PRIMARY KEY,"
                    + "hoten VARCHAR(255),"
                    + "sodienthoai VARCHAR(20),"
                    + "diachi VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaythamgia TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS nhanvien ("
                    + "manhanvien INT AUTO_INCREMENT PRIMARY KEY,"
                    + "hoten VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "ngaysinh DATE,"
                    + "sodienthoai VARCHAR(20),"
                    + "matkhau VARCHAR(255),"
                    + "gioitinh BOOLEAN,"
                    + "vaitro VARCHAR(50),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS loaihang ("
                    + "maloaihang INT PRIMARY KEY AUTO_INCREMENT,"
                    + "tenloaihang VARCHAR(255),"
                    + "ghichu VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS loaisan ("
                    + "maloaisan INT PRIMARY KEY AUTO_INCREMENT,"
                    + "tenloaisan VARCHAR(255),"
                    + "ghichu VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS san ("
                    + "masan INT PRIMARY KEY AUTO_INCREMENT,"
                    + "loaisan int,"
                    + "tensan VARCHAR(255),"
                    + "giasan DECIMAL(10,2),"
                    + "ghichu TEXT,"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (loaisan) REFERENCES loaisan(maloaisan)"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS sanpham ("
                    + "masanpham INT PRIMARY KEY AUTO_INCREMENT,"
                    + "loaisanpham INT,"
                    + "tensanpham VARCHAR(255),"
                    + "giaban DECIMAL(10,2),"
                    + "soluong INT,"
                    + "donvi VARCHAR(255),"
                    + "ghichu VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (loaisanpham) REFERENCES loaihang(maloaihang)"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS khunggia ("
                    + "makhunggia INT PRIMARY KEY AUTO_INCREMENT,"
                    + "checkin TIME,"
                    + "checkout TIME,"
                    + "thu INT,"
                    + "loaingay VARCHAR(50),"
                    + "dongia DECIMAL(10,2),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS datsan ("
                    + "madatsan INT PRIMARY KEY AUTO_INCREMENT,"
                    + "manhanvien INT,"
                    + "masan INT,"
                    + "makhachhang INT,"
                    + "checkin DATETIME,"
                    + "checkout DATETIME,"
                    + "tongtien DECIMAL(10,2),"
                    + "thanhtoan BOOLEAN,"
                    + "ghichu VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (manhanvien) REFERENCES nhanvien(manhanvien),"
                    + "FOREIGN KEY (masan) REFERENCES san(masan),"
                    + "FOREIGN KEY (makhachhang) REFERENCES khachhang(makhachhang)"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS chitietdatsan ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "madatsan INT,"
                    + "masanpham INT,"
                    + "soluong INT,"
                    + "giatien DECIMAL(10,2),"
                    + "ghichu VARCHAR(255),"
                    + "trangthai INT,"
                    + "ngaytao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "ngaycapnhat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (madatsan) REFERENCES datsan(madatsan),"
                    + "FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)"
                    + ");";

            sql += "CREATE TABLE IF NOT EXISTS chitietgiasan ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "masan INT,"
                    + "makhunggia INT,"
                    + "ghichu VARCHAR(255),"
                    + "FOREIGN KEY (masan) REFERENCES san(masan),"
                    + "FOREIGN KEY (makhunggia) REFERENCES khunggia(makhunggia)"
                    + ");";

            // Chạy nhiều lệnh SQL (chia nhỏ hoặc dùng batch nếu cần)
            for (String singleSql : sql.split(";")) {
                if (!singleSql.trim().isEmpty()) {
                    stmt.executeUpdate(singleSql + ";");
                }
            }

            JOptionPane.showMessageDialog(null, "Tạo tất cả các bảng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                JDBCUtil.closeConnection(conn);
            } catch (SQLException e) {
            }
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
