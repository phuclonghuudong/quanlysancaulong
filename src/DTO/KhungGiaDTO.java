package DTO;

import java.util.Date;
import java.sql.Time;

public class KhungGiaDTO {

    private int id;
    private Time timeBatDau;
    private Time timeketThuc;
    private int thu;
    private String loaiNgay;
    private double donGia;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public KhungGiaDTO(int id, Time timeBatDau, Time timeketThuc, int thu, String loaiNgay, double donGia, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.timeBatDau = timeBatDau;
        this.timeketThuc = timeketThuc;
        this.thu = thu;
        this.loaiNgay = loaiNgay;
        this.donGia = donGia;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public KhungGiaDTO() {
    }

    @Override
    public String toString() {
        return "KhungGiaModel{" + "id=" + id + ", timeBatDau=" + timeBatDau + ", timeketThuc=" + timeketThuc + ", thu=" + thu + ", loaiNgay=" + loaiNgay + ", donGia=" + donGia + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTimeBatDau() {
        return timeBatDau;
    }

    public void setTimeBatDau(Time timeBatDau) {
        this.timeBatDau = timeBatDau;
    }

    public Time getTimeketThuc() {
        return timeketThuc;
    }

    public void setTimeketThuc(Time timeketThuc) {
        this.timeketThuc = timeketThuc;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public String getLoaiNgay() {
        return loaiNgay;
    }

    public void setLoaiNgay(String loaiNgay) {
        this.loaiNgay = loaiNgay;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
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
