package DTO;

import java.util.Date;
import java.util.Objects;

public class KhachHangDTO {

    private int ID;
    private String Ten_Khach_hang;
    private String Email;
    private Date Ngay_Sinh;
    private String So_Dien_Thoai;
    private String Mat_Khau;
    private String Vai_Tro;
    private boolean Gioi_Tinh;
    private Date Ngay_Tao;
    private Date Ngay_Cap_Nhat;
    private int Status;

    public KhachHangDTO() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.ID;
        hash = 29 * hash + Objects.hashCode(this.Ten_Khach_hang);
        hash = 29 * hash + Objects.hashCode(this.Email);
        hash = 29 * hash + Objects.hashCode(this.Ngay_Sinh);
        hash = 29 * hash + Objects.hashCode(this.So_Dien_Thoai);
        hash = 29 * hash + Objects.hashCode(this.Mat_Khau);
        hash = 29 * hash + Objects.hashCode(this.Vai_Tro);
        hash = 29 * hash + (this.Gioi_Tinh ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.Ngay_Tao);
        hash = 29 * hash + Objects.hashCode(this.Ngay_Cap_Nhat);
        hash = 29 * hash + this.Status;
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
        final KhachHangDTO other = (KhachHangDTO) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.Gioi_Tinh != other.Gioi_Tinh) {
            return false;
        }
        if (this.Status != other.Status) {
            return false;
        }
        if (!Objects.equals(this.Ten_Khach_hang, other.Ten_Khach_hang)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.So_Dien_Thoai, other.So_Dien_Thoai)) {
            return false;
        }
        if (!Objects.equals(this.Mat_Khau, other.Mat_Khau)) {
            return false;
        }
        if (!Objects.equals(this.Vai_Tro, other.Vai_Tro)) {
            return false;
        }
        if (!Objects.equals(this.Ngay_Sinh, other.Ngay_Sinh)) {
            return false;
        }
        if (!Objects.equals(this.Ngay_Tao, other.Ngay_Tao)) {
            return false;
        }
        return Objects.equals(this.Ngay_Cap_Nhat, other.Ngay_Cap_Nhat);
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" + "ID=" + ID + ", Ten_Khach_hang=" + Ten_Khach_hang + ", Email=" + Email + ", Ngay_Sinh=" + Ngay_Sinh + ", So_Dien_Thoai=" + So_Dien_Thoai + ", Mat_Khau=" + Mat_Khau + ", Vai_Tro=" + Vai_Tro + ", Gioi_Tinh=" + Gioi_Tinh + ", Ngay_Tao=" + Ngay_Tao + ", Ngay_Cap_Nhat=" + Ngay_Cap_Nhat + ", Status=" + Status + '}';
    }

    public KhachHangDTO(int ID, String Ten_Khach_hang, String Email, Date Ngay_Sinh, String So_Dien_Thoai, String Mat_Khau, String Vai_Tro, boolean Gioi_Tinh, Date Ngay_Tao, Date Ngay_Cap_Nhat, int Status) {
        this.ID = ID;
        this.Ten_Khach_hang = Ten_Khach_hang;
        this.Email = Email;
        this.Ngay_Sinh = Ngay_Sinh;
        this.So_Dien_Thoai = So_Dien_Thoai;
        this.Mat_Khau = Mat_Khau;
        this.Vai_Tro = Vai_Tro;
        this.Gioi_Tinh = Gioi_Tinh;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
        this.Status = Status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen_Khach_hang() {
        return Ten_Khach_hang;
    }

    public void setTen_Khach_hang(String Ten_Khach_hang) {
        this.Ten_Khach_hang = Ten_Khach_hang;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgay_Sinh() {
        return Ngay_Sinh;
    }

    public void setNgay_Sinh(Date Ngay_Sinh) {
        this.Ngay_Sinh = Ngay_Sinh;
    }

    public String getSo_Dien_Thoai() {
        return So_Dien_Thoai;
    }

    public void setSo_Dien_Thoai(String So_Dien_Thoai) {
        this.So_Dien_Thoai = So_Dien_Thoai;
    }

    public String getMat_Khau() {
        return Mat_Khau;
    }

    public void setMat_Khau(String Mat_Khau) {
        this.Mat_Khau = Mat_Khau;
    }

    public String getVai_Tro() {
        return Vai_Tro;
    }

    public void setVai_Tro(String Vai_Tro) {
        this.Vai_Tro = Vai_Tro;
    }

    public boolean isGioi_Tinh() {
        return Gioi_Tinh;
    }

    public void setGioi_Tinh(boolean Gioi_Tinh) {
        this.Gioi_Tinh = Gioi_Tinh;
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
