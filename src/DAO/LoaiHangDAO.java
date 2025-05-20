package DAO;

import DTO.LoaiHangDTO;
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
public class LoaiHangDAO implements DAOinterface<LoaiHangDTO> {

    public static LoaiHangDAO getInstance() {
        return new LoaiHangDAO();
    }

    @Override
    public int insert(LoaiHangDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `loaihang`(`tenloaihang`, `ghichu`,`trangthai`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaihang());
            pst.setString(2, t.getGhichu());
            pst.setInt(3, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(LoaiHangDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `loaihang` SET`tenloaihang`=?,`ghichu`=?, `trangthai`=? WHERE `maloaihang`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaihang());
            pst.setString(2, t.getGhichu());
            pst.setInt(3, t.getTrangthai());
            pst.setInt(4, t.getMahang());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update loaihang set `trangthai` = -1 WHERE maloaihang = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<LoaiHangDTO> selectAll() {
        ArrayList<LoaiHangDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaihang WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("maloaihang");
                String tenloaihang = rs.getString("tenloaihang");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                LoaiHangDTO nv = new LoaiHangDTO(ma, tenloaihang, ghichu, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public LoaiHangDTO selectById(String t) {
        LoaiHangDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaihang WHERE maloaihang=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("maloaihang");
                String tenloaihang = rs.getString("tenloaihang");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                result = new LoaiHangDTO(ma, tenloaihang, ghichu, trangthai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'loaihang'";
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
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
