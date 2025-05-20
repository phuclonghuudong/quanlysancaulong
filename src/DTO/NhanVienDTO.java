package DTO;

import java.security.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class NhanVienDTO {

    private int manhanvien;
    private String hoten;
    private String email;
    private String sodienthoai;
    private boolean gioitinh;
    private Date ngaysinh;
    private String matkhau;
    private String vaitro;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int manhanvien, String hoten, String email, String sodienthoai, boolean gioitinh, Date ngaysinh, String vaitro, int trangthai) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    public NhanVienDTO(int manhanvien, String hoten, String email, String sodienthoai, boolean gioitinh, Date ngaysinh, String matkhau, String vaitro, int trangthai, Timestamp ngaytao, Timestamp ngaycapnhat) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
        this.ngaycapnhat = ngaycapnhat;
    }

    public NhanVienDTO(int manhanvien, String hoten, String email, String sodienthoai, boolean gioitinh, Date ngaysinh, String matkhau, String vaitro, int trangthai) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "manhanvien=" + manhanvien + ", hoten=" + hoten + ", email=" + email + ", sodienthoai=" + sodienthoai + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", matkhau=" + matkhau + ", vaitro=" + vaitro + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.manhanvien;
        hash = 37 * hash + Objects.hashCode(this.hoten);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.sodienthoai);
        hash = 37 * hash + (this.gioitinh ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.ngaysinh);
        hash = 37 * hash + Objects.hashCode(this.matkhau);
        hash = 37 * hash + Objects.hashCode(this.vaitro);
        hash = 37 * hash + this.trangthai;
        hash = 37 * hash + Objects.hashCode(this.ngaytao);
        hash = 37 * hash + Objects.hashCode(this.ngaycapnhat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (this.manhanvien != other.manhanvien) {
            return false;
        }
        if (this.sodienthoai != other.sodienthoai) {
            return false;
        }
        if (this.gioitinh != other.gioitinh) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.hoten, other.hoten)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.matkhau, other.matkhau)) {
            return false;
        }
        if (!Objects.equals(this.vaitro, other.vaitro)) {
            return false;
        }
        if (!Objects.equals(this.ngaysinh, other.ngaysinh)) {
            return false;
        }
        if (!Objects.equals(this.ngaytao, other.ngaytao)) {
            return false;
        }
        return Objects.equals(this.ngaycapnhat, other.ngaycapnhat);
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Timestamp getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Timestamp ngaytao) {
        this.ngaytao = ngaytao;
    }

}
