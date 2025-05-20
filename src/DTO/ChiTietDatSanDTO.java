package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class ChiTietDatSanDTO {

    private int madatsan;
    private int masanpham;
    private int soluong;
    private double giatien;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public ChiTietDatSanDTO() {
    }

    public ChiTietDatSanDTO(int madatsan, int masanpham, int soluong, double giatien, String ghichu, int trangthai) {
        this.madatsan = madatsan;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "ChiTietDatSanDTO{" + "madatsan=" + madatsan + ", masanpham=" + masanpham + ", soluong=" + soluong + ", giatien=" + giatien + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public int getMadatsan() {
        return madatsan;
    }

    public void setMadatsan(int madatsan) {
        this.madatsan = madatsan;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
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
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.madatsan;
        hash = 79 * hash + this.masanpham;
        hash = 79 * hash + this.soluong;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.giatien) ^ (Double.doubleToLongBits(this.giatien) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.ghichu);
        hash = 79 * hash + this.trangthai;
        hash = 79 * hash + Objects.hashCode(this.ngaytao);
        hash = 79 * hash + Objects.hashCode(this.ngaycapnhat);
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
        final ChiTietDatSanDTO other = (ChiTietDatSanDTO) obj;
        if (this.madatsan != other.madatsan) {
            return false;
        }
        if (this.masanpham != other.masanpham) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        if (Double.doubleToLongBits(this.giatien) != Double.doubleToLongBits(other.giatien)) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
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
