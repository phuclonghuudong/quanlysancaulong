package DTO;

import java.util.Date;

public class SanPhamDTO {

    private int ID;
    private String Ten_San_Pham;
    private int Loai_San_Pham_ID;
    private double Gia_Ban;
    private int So_Luong;
    private String Mo_Ta;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int ID, String Ten_San_Pham, int Loai_San_Pham_ID, double Gia_Ban, int So_Luong, String Mo_Ta, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = ID;
        this.Ten_San_Pham = Ten_San_Pham;
        this.Loai_San_Pham_ID = Loai_San_Pham_ID;
        this.Gia_Ban = Gia_Ban;
        this.So_Luong = So_Luong;
        this.Mo_Ta = Mo_Ta;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "ID=" + ID + ", Ten_San_Pham=" + Ten_San_Pham + ", Loai_San_Pham_ID=" + Loai_San_Pham_ID + ", Gia_Ban=" + Gia_Ban + ", So_Luong=" + So_Luong + ", Mo_Ta=" + Mo_Ta + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen_San_Pham() {
        return Ten_San_Pham;
    }

    public void setTen_San_Pham(String Ten_San_Pham) {
        this.Ten_San_Pham = Ten_San_Pham;
    }

    public int getLoai_San_Pham_ID() {
        return Loai_San_Pham_ID;
    }

    public void setLoai_San_Pham_ID(int Loai_San_Pham_ID) {
        this.Loai_San_Pham_ID = Loai_San_Pham_ID;
    }

    public double getGia_Ban() {
        return Gia_Ban;
    }

    public void setGia_Ban(double Gia_Ban) {
        this.Gia_Ban = Gia_Ban;
    }

    public int getSo_Luong() {
        return So_Luong;
    }

    public void setSo_Luong(int So_Luong) {
        this.So_Luong = So_Luong;
    }

    public String getMo_Ta() {
        return Mo_Ta;
    }

    public void setMo_Ta(String Mo_Ta) {
        this.Mo_Ta = Mo_Ta;
    }

    public Date getNgay_Tao() {
        return Ngay_Tao;
    }

    public void setNgay_Tao(Date Ngay_Tao) {
        this.Ngay_Tao = Ngay_Tao;
    }

    public Date getNgay_Cap_Nhat() {
        return Ngay_Cap_Nhat;
    }

    public void setNgay_Cap_Nhat(Date Ngay_Cap_Nhat) {
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

}
