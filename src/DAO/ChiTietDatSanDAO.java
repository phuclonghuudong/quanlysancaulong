package DAO;

import DTO.ChiTietDatSanDTO;
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
public class ChiTietDatSanDAO implements DAOinterface<ChiTietDatSanDTO> {

    public static ChiTietDatSanDAO getInstance() {
        return new ChiTietDatSanDAO();
    }

    @Override
    public int insert(ChiTietDatSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `chitietdatsan`(`id`,`madatsan`,`masanpham`,`soluong`,`giatien`,`ghichu`,`trangthai`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getID());
            pst.setInt(2, t.getMadatsan());
            pst.setInt(3, t.getMasanpham());
            pst.setInt(4, t.getSoluong());
            pst.setDouble(5, t.getGiatien());
            pst.setString(6, t.getGhichu());
            pst.setInt(7, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ChiTietDatSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `chitietdatsan` SET `soluong`=?,`giatien`=?,`ghichu`=?, `trangthai`=? WHERE `madatsan`=? AND `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);

            pst.setInt(1, t.getSoluong());
            pst.setDouble(2, t.getGiatien());
            pst.setString(3, t.getGhichu());
            pst.setInt(4, t.getTrangthai());
            pst.setInt(5, t.getMadatsan());
            pst.setInt(6, t.getMasanpham());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update datsan set `trangthai` = -1, WHERE id =?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietDatSanDTO> selectAll() {
        ArrayList<ChiTietDatSanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietdatsan";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("id");
                int maDS = rs.getInt("madatsan");
                int maSP = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                Double giatien = rs.getDouble("giatien");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                ChiTietDatSanDTO ds = new ChiTietDatSanDTO(ID, maDS, maSP, soluong, giatien, ghichu, trangthai);
                result.add(ds);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public ChiTietDatSanDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM chitietdatsan";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result + 1;
//          try {
//            Connection con = (Connection) JDBCUtil.getConnection();
//            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysancaulong' AND   TABLE_NAME   = 'datsan'";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            ResultSet rs2 = pst.executeQuery(sql);
//            if (!rs2.isBeforeFirst()) {
//                System.out.println("No data");
//            } else {
//                while (rs2.next()) {
//                    result = rs2.getInt("AUTO_INCREMENT");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
    }

    public ArrayList<ChiTietDatSanDTO> selectByMaSP_MaDS() {
        ArrayList<ChiTietDatSanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietdatsan";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("id");
                int maDS = rs.getInt("madatsan");
                int maSP = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                Double giatien = rs.getDouble("giatien");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                ChiTietDatSanDTO ds = new ChiTietDatSanDTO(ID, maDS, maSP, soluong, giatien, ghichu, trangthai);
                result.add(ds);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public int deleteSanPham(int maSP, int maDS) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update chitietdatsan set `trangthai` = -1, WHERE masanpham=? AND madatsan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maSP);
            pst.setInt(2, maDS);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
