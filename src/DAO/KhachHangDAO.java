package DAO;

import DTO.KhachHangDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author phucp
 */
public class KhachHangDAO implements DAOinterface<KhachHangDTO> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHangDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhanvien`(`hoten`, `sodienthoai`,`diachi` ,`trangthai`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setString(2, t.getSodienthoai());
            pst.setString(3, t.getDiachi());
            pst.setInt(4, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhachHangDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `khachhang` SET`hoten`=?,`sodienthoai`=?,`diachi`=?, `trangthai`=? WHERE `makhachhang`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setString(2, t.getSodienthoai());
            pst.setString(3, t.getDiachi());
            pst.setInt(4, t.getTrangthai());
            pst.setInt(5, t.getMakhachhang());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update khachhang set `trangthai` = -1 WHERE makhachhang = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("makhachhang");
                String hoten = rs.getString("hoten");
                String sodienthoai = rs.getString("sodienthoai");
                String diachi = rs.getString("diachi");
                int trangthai = rs.getInt("trangthai");
                KhachHangDTO nv = new KhachHangDTO(manv, hoten, sodienthoai, diachi, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public KhachHangDTO selectById(String t) {
        KhachHangDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("makhachhang");
                String hoten = rs.getString("hoten");
                String sodienthoai = rs.getString("sodienthoai");
                String diachi = rs.getString("diachi");
                int trangthai = rs.getInt("trangthai");
                result = new KhachHangDTO(manv, hoten, sodienthoai, diachi, trangthai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'khachhang'";
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
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean selectByPhoneExist(String p) {
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE sodienthoai=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p);
            ResultSet rs = (ResultSet) pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }
}
