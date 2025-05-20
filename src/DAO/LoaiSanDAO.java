package DAO;

import DTO.LoaiSanDTO;
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
public class LoaiSanDAO implements DAOinterface<LoaiSanDTO> {

    public static LoaiSanDAO getInstance() {
        return new LoaiSanDAO();
    }

    @Override
    public int insert(LoaiSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `loaisan`(`tenloaisan`, `ghichu`,`trangthai`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaisan());
            pst.setString(2, t.getGhichu());
            pst.setInt(3, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(LoaiSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `loaisan` SET`tenloaisan`=?,`ghichu`=?, `trangthai`=? WHERE `maloaisan`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaisan());
            pst.setString(2, t.getGhichu());
            pst.setInt(3, t.getTrangthai());
            pst.setInt(4, t.getMaloaisan());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update loaisan set `trangthai` = -1 WHERE maloaisan = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<LoaiSanDTO> selectAll() {
        ArrayList<LoaiSanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisan WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("maloaisan");
                String tenloaihang = rs.getString("tenloaisan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                LoaiSanDTO nv = new LoaiSanDTO(ma, tenloaihang, ghichu, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public LoaiSanDTO selectById(String t) {
        LoaiSanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisan WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("maloaisan");
                String tenloaihang = rs.getString("tenloaisan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                result = new LoaiSanDTO(ma, tenloaihang, ghichu, trangthai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'loaisan'";
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
            Logger.getLogger(LoaiSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
