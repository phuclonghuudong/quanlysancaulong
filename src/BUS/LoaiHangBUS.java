package BUS;

import DAO.LoaiHangDAO;
import DTO.LoaiHangDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import utils.Validation;

/**
 *
 * @author phucp
 */
public class LoaiHangBUS {

    private final LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
    public ArrayList<LoaiHangDTO> listLoaiHang = new ArrayList<>();

    public LoaiHangBUS() {
        listLoaiHang = loaiHangDAO.selectAll();
    }

    public ArrayList<LoaiHangDTO> getAll() {
        return this.listLoaiHang;
    }

    public LoaiHangDTO getByIndex(int index) {
        return this.listLoaiHang.get(index);
    }

    public int getIndexByMaDV(int ID) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLoaiHang.size() && vitri == -1) {
            if (listLoaiHang.get(i).getMaloaihang() == ID) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(LoaiHangDTO kh) {
        boolean check = loaiHangDAO.insert(kh) != 0;
        if (check) {
            this.listLoaiHang.add(kh);
        }
        return check;
    }

    public Boolean delete(LoaiHangDTO kh) {
        boolean check = loaiHangDAO.delete(Integer.toString(kh.getMaloaihang())) != 0;
        if (check) {
            this.listLoaiHang = loaiHangDAO.selectAll();
        }
        return check;
    }

    public Boolean update(LoaiHangDTO kh) {
        boolean check = loaiHangDAO.update(kh) != 0;
        if (check) {
            this.listLoaiHang.set(getIndexByMaDV(kh.getMaloaihang()), kh);
        }
        return check;
    }

    public String[] getArrTenLoaiHang() {
        Set<String> vaiTroSet = new HashSet<>();
        for (LoaiHangDTO kh : listLoaiHang) {
            vaiTroSet.add(kh.getTenloaihang());
        }
        return vaiTroSet.toArray(String[]::new);
    }

    public ArrayList<LoaiHangDTO> search(String text, String type) {
        ArrayList<LoaiHangDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (LoaiHangDTO i : this.listLoaiHang) {
                    if (Integer.toString(i.getMaloaihang()).toLowerCase().contains(text) || i.getTenloaihang().toLowerCase().contains(text) || i.getGhichu().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã loại" -> {
                for (LoaiHangDTO i : this.listLoaiHang) {
                    if (Integer.toString(i.getMaloaihang()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên loại hàng" -> {
                for (LoaiHangDTO i : this.listLoaiHang) {
                    if (i.getTenloaihang().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Trạng thái" -> {
                for (LoaiHangDTO i : this.listLoaiHang) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String validateLoaiHang(String tenLoai, String trangThai, int currentID) {
        if (Validation.isEmpty(tenLoai)) {
            return "Tên loại hàng không được rỗng";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }

        if (LoaiHangDAO.getInstance().isNameUnique(tenLoai, currentID)) {
            return "Tên loại hàng đã được sử dụng!";
        }

        return "valid";
    }
}
