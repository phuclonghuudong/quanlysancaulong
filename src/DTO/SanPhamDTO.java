package DTO;

import java.util.Date;

public class SanPhamDTO {

    private int id;
    private String tenSanPham;
    private int idLoaiSanPham;
    private double giaBan;
    private int soLuong;
    private String moTa;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int id, String tenSanPham, int idLoaiSanPham, double giaBan, int soLuong, String moTa, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.idLoaiSanPham = idLoaiSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SanPhamModel{" + "id=" + id + ", tenSanPham=" + tenSanPham + ", idLoaiSanPham=" + idLoaiSanPham + ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", moTa=" + moTa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
