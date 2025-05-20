package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class KhachHangDTO {

    private int makhachhang;
    private String hoten;
    private String sodienthoai;
    private String diachi;
    private int trangthai;
    private Timestamp ngaythamgia;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int makhachhang, String hoten, String sodienthoai, String diachi, int trangthai) {
        this.makhachhang = makhachhang;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" + "makhachhang=" + makhachhang + ", hoten=" + hoten + ", sodienthoai=" + sodienthoai + ", diachi=" + diachi + ", trangthai=" + trangthai + ", ngaythamgia=" + ngaythamgia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.makhachhang;
        hash = 37 * hash + Objects.hashCode(this.hoten);
        hash = 37 * hash + Objects.hashCode(this.sodienthoai);
        hash = 37 * hash + Objects.hashCode(this.diachi);
        hash = 37 * hash + this.trangthai;
        hash = 37 * hash + Objects.hashCode(this.ngaythamgia);
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
        final KhachHangDTO other = (KhachHangDTO) obj;
        if (this.makhachhang != other.makhachhang) {
            return false;
        }
        if (this.sodienthoai != other.sodienthoai) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.hoten, other.hoten)) {
            return false;
        }
        if (!Objects.equals(this.diachi, other.diachi)) {
            return false;
        }
        return Objects.equals(this.ngaythamgia, other.ngaythamgia);
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Timestamp getNgaythamgia() {
        return ngaythamgia;
    }

    public void setNgaythamgia(Timestamp ngaytao) {
        this.ngaythamgia = ngaytao;
    }

}
