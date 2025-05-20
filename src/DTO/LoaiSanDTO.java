package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class LoaiSanDTO {

    private int maloaisan;
    private String tenloaisan;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;

    public LoaiSanDTO() {
    }

    public LoaiSanDTO(int maloaisan, String tensan, String ghichu, int trangthai) {
        this.maloaisan = maloaisan;
        this.tenloaisan = tensan;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "LoaiSanDTO{" + "maloaisan=" + maloaisan + ", tenloaisan=" + tenloaisan + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + '}';
    }

    public int getMaloaisan() {
        return maloaisan;
    }

    public void setMaloaisan(int maloaisan) {
        this.maloaisan = maloaisan;
    }

    public String getTenloaisan() {
        return tenloaisan;
    }

    public void setTenloaisan(String tensan) {
        this.tenloaisan = tensan;
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
        int hash = 5;
        hash = 29 * hash + this.maloaisan;
        hash = 29 * hash + Objects.hashCode(this.tenloaisan);
        hash = 29 * hash + Objects.hashCode(this.ghichu);
        hash = 29 * hash + this.trangthai;
        hash = 29 * hash + Objects.hashCode(this.ngaytao);
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
        final LoaiSanDTO other = (LoaiSanDTO) obj;
        if (this.maloaisan != other.maloaisan) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.tenloaisan, other.tenloaisan)) {
            return false;
        }
        if (!Objects.equals(this.ghichu, other.ghichu)) {
            return false;
        }
        return Objects.equals(this.ngaytao, other.ngaytao);
    }

}
