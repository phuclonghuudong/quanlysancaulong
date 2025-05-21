package DAO;

import DTO.KhungGiaDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;

/**
 *
 * @author phucp
 */
public class KhungGiaDAO implements DAOinterface<KhungGiaDTO> {

    public static KhungGiaDAO getInstance() {
        return new KhungGiaDAO();
    }

    @Override
    public int insert(KhungGiaDTO t) {
        int result = 0;
        try {
            LocalTime checkin = t.getCheckin();
            LocalTime checkout = t.getCheckout();

            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `khunggia`(`checkin`,`checkout`,`thu`,`loaingay`,`dongia`,`trangthai`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setTime(1, Time.valueOf(checkin));
            pst.setTime(2, Time.valueOf(checkout));
            pst.setString(3, t.getThu());
            pst.setString(4, t.getLoaingay());
            pst.setDouble(5, t.getDongia());
            pst.setInt(6, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhungGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhungGiaDTO t) {
        int result = 0;
        try {
            LocalTime checkin = t.getCheckin();
            LocalTime checkout = t.getCheckout();

            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `khunggia` SET `checkin`=?,`checkout`=?,`thu`=?,`loaingay`=?,`dongia`=?,`trangthai`=? WHERE `makhunggia`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setTime(1, Time.valueOf(checkin));
            pst.setTime(2, Time.valueOf(checkout));
            pst.setString(3, t.getThu());
            pst.setString(4, t.getLoaingay());
            pst.setDouble(5, t.getDongia());
            pst.setInt(6, t.getTrangthai());
            pst.setInt(7, t.getMakhunggia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhungGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update khunggia set `trangthai` = -1 WHERE makhunggia = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhungGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhungGiaDTO> selectAll() {
        ArrayList<KhungGiaDTO> result = new ArrayList<>();
        try {

            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khunggia WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("makhunggia");
                LocalTime checkin = rs.getTime("checkin").toLocalTime();
                LocalTime checkout = rs.getTime("checkout").toLocalTime();
                String thu = rs.getString("thu");
                String loaingay = rs.getString("loaingay");
                Double dongia = rs.getDouble("dongia");
                int trangthai = rs.getInt("trangthai");
                KhungGiaDTO nv = new KhungGiaDTO(ma, checkin, checkout, thu, loaingay, dongia, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public KhungGiaDTO selectById(String t) {
        KhungGiaDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM san WHERE masan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("makhunggia");
                LocalTime checkin = rs.getTime("checkin").toLocalTime();
                LocalTime checkout = rs.getTime("checkout").toLocalTime();
                String thu = rs.getString("thu");
                String loaingay = rs.getString("loaingay");
                Double dongia = rs.getDouble("dongia");
                int trangthai = rs.getInt("trangthai");
                result = new KhungGiaDTO(ma, checkin, checkout, thu, loaingay, dongia, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'khunggia'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhungGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
