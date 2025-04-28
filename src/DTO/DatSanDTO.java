package DTO;

import java.sql.Time;
import java.util.Date;

public class DatSanDTO {

    private int ID;
    private int San_ID;
    private int Khach_Hang_ID;
    private Time Thoi_Gian_Bat_Dau;
    private Time Thoi_Gian_Ket_Thuc;
    private double Tong_Tien;
    private boolean Da_Thanh_Toan;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public DatSanDTO() {
    }

    public DatSanDTO(int ID, int San_ID, int Khach_Hang_ID, Time Thoi_Gian_Bat_Dau, Time Thoi_Gian_Ket_Thuc, double Tong_Tien, boolean Da_Thanh_Toan, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = ID;
        this.San_ID = San_ID;
        this.Khach_Hang_ID = Khach_Hang_ID;
        this.Thoi_Gian_Bat_Dau = Thoi_Gian_Bat_Dau;
        this.Thoi_Gian_Ket_Thuc = Thoi_Gian_Ket_Thuc;
        this.Tong_Tien = Tong_Tien;
        this.Da_Thanh_Toan = Da_Thanh_Toan;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "DatSanDTO{" + "ID=" + ID + ", San_ID=" + San_ID + ", Khach_Hang_ID=" + Khach_Hang_ID + ", Thoi_Gian_Bat_Dau=" + Thoi_Gian_Bat_Dau + ", Thoi_Gian_Ket_Thuc=" + Thoi_Gian_Ket_Thuc + ", Tong_Tien=" + Tong_Tien + ", Da_Thanh_Toan=" + Da_Thanh_Toan + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSan_ID() {
        return San_ID;
    }

    public void setSan_ID(int San_ID) {
        this.San_ID = San_ID;
    }

    public int getKhach_Hang_ID() {
        return Khach_Hang_ID;
    }

    public void setKhach_Hang_ID(int Khach_Hang_ID) {
        this.Khach_Hang_ID = Khach_Hang_ID;
    }

    public Time getThoi_Gian_Bat_Dau() {
        return Thoi_Gian_Bat_Dau;
    }

    public void setThoi_Gian_Bat_Dau(Time Thoi_Gian_Bat_Dau) {
        this.Thoi_Gian_Bat_Dau = Thoi_Gian_Bat_Dau;
    }

    public Time getThoi_Gian_Ket_Thuc() {
        return Thoi_Gian_Ket_Thuc;
    }

    public void setThoi_Gian_Ket_Thuc(Time Thoi_Gian_Ket_Thuc) {
        this.Thoi_Gian_Ket_Thuc = Thoi_Gian_Ket_Thuc;
    }

    public double getTong_Tien() {
        return Tong_Tien;
    }

    public void setTong_Tien(double Tong_Tien) {
        this.Tong_Tien = Tong_Tien;
    }

    public boolean isDa_Thanh_Toan() {
        return Da_Thanh_Toan;
    }

    public void setDa_Thanh_Toan(boolean Da_Thanh_Toan) {
        this.Da_Thanh_Toan = Da_Thanh_Toan;
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
