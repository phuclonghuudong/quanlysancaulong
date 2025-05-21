package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.NhanVienDTO;

/**
 *
 * @author phucp
 */
public class NhanVienDAO implements DAOinterface<NhanVienDTO> {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVienDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhanvien`(`hoten`, `email`,`sodienthoai`,`ngaysinh`,`gioitinh`,`matkhau`,`vaitro` ,`trangthai`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getSodienthoai());
            pst.setDate(4, new Date(t.getNgaysinh().getTime()));
            pst.setBoolean(5, t.isGioitinh());
            pst.setString(6, t.getMatkhau());
            pst.setString(7, t.getVaitro());
            pst.setInt(8, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhanVienDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhanvien` SET`hoten`=?,`gioitinh`=?,`ngaysinh`=?,`sodienthoai`=?, `trangthai`=?, `email`=?, `vaitro`=? WHERE `manhanvien`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setBoolean(2, t.isGioitinh());
            pst.setDate(3, (Date) t.getNgaysinh());
            pst.setString(4, t.getSodienthoai());
            pst.setInt(5, t.getTrangthai());
            pst.setString(6, t.getEmail());
            pst.setString(7, t.getVaitro());
            pst.setInt(8, t.getManhanvien());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "Update nhanvien set `trangthai` = -1 WHERE manhanvien = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manhanvien");
                String hoten = rs.getString("hoten");
                boolean gioitinh = rs.getBoolean("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sodienthoai = rs.getString("sodienthoai");
                int trangthai = rs.getInt("trangthai");
                String vaitro = rs.getString("vaitro");
                String email = rs.getString("email");
                NhanVienDTO nv = new NhanVienDTO(manv, hoten, email, sodienthoai, gioitinh, ngaysinh, vaitro, trangthai);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public NhanVienDTO selectById(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manhanvien=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manhanvien");
                String hoten = rs.getString("hoten");
                boolean gioitinh = rs.getBoolean("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sodienthoai");
                int trangthai = rs.getInt("trangthai");
                String vaitro = rs.getString("vaitro");
                String email = rs.getString("email");
                result = new NhanVienDTO(manv, hoten, email, sdt, gioitinh, ngaysinh, vaitro, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public NhanVienDTO selectByEmail(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                boolean gioitinh = rs.getBoolean("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sodienthoai");
                int trangthai = rs.getInt("trangthai");
                String vaitro = rs.getString("vaitro");
                String email = rs.getString("email");
                result = new NhanVienDTO(manv, hoten, email, sdt, gioitinh, ngaysinh, vaitro, trangthai);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlisancaulong' AND   TABLE_NAME   = 'nhanvien'";
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
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public NhanVienDTO getUserByEmailAndPassword(String email, String hashedPassword) {
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE email = ? AND matkhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                NhanVienDTO user = new NhanVienDTO();
                user.setManhanvien(rs.getInt("manhanvien"));
                user.setHoten(rs.getString("hoten"));
                user.setEmail(rs.getString("email"));
                user.setNgaysinh(rs.getDate("ngaysinh"));
                user.setSodienthoai(rs.getString("sodienthoai"));
                user.setMatkhau(rs.getString("matkhau"));
                user.setVaitro(rs.getString("vaitro"));
                user.setGioitinh(rs.getBoolean("gioitinh"));
                user.setTrangthai(rs.getInt("trangthai"));
                return user;
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean selectByEmailExist(String p) {
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p);
            ResultSet rs = (ResultSet) pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }

    public boolean selectByPhoneExist(String p) {
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE sodienthoai=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p);
            ResultSet rs = (ResultSet) pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }

}
