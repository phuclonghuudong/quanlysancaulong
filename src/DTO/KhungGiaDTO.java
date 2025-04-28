package DTO;

import java.util.Date;
import java.sql.Time;

public class KhungGiaDTO {

    private int ID;
    private Time Gio_Bat_Dau;
    private Time Gio_Ket_Thuc;
    private int Thu;
    private String Loai_Ngay;
    private double Don_Gia;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public KhungGiaDTO() {
    }

    public KhungGiaDTO(int id, Time Gio_Bat_Dau, Time Gio_Ket_Thuc, int Thu, String Loai_Ngay, double Don_Gia, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = id;
        this.Gio_Bat_Dau = Gio_Bat_Dau;
        this.Gio_Ket_Thuc = Gio_Ket_Thuc;
        this.Thu = Thu;
        this.Loai_Ngay = Loai_Ngay;
        this.Don_Gia = Don_Gia;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "KhungGiaDTO{" + "id=" + ID + ", Gio_Bat_Dau=" + Gio_Bat_Dau + ", Gio_Ket_Thuc=" + Gio_Ket_Thuc + ", Thu=" + Thu + ", Loai_Ngay=" + Loai_Ngay + ", Don_Gia=" + Don_Gia + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public Time getGio_Bat_Dau() {
        return Gio_Bat_Dau;
    }

    public void setGio_Bat_Dau(Time Gio_Bat_Dau) {
        this.Gio_Bat_Dau = Gio_Bat_Dau;
    }

    public Time getGio_Ket_Thuc() {
        return Gio_Ket_Thuc;
    }

    public void setGio_Ket_Thuc(Time Gio_Ket_Thuc) {
        this.Gio_Ket_Thuc = Gio_Ket_Thuc;
    }

    public int getThu() {
        return Thu;
    }

    public void setThu(int Thu) {
        this.Thu = Thu;
    }

    public String getLoai_Ngay() {
        return Loai_Ngay;
    }

    public void setLoai_Ngay(String Loai_Ngay) {
        this.Loai_Ngay = Loai_Ngay;
    }

    public double getDon_Gia() {
        return Don_Gia;
    }

    public void setDon_Gia(double Don_Gia) {
        this.Don_Gia = Don_Gia;
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
