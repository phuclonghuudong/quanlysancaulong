package BUS;

import DAO.DatSanDAO;
import DTO.DatSanDTO;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author phucp
 */
public class DatSanBUS {

    private final DatSanDAO dsDAO = new DatSanDAO();
    public ArrayList<DatSanDTO> listDS = new ArrayList<>();

    public DatSanBUS() {
        listDS = dsDAO.selectAll();
    }

    public ArrayList<DatSanDTO> getAll() {
        return this.listDS;
    }

    public DatSanDTO getByIndex(int index) {
        return this.listDS.get(index);
    }

    public int getIndexByMaDS(int ID) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDS.size() && vitri == -1) {
            if (listDS.get(i).getMadatsan() == ID) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(DatSanDTO kh) {
        boolean check = dsDAO.insert(kh) != 0;
        if (check) {
            this.listDS.add(kh);
        }
        return check;
    }

    public Boolean delete(DatSanDTO kh) {
        boolean check = dsDAO.delete(Integer.toString(kh.getMadatsan())) != 0;
        if (check) {
            this.listDS = dsDAO.selectAll();
        }
        return check;
    }

    public Boolean update(DatSanDTO kh) {
        boolean check = dsDAO.update(kh) != 0;
        if (check) {
            this.listDS.set(getIndexByMaDS(kh.getMadatsan()), kh);
        }
        return check;
    }

    public String datSanBUS(DatSanDTO dto) throws ParseException {

        if (dto.getMakhachhang() <= 0) {
            return "Khách hàng không hợp lệ.";
        }

        if (dto.getMasan() <= 0) {
            return "Sân không hợp lệ.";
        }

        if (dto.getManhanvien() <= 0) {
            return "Nhân viên không hợp lệ.";
        }

        if (dto.getCheckin() == null || dto.getCheckout() == null) {
            return "Giờ checkin hoặc checkout không được để trống.";
        }

        if (!dto.getCheckout().isAfter(dto.getCheckin())) {
            return "Giờ checkout phải sau giờ checkin.";
        }

        if (dto.getGiasan() <= 0) {
            return "Giá sân không hợp lệ.";
        }

        if (dto.getTongtien() < 0) {
            return "Tổng tiền không hợp lệ.";
        }

//        if (dto.getThanhtoan() == null || dto.getThanhtoan().trim().isEmpty()) {
//            return "Phương thức thanh toán không được để trống.";
//        }
        if (dto.getTrangthai() < 0 || dto.getTrangthai() > 2) { // giả sử: 0 - Đang sử dụng, 1 - Hoàn tất, 2 - Hủy
            return "Trạng thái không hợp lệ.";
        }
        ArrayList<DatSanDTO> danhSachDaDat = dsDAO.getBySan(dto.getMasan());
        for (DatSanDTO ds : danhSachDaDat) {
            if (ds.getTrangthai() == 0 || ds.getTrangthai() == 1) {
                if ((dto.getCheckin().isBefore(ds.getCheckout())) && (dto.getCheckout().isAfter(ds.getCheckin()))) {
                    return "Khung giờ bị trùng với lịch đã đặt.";
                }
            }
        }

        int inserted = dsDAO.insert(dto);
        return inserted > 0 ? "Đặt sân thành công!" : "Đăng sân thất bại!";

    }
}
