package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class SanDTO {

    private int masan;
    private int loaisan;
    private String tensan;
    private String tenloaisan;
    private double giasan;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;
    private Timestamp ngaycapnhat;

    public SanDTO() {
    }

    public SanDTO(int masan, int loaisan, String tensan, double giasan, String ghichu, int trangthai) {
        this.masan = masan;
        this.loaisan = loaisan;
        this.tensan = tensan;
        this.giasan = giasan;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "SanDTO{" + "masan=" + masan + ", loaisan=" + loaisan + ", tensan=" + tensan + ", giasan=" + giasan + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.masan;
        hash = 59 * hash + this.loaisan;
        hash = 59 * hash + Objects.hashCode(this.tensan);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.giasan) ^ (Double.doubleToLongBits(this.giasan) >>> 32));
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
        final SanDTO other = (SanDTO) obj;
        if (this.masan != other.masan) {
            return false;
        }
        if (this.loaisan != other.loaisan) {
            return false;
        }
        if (Double.doubleToLongBits(this.giasan) != Double.doubleToLongBits(other.giasan)) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.tensan, other.tensan)) {
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

    public Timestamp getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Timestamp ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    public int getMasan() {
        return masan;
    }

    public void setMasan(int masan) {
        this.masan = masan;
    }

    public int getLoaisan() {
        return loaisan;
    }

    public void setLoaisan(int loaisan) {
        this.loaisan = loaisan;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    public double getGiasan() {
        return giasan;
    }

    public void setGiasan(double giasan) {
        this.giasan = giasan;
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
// Thêm thông tin

    public String getTenloaisan() {
        return tenloaisan;
    }

    public void setTenloaisan(String tenLoai) {
        this.tenloaisan = tenLoai;
    }
}
