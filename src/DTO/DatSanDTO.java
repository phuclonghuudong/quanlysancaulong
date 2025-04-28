package DTO;

import java.sql.Time;
import java.util.Date;

public class DatSanDTO {

    private int id;
    private int idSan;
    private int idKhachHang;
    private Time timeBatDau;
    private Time timeKetThuc;
    private double tongTien;
    private String daThanhToan;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public DatSanDTO() {
    }

    public DatSanDTO(int id, int idSan, int idKhachHang, Time timeBatDau, Time timeKetThuc, double tongTien, String daThanhToan, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.idSan = idSan;
        this.idKhachHang = idKhachHang;
        this.timeBatDau = timeBatDau;
        this.timeKetThuc = timeKetThuc;
        this.tongTien = tongTien;
        this.daThanhToan = daThanhToan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DatSanModel{" + "id=" + id + ", idSan=" + idSan + ", idKhachHang=" + idKhachHang + ", timeBatDau=" + timeBatDau + ", timeKetThuc=" + timeKetThuc + ", tongTien=" + tongTien + ", daThanhToan=" + daThanhToan + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Date getTimeBatDau() {
        return timeBatDau;
    }

    public void setTimeBatDau(Time timeBatDau) {
        this.timeBatDau = timeBatDau;
    }

    public Date getTimeKetThuc() {
        return timeKetThuc;
    }

    public void setTimeKetThuc(Time timeKetThuc) {
        this.timeKetThuc = timeKetThuc;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(String daThanhToan) {
        this.daThanhToan = daThanhToan;
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
