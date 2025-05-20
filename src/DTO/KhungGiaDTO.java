package DTO;

import java.security.Timestamp;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class KhungGiaDTO {

    private int makhunggia;
    private LocalTime checkin;
    private LocalTime checkout;
    private String thu;
    private String loaingay;
    private double dongia;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public KhungGiaDTO() {
    }

    public KhungGiaDTO(int makhunggia, LocalTime checkin, LocalTime checkout, String thu, String loaingay, double dongia, int trangthai) {
        this.makhunggia = makhunggia;
        this.checkin = checkin;
        this.checkout = checkout;
        this.thu = thu;
        this.loaingay = loaingay;
        this.dongia = dongia;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "KhungGiaDTO{" + "makhunggia=" + makhunggia + ", checkin=" + checkin + ", checkout=" + checkout + ", thu=" + thu + ", loaingay=" + loaingay + ", dongia=" + dongia + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.makhunggia;
        hash = 67 * hash + Objects.hashCode(this.checkin);
        hash = 67 * hash + Objects.hashCode(this.checkout);
        hash = 67 * hash + Objects.hashCode(this.thu);
        hash = 67 * hash + Objects.hashCode(this.loaingay);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.dongia) ^ (Double.doubleToLongBits(this.dongia) >>> 32));
        hash = 67 * hash + this.trangthai;
        hash = 67 * hash + Objects.hashCode(this.ngaytao);
        hash = 67 * hash + Objects.hashCode(this.ngaycapnhat);
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
        final KhungGiaDTO other = (KhungGiaDTO) obj;
        if (this.makhunggia != other.makhunggia) {
            return false;
        }
        if (Double.doubleToLongBits(this.dongia) != Double.doubleToLongBits(other.dongia)) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.thu, other.thu)) {
            return false;
        }
        if (!Objects.equals(this.loaingay, other.loaingay)) {
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

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public int getMakhunggia() {
        return makhunggia;
    }

    public void setMakhunggia(int makhunggia) {
        this.makhunggia = makhunggia;
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

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getLoaingay() {
        return loaingay;
    }

    public void setLoaingay(String loaingay) {
        this.loaingay = loaingay;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
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
