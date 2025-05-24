package DAO;

import DTO.DatSanDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phucp
 */
public class DatSanDAO implements DAOinterface<DatSanDTO> {

    public static DatSanDAO getInstance() {
        return new DatSanDAO();
    }

    @Override
    public int insert(DatSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `datsan`(`madatsan`,`manhanvien`,`masan`,`makhachhang`,`checkin`,`checkout`,`giasan`,`tongtien`,`thanhtoan`,`ghichu`,`trangthai`,`ngaydat`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMadatsan());
            pst.setInt(2, t.getManhanvien());
            pst.setInt(3, t.getMasan());
            pst.setInt(4, t.getMakhachhang());
            pst.setTime(5, java.sql.Time.valueOf(t.getCheckin()));
            pst.setTime(6, java.sql.Time.valueOf(t.getCheckout()));
            pst.setDouble(7, t.getGiasan());
            pst.setDouble(8, t.getTongtien());
            pst.setString(9, t.getThanhtoan());
            pst.setString(10, t.getGhichu());
            pst.setInt(11, t.getTrangthai());
            pst.setDate(12, (java.sql.Date) t.getNgaydat());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(DatSanDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `datsan` SET `manhanvien`=?,`masan`=?,`makhachhang`=?,`checkin`=?,`checkout`=?,"
                    + "`giasan`=?,`tongtien`=?,`thanhtoan`=?,`ghichu`=?,`trangthai`=?,`ngaydat`=? WHERE `madatsan`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManhanvien());
            pst.setInt(2, t.getMasan());
            pst.setInt(3, t.getMakhachhang());
            pst.setTime(4, java.sql.Time.valueOf(t.getCheckin()));
            pst.setTime(5, java.sql.Time.valueOf(t.getCheckout()));
            pst.setDouble(6, t.getGiasan());
            pst.setDouble(7, t.getTongtien());
            pst.setString(8, t.getThanhtoan());
            pst.setString(9, t.getGhichu());
            pst.setInt(10, t.getTrangthai());
            pst.setDate(11, (java.sql.Date) t.getNgaydat());
            pst.setInt(12, t.getMadatsan());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update datsan set `trangthai` = -1, `thanhtoan`='Đã hủy sân' WHERE madatsan = ?";
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
    public ArrayList<DatSanDTO> selectAll() {
        ArrayList<DatSanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM datsan";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maDS = rs.getInt("madatsan");
                int maNV = rs.getInt("manhanvien");
                int maS = rs.getInt("masan");
                int maKH = rs.getInt("makhachhang");
                LocalTime checkin = rs.getTime("checkin").toLocalTime();
                LocalTime checkout = rs.getTime("checkout").toLocalTime();
                Date ngaydat = rs.getDate("ngaydat");
                Double giasan = rs.getDouble("giasan");
                Double tongtien = rs.getDouble("tongtien");
                String thanhtoan = rs.getString("thanhtoan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                DatSanDTO ds = new DatSanDTO(maDS, maNV, maS, maKH, checkin, checkout, ngaydat, giasan, tongtien, thanhtoan, ghichu, trangthai);
                result.add(ds);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public DatSanDTO selectById(String t) {
        DatSanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM datsan WHERE madatsan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maDS = rs.getInt("madatsan");
                int maNV = rs.getInt("manhanvien");
                int maS = rs.getInt("masan");
                int maKH = rs.getInt("makhachhang");
                LocalTime checkin = rs.getTime("checkin").toLocalTime();
                LocalTime checkout = rs.getTime("checkout").toLocalTime();
                Double giasan = rs.getDouble("giasan");
                Double tongtien = rs.getDouble("tongtien");
                String thanhtoan = rs.getString("thanhtoan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                Date ngaydat = rs.getDate("ngaydat");
                result = new DatSanDTO(maDS, maNV, maS, maKH, checkin, checkout, ngaydat, giasan, tongtien, thanhtoan, ghichu, trangthai);

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
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM datsan";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatSanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result + 1;
//        try {
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

    public static ArrayList<DatSanDTO> getBySan(int masan) {
        ArrayList<DatSanDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM datsan WHERE masan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, masan);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maDS = rs.getInt("madatsan");
                int maNV = rs.getInt("manhanvien");
                int maS = rs.getInt("masan");
                int maKH = rs.getInt("makhachhang");
                LocalTime checkin = rs.getTime("checkin").toLocalTime();
                LocalTime checkout = rs.getTime("checkout").toLocalTime();
                Double giasan = rs.getDouble("giasan");
                Double tongtien = rs.getDouble("tongtien");
                Date ngaydat = rs.getDate("ngaydat");
                String thanhtoan = rs.getString("thanhtoan");
                String ghichu = rs.getString("ghichu");
                int trangthai = rs.getInt("trangthai");
                DatSanDTO ds = new DatSanDTO(maDS, maNV, maS, maKH, checkin, checkout, ngaydat, giasan, tongtien, thanhtoan, ghichu, trangthai);
                result.add(ds);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

}
