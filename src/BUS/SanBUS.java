package BUS;

import DAO.SanDAO;
import DTO.SanDTO;
import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author phucp
 */
public class SanBUS {

    private final SanDAO sanDAO = new SanDAO();
    public ArrayList<SanDTO> listSan = new ArrayList<>();

    public SanBUS() {
        listSan = sanDAO.selectAll();
    }

    public ArrayList<SanDTO> getAll() {
        return this.listSan;
    }

    public ArrayList<SanDTO> getAllLoaiSan() {
        return sanDAO.selectSanJoinLoaiSan();
    }

    public SanDTO getByIndex(int index) {
        return this.listSan.get(index);
    }

    public int getIndexByMaDV(int ID) {
        int i = 0;
        int vitri = -1;
        while (i < this.listSan.size() && vitri == -1) {
            if (listSan.get(i).getMasan() == ID) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(SanDTO kh) {
        boolean check = sanDAO.insert(kh) != 0;
        if (check) {
            this.listSan.add(kh);
        }
        return check;
    }

    public Boolean delete(SanDTO kh) {
        boolean check = sanDAO.delete(Integer.toString(kh.getMasan())) != 0;
        if (check) {
            this.listSan = sanDAO.selectSanJoinLoaiSan();
        }
        return check;
    }

    public Boolean update(SanDTO kh) {
        boolean check = sanDAO.update(kh) != 0;
        if (check) {
            this.listSan.set(getIndexByMaDV(kh.getMasan()), kh);
        }
        return check;
    }

    public ArrayList<SanDTO> search(String text, String type) {
        ArrayList<SanDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (SanDTO i : this.listSan) {
                    if (Integer.toString(i.getMasan()).toLowerCase().contains(text) || i.getTensan().toLowerCase().contains(text) || i.getGhichu().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã sân" -> {
                for (SanDTO i : this.listSan) {
                    if (Integer.toString(i.getMasan()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên sân" -> {
                for (SanDTO i : this.listSan) {
                    if (i.getTensan().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }

            case "Trạng thái" -> {
                for (SanDTO i : this.listSan) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động"
                            : i.getTrangthai() == 2 ? "Trống"
                            : i.getTrangthai() == 3 ? "Đang bảo trì" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String validateSan(int loaiSan, String tenSan, double giaSan, String ghiChu, String trangThai, int currentID) {
        if (Validation.isEmpty(tenSan)) {
            return "Tên sân không được rỗng";
        }
        if (giaSan <= 0) {
            return "Giá sân phải lớn hơn 0";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }

        if (SanDAO.getInstance().isNameUnique(tenSan, currentID)) {
            return "Tên sân đã được sử dụng!";
        }

        return "valid";
    }
}
