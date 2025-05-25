package BUS;

import DAO.SanPhamDAO;
import DTO.LoaiHangDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Validation;

/**
 *
 * @author phucp
 */
public class SanPhamBUS {

    private final SanPhamDAO spDAO = new SanPhamDAO();
    public ArrayList<SanPhamDTO> listDS = new ArrayList<>();
    LoaiHangBUS loaiSanPhamBUS = new LoaiHangBUS();

    public SanPhamBUS() {
        listDS = spDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        return this.listDS;
    }

    public ArrayList<SanPhamDTO> getAllLoaiSanPham() {
        return spDAO.selectSanPhamJoinLoaiSanPham();
    }

    public ArrayList<SanPhamDTO> getAllLoaiSanPhamHoatDong() {
        return spDAO.selectSanPhamJoinLoaiSanPhamHoatDong();
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listDS.get(index);
    }

    public SanPhamDTO getByMaSP(String maSP) {
        try {
            int ma = Integer.parseInt(maSP);
            for (SanPhamDTO sp : listDS) {
                if (sp.getMasanpham() == ma) {
                    return sp;
                }
            }
        } catch (NumberFormatException e) {
//            System.err.println("Lỗi: mã sản phẩm không hợp lệ -> " + maSP);
        }
        return null;
    }

    public int getIndexByMaDV(int ID) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDS.size() && vitri == -1) {
            if (listDS.get(i).getMasanpham() == ID) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(SanPhamDTO kh) {
        boolean check = spDAO.insert(kh) != 0;
        if (check) {
            this.listDS.add(kh);
        }
        return check;
    }

    public Boolean delete(SanPhamDTO kh) {
        boolean check = spDAO.delete(Integer.toString(kh.getMasanpham())) != 0;
        if (check) {
            this.listDS = spDAO.selectSanPhamJoinLoaiSanPham();
        }
        return check;
    }

    public Boolean update(SanPhamDTO kh) {
        boolean check = spDAO.update(kh) != 0;
        if (check) {
            this.listDS.set(getIndexByMaDV(kh.getMasanpham()), kh);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> getByMaLoaiSanPham(int makv) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.listDS) {
            if (i.getLoaisanpham() == makv) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<SanPhamDTO> search(String text, String type) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();

        Map<Integer, String> loaiSanPhamMap = new HashMap<>();
        List<LoaiHangDTO> dsLoai = loaiSanPhamBUS.getAll();
        if (dsLoai != null) {
            dsLoai.forEach(loai -> {
                loaiSanPhamMap.put(loai.getMaloaihang(), loai.getTenloaihang());
            });
        }

        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (SanPhamDTO i : this.listDS) {
                    String tenLoai = loaiSanPhamMap.get(i.getLoaisanpham());
                    boolean match = Integer.toString(i.getMasanpham()).toLowerCase().contains(text)
                            || i.getTensanpham().toLowerCase().contains(text)
                            || (tenLoai != null && tenLoai.toLowerCase().contains(text));
                    if (match) {
                        i.setTenloaisanpham(tenLoai);
                        result.add(i);
                    }
                }
            }
            case "Mã sản phẩm" -> {
                for (SanPhamDTO i : this.listDS) {
                    if (Integer.toString(i.getMasanpham()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên sản phẩm" -> {
                for (SanPhamDTO i : this.listDS) {
                    if (i.getTensanpham().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Loại sản phẩm" -> {
                for (SanPhamDTO i : this.listDS) {
                    String tenLoai = loaiSanPhamMap.get(i.getLoaisanpham());
                    if (tenLoai != null && tenLoai.toLowerCase().contains(text)) {
                        i.setTenloaisanpham(tenLoai);
                        result.add(i);
                    }
                }
            }

            case "Trạng thái" -> {
                for (SanPhamDTO i : this.listDS) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động"
                            : i.getTrangthai() == 3 ? "Hết hàng"
                            : i.getTrangthai() == 2 ? "Bảo trì" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String validateSanPham(int loaiSP, String tenSP, double giaSP, int soLuong, String donVi, String ghiChu, String trangThai, int currentID) {
        if (Validation.isEmpty(tenSP) || Validation.isEmpty(donVi)) {
            return "Vui lòng nhập đầy đủ thông tin!";
        }

        if (soLuong <= 0) {
            return "Số lượng phải lớn hơn 0";
        }
        if (giaSP <= 0) {
            return "Giá sản phẩm phải lớn hơn 0";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }

        if (SanPhamDAO.getInstance().isNameUnique(tenSP, currentID)) {
            return "Tên sản phẩm đã được sử dụng!";
        }

        return "valid";
    }
}
