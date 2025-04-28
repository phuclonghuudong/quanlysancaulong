package DTO;

import java.util.Date;

public class KhachHangDTO {

    private int id;
    private String tenKhachHang;
    private String email;
    private String soDienThoai;
    private String MatKhau;
    private String chucNang;
    private boolean gioiTinh;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public KhachHangDTO(int makh, String tenkhachhang, String sdt, String diachi, Date ngaythamgia) {
    }

    public KhachHangDTO(int id, String tenKhachHang, String email, String soDienThoai, String MatKhau, String chucNang, boolean gioiTinh, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.tenKhachHang = tenKhachHang;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.MatKhau = MatKhau;
        this.chucNang = chucNang;
        this.gioiTinh = gioiTinh;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "KhachHangModel{" + "id=" + id + ", tenKhachHang=" + tenKhachHang + ", email=" + email + ", soDienThoai=" + soDienThoai + ", MatKhau=" + MatKhau + ", chucNang=" + chucNang + ", gioiTinh=" + gioiTinh + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getChucNang() {
        return chucNang;
    }

    public void setChucNang(String chucNang) {
        this.chucNang = chucNang;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaKH() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getHoten() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getDiachi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getSdt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
