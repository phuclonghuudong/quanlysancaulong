package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class SanPhamDTO {

    private int masanpham;
    private int loaisanpham;
    private String tensanpham;
    private double giaban;
    private int soluong;
    private String donvi;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int masanpham, int loaisanpham, String tensanpham, double giaban, int soluong, String donvi, String ghichu, int trangthai) {
        this.masanpham = masanpham;
        this.loaisanpham = loaisanpham;
        this.tensanpham = tensanpham;
        this.giaban = giaban;
        this.soluong = soluong;
        this.donvi = donvi;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getLoaisanpham() {
        return loaisanpham;
    }

    public void setLoaisanpham(int loaisanpham) {
        this.loaisanpham = loaisanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
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

    @Override
    public String toString() {
        return "SanPhamDTO{" + "masanpham=" + masanpham + ", loaisanpham=" + loaisanpham + ", tensanpham=" + tensanpham + ", giaban=" + giaban + ", soluong=" + soluong + ", donvi=" + donvi + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.masanpham;
        hash = 97 * hash + this.loaisanpham;
        hash = 97 * hash + Objects.hashCode(this.tensanpham);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.giaban) ^ (Double.doubleToLongBits(this.giaban) >>> 32));
        hash = 97 * hash + this.soluong;
        hash = 97 * hash + Objects.hashCode(this.donvi);
        hash = 97 * hash + Objects.hashCode(this.ghichu);
        hash = 97 * hash + this.trangthai;
        hash = 97 * hash + Objects.hashCode(this.ngaytao);
        hash = 97 * hash + Objects.hashCode(this.ngaycapnhat);
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
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.masanpham != other.masanpham) {
            return false;
        }
        if (this.loaisanpham != other.loaisanpham) {
            return false;
        }
        if (Double.doubleToLongBits(this.giaban) != Double.doubleToLongBits(other.giaban)) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.tensanpham, other.tensanpham)) {
            return false;
        }
        if (!Objects.equals(this.donvi, other.donvi)) {
            return false;
        }
        if (!Objects.equals(this.ghichu, other.ghichu)) {
            return false;
        }
        if (!Objects.equals(this.ngaytao, other.ngaytao)) {
            return false;
        }
        return Objects.equals(this.ngaycapnhat, other.ngaycapnhat);
    }

}
