package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author phucp
 */
public class KhachHangBUS {

    private final KhachHangDAO khDAO = new KhachHangDAO();
    public ArrayList<KhachHangDTO> listDS = new ArrayList<>();

    public KhachHangBUS() {
        listDS = khDAO.selectAll();
    }

    public ArrayList<KhachHangDTO> getAll() {
        return this.listDS;
    }

    public KhachHangDTO getByIndex(int index) {
        return this.listDS.get(index);
    }

    public int getIndexByMaKH(int manv) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDS.size() && vitri == -1) {
            if (listDS.get(i).getMakhachhang() == manv) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(KhachHangDTO kh) {
        boolean check = khDAO.insert(kh) != 0;
        if (check) {
            this.listDS.add(kh);
        }
        return check;
    }

    public Boolean delete(KhachHangDTO kh) {
        boolean check = khDAO.delete(Integer.toString(kh.getMakhachhang())) != 0;
        if (check) {
            this.listDS = khDAO.selectAll();
        }
        return check;
    }

    public Boolean update(KhachHangDTO kh) {
        boolean check = khDAO.update(kh) != 0;
        if (check) {
            this.listDS.set(getIndexByMaKH(kh.getMakhachhang()), kh);
        }
        return check;
    }

    public ArrayList<KhachHangDTO> search(String text, String type) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhachHangDTO i : this.listDS) {
                    if (Integer.toString(i.getMakhachhang()).toLowerCase().contains(text) || i.getHoten().toLowerCase().contains(text) || i.getSodienthoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã loại" -> {
                for (KhachHangDTO i : this.listDS) {
                    if (Integer.toString(i.getMakhachhang()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khách hàng" -> {
                for (KhachHangDTO i : this.listDS) {
                    if (i.getHoten().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (KhachHangDTO i : this.listDS) {
                    if (i.getSodienthoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Trạng thái" -> {
                for (KhachHangDTO i : this.listDS) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String validateKhachHang(String hoTen, String soDienThoai, String diaChi, String trangThai, int currentID) {
        if (Validation.isEmpty(hoTen) || Validation.isEmpty(diaChi) || Validation.isEmpty(soDienThoai)) {
            return "Vui lòng điền đầy đủ thông tin";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }

        if (!Validation.isValidPhone(String.valueOf(soDienThoai))) {
            return "Số điện thoại không đúng định dạng.";
        }

        if (KhachHangDAO.getInstance().isSoDienThoaiUnique(soDienThoai, currentID)) {
            return "Số điện thoại đã được sử dụng!";
        }

        return "valid";
    }
}
