package DAO;

import DTO.SanDTO;
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
public class SanDAO implements DAOinterface<SanDTO> {

    public static SanDAO getInstance() {
        return new SanDAO();
    }

    @Override
    public int insert(SanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `san`(`masan`,`loaisan`,`tensan`,`giasan`,`ghichu`,`trangthai`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasan());
            pst.setInt(2, t.getLoaisan());
            pst.setString(3, t.getTensan());
            pst.setDouble(4, t.getGiasan());
            pst.setString(5, t.getGhichu());
            pst.setInt(6, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `san` SET`loaisan`=?, `tensan`=?, `giasan`=?, `ghichu`=?, `trangthai`=? WHERE `masan`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getLoaisan());
            pst.setString(2, t.getTensan());
            pst.setDouble(3, t.getGiasan());
            pst.setString(4, t.getGhichu());
            pst.setInt(5, t.getTrangthai());
            pst.setInt(6, t.getMasan());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update san set `trangthai` = -1 WHERE masan = ?";
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
    public ArrayList<SanDTO> selectAll() {
        ArrayList<SanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM san";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("masan");
                int loaisan = rs.getInt("loaisan");
                String tensan = rs.getString("tensan");
                Double giasan = rs.getDouble("giasan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                SanDTO nv = new SanDTO(ma, loaisan, tensan, giasan, ghichu, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public SanDTO selectById(String t) {
        SanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM san WHERE masan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("masan");
                int loaisan = rs.getInt("loaisan");
                String tensan = rs.getString("tensan");
                Double giasan = rs.getDouble("giasan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                result = new SanDTO(ma, loaisan, tensan, giasan, ghichu, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public ArrayList<SanDTO> selectSanJoinLoaiSan() {
        ArrayList<SanDTO> result = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT s.masan as masan, s.tensan as tensan, s.giasan AS giasan, s.ghichu as ghichu, s.trangthai as trangthai, ls.tenloaisan as tenloaisan, s.loaisan as loaisan"
                    + " FROM san AS s JOIN loaisan AS ls on ls.maloaisan=s.loaisan";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("masan");
                String tenSan = rs.getString("tensan");
                int loaiSan = rs.getInt("loaisan");
                double giaSan = rs.getDouble("giasan");
                String moTa = rs.getString("ghichu");
                int status = rs.getInt("trangthai");
                String tenLoai = rs.getString("tenloaisan");

                SanDTO san = new SanDTO(ID, loaiSan, tenSan, giaSan, moTa, status);
                san.setTenloaisan(tenLoai);

                result.add(san);
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM san";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result + 1;
//        int result = -1;
//        try {
//            Connection con = (Connection) JDBCUtil.getConnection();
//            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysancaulong' AND   TABLE_NAME   = 'san'";
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

    public boolean isNameUnique(String p, int ID) {
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM san WHERE tensan=? AND masan<>?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p);
            pst.setInt(2, ID);
            ResultSet rs = (ResultSet) pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }

    public ArrayList<SanDTO> selectSanJoinLoaiSanHoatDong() {
        ArrayList<SanDTO> result = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT s.masan as masan, s.tensan as tensan, s.giasan AS giasan, s.ghichu as ghichu, s.trangthai as trangthai, ls.tenloaisan as tenloaisan, s.loaisan as loaisan"
                    + " FROM san AS s JOIN loaisan AS ls on ls.maloaisan=s.loaisan "
                    + " WHERE s.trangthai=1 ";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("masan");
                String tenSan = rs.getString("tensan");
                int loaiSan = rs.getInt("loaisan");
                double giaSan = rs.getDouble("giasan");
                String moTa = rs.getString("ghichu");
                int status = rs.getInt("trangthai");
                String tenLoai = rs.getString("tenloaisan");

                SanDTO san = new SanDTO(ID, loaiSan, tenSan, giaSan, moTa, status);
                san.setTenloaisan(tenLoai);

                result.add(san);
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }
}
