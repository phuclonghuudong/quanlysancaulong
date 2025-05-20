package DTO;

import java.util.Objects;

/**
 *
 * @author phucp
 */
public class ChiTietGiaSanDTO {

    private int masan;
    private int makhunggia;
    private String ghichu;

    public ChiTietGiaSanDTO() {
    }

    public ChiTietGiaSanDTO(int masan, int makhunggia, String ghichu) {
        this.masan = masan;
        this.makhunggia = makhunggia;
        this.ghichu = ghichu;
    }

    @Override
    public String toString() {
        return "ChiTietGiaSanDTO{" + "masan=" + masan + ", makhunggia=" + makhunggia + ", ghichu=" + ghichu + '}';
    }

    public int getMasan() {
        return masan;
    }

    public void setMasan(int masan) {
        this.masan = masan;
    }

    public int getMakhunggia() {
        return makhunggia;
    }

    public void setMakhunggia(int makhunggia) {
        this.makhunggia = makhunggia;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.masan;
        hash = 23 * hash + this.makhunggia;
        hash = 23 * hash + Objects.hashCode(this.ghichu);
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
        final ChiTietGiaSanDTO other = (ChiTietGiaSanDTO) obj;
        if (this.masan != other.masan) {
            return false;
        }
        if (this.makhunggia != other.makhunggia) {
            return false;
        }
        return Objects.equals(this.ghichu, other.ghichu);
    }

}
