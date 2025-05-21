package BUS;

import DAO.LoaiSanDAO;
import DTO.LoaiSanDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import utils.Validation;

/**
 *
 * @author phucp
 */
public class LoaiSanBUS {

    private final LoaiSanDAO loaiSanDAO = new LoaiSanDAO();
    public ArrayList<LoaiSanDTO> listLoaiSan = new ArrayList<>();

    public LoaiSanBUS() {
        listLoaiSan = loaiSanDAO.selectAll();
    }

    public ArrayList<LoaiSanDTO> getAll() {
        return this.listLoaiSan;
    }

    public LoaiSanDTO getByIndex(int index) {
        return this.listLoaiSan.get(index);
    }

    public int getIndexByMaDV(int ID) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLoaiSan.size() && vitri == -1) {
            if (listLoaiSan.get(i).getMaloaisan() == ID) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(LoaiSanDTO kh) {
        boolean check = loaiSanDAO.insert(kh) != 0;
        if (check) {
            this.listLoaiSan.add(kh);
        }
        return check;
    }

    public Boolean delete(LoaiSanDTO kh) {
        boolean check = loaiSanDAO.delete(Integer.toString(kh.getMaloaisan())) != 0;
        if (check) {
            this.listLoaiSan = loaiSanDAO.selectAll();
        }
        return check;
    }

    public Boolean update(LoaiSanDTO kh) {
        boolean check = loaiSanDAO.update(kh) != 0;
        if (check) {
            this.listLoaiSan.set(getIndexByMaDV(kh.getMaloaisan()), kh);
        }
        return check;
    }

    public String[] getArrTenLoaiSan() {
        Set<String> vaiTroSet = new HashSet<>();
        for (LoaiSanDTO kh : listLoaiSan) {
            vaiTroSet.add(kh.getTenloaisan());
        }
        return vaiTroSet.toArray(String[]::new);
    }

    public ArrayList<LoaiSanDTO> search(String text, String type) {
        ArrayList<LoaiSanDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (LoaiSanDTO i : this.listLoaiSan) {
                    if (Integer.toString(i.getMaloaisan()).toLowerCase().contains(text) || i.getTenloaisan().toLowerCase().contains(text) || i.getGhichu().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã loại" -> {
                for (LoaiSanDTO i : this.listLoaiSan) {
                    if (Integer.toString(i.getMaloaisan()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên loại" -> {
                for (LoaiSanDTO i : this.listLoaiSan) {
                    if (i.getTenloaisan().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mô tả" -> {
                for (LoaiSanDTO i : this.listLoaiSan) {
                    if (i.getGhichu().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Trạng thái" -> {
                for (LoaiSanDTO i : this.listLoaiSan) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String validateLoaiSan(String tenLoai, String trangThai, int currentID) {
        if (Validation.isEmpty(tenLoai)) {
            return "Tên loại sân không được rỗng";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }

        if (LoaiSanDAO.getInstance().isNameUnique(tenLoai, currentID)) {
            return "Tên loại đã được sử dụng!";
        }

        return "valid";
    }
}
