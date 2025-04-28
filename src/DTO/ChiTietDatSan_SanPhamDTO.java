package DTO;

import java.util.Date;

public class ChiTietDatSan_SanPhamDTO {

    private int ID;
    private int Dat_San_ID;
    private int San_Pham_ID;
    private double Tong_Tien;
    private int So_Luong;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public ChiTietDatSan_SanPhamDTO() {
    }

    public ChiTietDatSan_SanPhamDTO(int id, int Dat_San_ID, int San_Pham_ID, double Tong_Tien, int So_Luong, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = id;
        this.Dat_San_ID = Dat_San_ID;
        this.San_Pham_ID = San_Pham_ID;
        this.Tong_Tien = Tong_Tien;
        this.So_Luong = So_Luong;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "ChiTietDatSan_SanPhamDTO{" + "id=" + ID + ", Dat_San_ID=" + Dat_San_ID + ", San_Pham_ID=" + San_Pham_ID + ", Tong_Tien=" + Tong_Tien + ", So_Luong=" + So_Luong + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getDat_San_ID() {
        return Dat_San_ID;
    }

    public void setDat_San_ID(int Dat_San_ID) {
        this.Dat_San_ID = Dat_San_ID;
    }

    public int getSan_Pham_ID() {
        return San_Pham_ID;
    }

    public void setSan_Pham_ID(int San_Pham_ID) {
        this.San_Pham_ID = San_Pham_ID;
    }

    public double getTong_Tien() {
        return Tong_Tien;
    }

    public void setTong_Tien(double Tong_Tien) {
        this.Tong_Tien = Tong_Tien;
    }

    public int getSo_Luong() {
        return So_Luong;
    }

    public void setSo_Luong(int So_Luong) {
        this.So_Luong = So_Luong;
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
