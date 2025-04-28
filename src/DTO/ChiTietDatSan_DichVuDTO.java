package DTO;

import java.util.Date;

public class ChiTietDatSan_DichVuDTO {

    private int id;
    private int idDatSan;
    private int idDichVu;
    private double tongTien;
    private int soLuong;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public ChiTietDatSan_DichVuDTO() {
    }

    public ChiTietDatSan_DichVuDTO(int id, int idDatSan, int idDichVu, double tongTien, int soLuong, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.idDatSan = idDatSan;
        this.idDichVu = idDichVu;
        this.tongTien = tongTien;
        this.soLuong = soLuong;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChiTietDatSan_DichVu{" + "id=" + id + ", idDatSan=" + idDatSan + ", idDichVu=" + idDichVu + ", tongTien=" + tongTien + ", soLuong=" + soLuong + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDatSan() {
        return idDatSan;
    }

    public void setIdDatSan(int idDatSan) {
        this.idDatSan = idDatSan;
    }

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
