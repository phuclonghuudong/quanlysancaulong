package DTO;

import java.util.Date;

public class SanCauLongDTO {

    private int id;
    private String tenSan;
    private int idLoai;
    private String moTa;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public SanCauLongDTO() {
    }

    public SanCauLongDTO(int id, String tenSan, int idLoai, String moTa, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.tenSan = tenSan;
        this.idLoai = idLoai;
        this.moTa = moTa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SanCauLongModel{" + "id=" + id + ", tenSan=" + tenSan + ", idLoai=" + idLoai + ", moTa=" + moTa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
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
