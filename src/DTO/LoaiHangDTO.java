package DTO;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author phucp
 */
public class LoaiHangDTO {

    private int mahang;
    private String tenloaihang;
    private String ghichu;
    private int trangthai;
    private Timestamp ngaytao;

    public LoaiHangDTO() {
    }

    public LoaiHangDTO(int mahang, String tenloaihang, String ghichu, int trangthai) {
        this.mahang = mahang;
        this.tenloaihang = tenloaihang;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "LoaiHangDTO{" + "mahang=" + mahang + ", tenloaihang=" + tenloaihang + ", ghichu=" + ghichu + ", trangthai=" + trangthai + ", ngaytao=" + ngaytao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.mahang;
        hash = 53 * hash + Objects.hashCode(this.tenloaihang);
        hash = 53 * hash + Objects.hashCode(this.ghichu);
        hash = 53 * hash + this.trangthai;
        hash = 53 * hash + Objects.hashCode(this.ngaytao);
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
        final LoaiHangDTO other = (LoaiHangDTO) obj;
        if (this.mahang != other.mahang) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        if (!Objects.equals(this.tenloaihang, other.tenloaihang)) {
            return false;
        }
        if (!Objects.equals(this.ghichu, other.ghichu)) {
            return false;
        }
        return Objects.equals(this.ngaytao, other.ngaytao);
    }

    public int getMahang() {
        return mahang;
    }

    public void setMahang(int mahang) {
        this.mahang = mahang;
    }

    public String getTenloaihang() {
        return tenloaihang;
    }

    public void setTenloaihang(String tenloaihang) {
        this.tenloaihang = tenloaihang;
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
