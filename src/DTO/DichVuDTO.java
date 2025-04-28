package DTO;

import java.util.Date;

public class DichVuDTO {

    private int id;
    private String tenDichVu;
    private int donViTinh;
    private String donGia;
    private String moTa;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public DichVuDTO() {
    }

    public DichVuDTO(int id, String tenDichVu, int donViTinh, String donGia, String moTa, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.moTa = moTa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DichVuModel{" + "id=" + id + ", tenDichVu=" + tenDichVu + ", donViTinh=" + donViTinh + ", donGia=" + donGia + ", moTa=" + moTa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(int donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
