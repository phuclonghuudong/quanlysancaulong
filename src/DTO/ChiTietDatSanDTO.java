package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class ChiTietDatSanDTO {

    private int ID;
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

    public ChiTietDatSanDTO(int ID, int madatsan, int masanpham, int soluong, double giatien, String ghichu, int trangthai) {
        this.ID = ID;
        this.madatsan = madatsan;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    public ChiTietDatSanDTO(int ID, int madatsan, int masanpham, int soluong, double giatien, String ghichu, int trangthai, Timestamp ngaytao, Timestamp ngaycapnhat) {
        this.ID = ID;
        this.madatsan = madatsan;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
        this.ngaycapnhat = ngaycapnhat;
    }

    @Override
    public String toString() {
        return "ChiTietDatSanDTO{" + "ID=" + ID + ", madatsan=" + madatsan + ", masanpham=" + masanpham + ", soluong=" + soluong + ", giatien=" + giatien + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + this.madatsan;
        hash = 59 * hash + this.masanpham;
        hash = 59 * hash + this.soluong;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.giatien) ^ (Double.doubleToLongBits(this.giatien) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.ghichu);
        hash = 59 * hash + this.trangthai;
        hash = 59 * hash + Objects.hashCode(this.ngaytao);
        hash = 59 * hash + Objects.hashCode(this.ngaycapnhat);
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
        if (this.ID != other.ID) {
            return false;
        }
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

}
