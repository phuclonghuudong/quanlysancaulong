package DTO;

import java.util.Date;

public class LoaiSanPhamDTO {

    private int ID;
    private String Ten_Loai;
    private String Mo_Ta;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    @Override
    public String toString() {
        return "LoaiSanPhamDTO{" + "ID=" + ID + ", Ten_Loai=" + Ten_Loai + ", Mo_Ta=" + Mo_Ta + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", status=" + Status + '}';
    }

    public LoaiSanPhamDTO(int ID, String Ten_Loai, String Mo_Ta, Date Ngay_Tao, Date Ngay_Cap_Nhat, int status) {
        this.ID = ID;
        this.Ten_Loai = Ten_Loai;
        this.Mo_Ta = Mo_Ta;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = status;
    }

    public LoaiSanPhamDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen_Loai() {
        return Ten_Loai;
    }

    public void setTen_Loai(String Ten_Loai) {
        this.Ten_Loai = Ten_Loai;
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

    public void setStatus(int status) {
        this.Status = status;
    }

}
