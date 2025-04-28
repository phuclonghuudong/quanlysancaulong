package DTO;

import java.util.Date;

public class LoaiSanDTO {

    private int id;
    private String tenLoaiSan;
    private String moTa;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public LoaiSanDTO() {
    }

    public LoaiSanDTO(int id, String tenLoaiSan, String moTa, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.tenLoaiSan = tenLoaiSan;
        this.moTa = moTa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoaiSanModel{" + "id=" + id + ", tenLoaiSan=" + tenLoaiSan + ", moTa=" + moTa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSan() {
        return tenLoaiSan;
    }

    public void setTenLoaiSan(String tenLoaiSan) {
        this.tenLoaiSan = tenLoaiSan;
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
