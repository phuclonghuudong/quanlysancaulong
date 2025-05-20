package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author phucp
 */
public class JDBCUtil {

    public static Connection getConnection() {
        Connection result = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://localhost:3306/quanlysancaulong";
            String userName = "root";
            String password = "123456";

            result = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
//            System.out.println("check:" + e);
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
        }
    }
}
