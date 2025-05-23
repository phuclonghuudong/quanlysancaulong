package DTO;

import java.security.Timestamp;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class DatSanDTO {

    private int madatsan;
    private int makhachhang;
    private int masan;
    private int manhanvien;
    private LocalTime checkin;
    private LocalTime checkout;
    private double giasan;
    private double tongtien;
    private String thanhtoan;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public DatSanDTO() {
    }

    public DatSanDTO(int madatsan, int makhachhang, int masan, int manhanvien, LocalTime checkin, LocalTime checkout, double giasan, double tongtien, String thanhtoan, String ghichu, int trangthai) {
        this.madatsan = madatsan;
        this.makhachhang = makhachhang;
        this.masan = masan;
        this.manhanvien = manhanvien;
        this.checkin = checkin;
        this.checkout = checkout;
        this.giasan = giasan;
        this.tongtien = tongtien;
        this.thanhtoan = thanhtoan;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    public DatSanDTO(int madatsan, int makhachhang, int masan, int manhanvien, LocalTime checkin, LocalTime checkout, double giasan, double tongtien, String thanhtoan, String ghichu, int trangthai, Timestamp ngaytao, Timestamp ngaycapnhat) {
        this.madatsan = madatsan;
        this.makhachhang = makhachhang;
        this.masan = masan;
        this.manhanvien = manhanvien;
        this.checkin = checkin;
        this.checkout = checkout;
        this.giasan = giasan;
        this.tongtien = tongtien;
        this.thanhtoan = thanhtoan;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
        this.ngaycapnhat = ngaycapnhat;
    }

    @Override
    public String toString() {
        return "DatSanDTO{" + "madatsan=" + madatsan + ", makhachhang=" + makhachhang + ", masan=" + masan + ", manhanvien=" + manhanvien + ", checkin=" + checkin + ", checkout=" + checkout + ", giasan=" + giasan + ", tongtien=" + tongtien + ", thanhtoan=" + thanhtoan + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.madatsan;
        hash = 97 * hash + this.makhachhang;
        hash = 97 * hash + this.masan;
        hash = 97 * hash + this.manhanvien;
        hash = 97 * hash + Objects.hashCode(this.checkin);
        hash = 97 * hash + Objects.hashCode(this.checkout);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.giasan) ^ (Double.doubleToLongBits(this.giasan) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tongtien) ^ (Double.doubleToLongBits(this.tongtien) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.thanhtoan);
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
        final DatSanDTO other = (DatSanDTO) obj;
        if (this.madatsan != other.madatsan) {
            return false;
        }
        if (this.makhachhang != other.makhachhang) {
            return false;
        }
        if (this.masan != other.masan) {
            return false;
        }
        if (this.manhanvien != other.manhanvien) {
            return false;
        }
        if (Double.doubleToLongBits(this.giasan) != Double.doubleToLongBits(other.giasan)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tongtien) != Double.doubleToLongBits(other.tongtien)) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.thanhtoan, other.thanhtoan)) {
            return false;
        }
        if (!Objects.equals(this.ghichu, other.ghichu)) {
            return false;
        }
        if (!Objects.equals(this.checkin, other.checkin)) {
            return false;
        }
        if (!Objects.equals(this.checkout, other.checkout)) {
            return false;
        }
        if (!Objects.equals(this.ngaytao, other.ngaytao)) {
            return false;
        }
        return Objects.equals(this.ngaycapnhat, other.ngaycapnhat);
    }

    public double getGiasan() {
        return giasan;
    }

    public void setGiasan(double giasan) {
        this.giasan = giasan;
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

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public int getMasan() {
        return masan;
    }

    public void setMasan(int masan) {
        this.masan = masan;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public LocalTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalTime checkin) {
        this.checkin = checkin;
    }

    public LocalTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalTime checkout) {
        this.checkout = checkout;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
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

}
