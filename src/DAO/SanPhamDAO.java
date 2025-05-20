package DAO;

import DTO.SanPhamDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phucp
 */
public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`loaisanpham`,`tensanpham`,`giaban`,`soluong`,`donvi`,`ghichu`,`trangthai`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getLoaisanpham());
            pst.setString(2, t.getTensanpham());
            pst.setDouble(3, t.getGiaban());
            pst.setInt(4, t.getSoluong());
            pst.setString(5, t.getDonvi());
            pst.setString(6, t.getGhichu());
            pst.setInt(7, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `san` SET`loaisanpham`=?, `tensanpham`=?, `soluong`=?, `donvi`=?, `giaban`=?, `ghichu`=?, `trangthai`=? WHERE `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getLoaisanpham());
            pst.setString(2, t.getTensanpham());
            pst.setDouble(3, t.getGiaban());
            pst.setInt(4, t.getSoluong());
            pst.setString(5, t.getDonvi());
            pst.setString(6, t.getGhichu());
            pst.setInt(7, t.getTrangthai());
            pst.setInt(8, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update sanpham set `trangthai` = -1 WHERE masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("masanpham");
                int loaisanpham = rs.getInt("loaisanpham");
                String tensanpham = rs.getString("tensanpham");
                Double giaban = rs.getDouble("giaban");
                int soluong = rs.getInt("soluong");
                String dongia = rs.getString("dongia");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                SanPhamDTO nv = new SanPhamDTO(ma, loaisanpham, tensanpham, giaban, soluong, dongia, ghichu, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM san WHERE masan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("masanpham");
                int loaisanpham = rs.getInt("loaisanpham");
                String tensanpham = rs.getString("tensanpham");
                Double giaban = rs.getDouble("giaban");
                int soluong = rs.getInt("soluong");
                String dongia = rs.getString("dongia");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                result = new SanPhamDTO(ma, loaisanpham, tensanpham, giaban, soluong, dongia, ghichu, trangthai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'sanpham'";
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
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
