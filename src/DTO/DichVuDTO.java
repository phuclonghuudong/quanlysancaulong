package DTO;

import java.util.Date;

public class DichVuDTO {

    private int ID;
    private String Ten_Dich_Vu;
    private int Don_Vi_Tinh;
    private double Don_Gia;
    private String Mo_Ta;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public DichVuDTO(int ID, String Ten_Dich_Vu, int Don_Vi_Tinh, double Don_Gia, String Mo_Ta, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = ID;
        this.Ten_Dich_Vu = Ten_Dich_Vu;
        this.Don_Vi_Tinh = Don_Vi_Tinh;
        this.Don_Gia = Don_Gia;
        this.Mo_Ta = Mo_Ta;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    public DichVuDTO() {
    }

    @Override
    public String toString() {
        return "DichVuDTO{" + "ID=" + ID + ", Ten_Dich_Vu=" + Ten_Dich_Vu + ", Don_Vi_Tinh=" + Don_Vi_Tinh + ", Don_Gia=" + Don_Gia + ", Mo_Ta=" + Mo_Ta + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen_Dich_Vu() {
        return Ten_Dich_Vu;
    }

    public void setTen_Dich_Vu(String Ten_Dich_Vu) {
        this.Ten_Dich_Vu = Ten_Dich_Vu;
    }

    public int getDon_Vi_Tinh() {
        return Don_Vi_Tinh;
    }

    public void setDon_Vi_Tinh(int Don_Vi_Tinh) {
        this.Don_Vi_Tinh = Don_Vi_Tinh;
    }

    public double getDon_Gia() {
        return Don_Gia;
    }

    public void setDon_Gia(double Don_Gia) {
        this.Don_Gia = Don_Gia;
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
