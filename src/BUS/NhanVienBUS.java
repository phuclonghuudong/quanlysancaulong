package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import utils.Validation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author phucp
 */
public class NhanVienBUS {

    private final NhanVienDAO nvDAO = new NhanVienDAO();
    public ArrayList<NhanVienDTO> listNhanVien = new ArrayList<>();

    public NhanVienBUS() {
        listNhanVien = nvDAO.selectAll();
    }

    public ArrayList<NhanVienDTO> getAll() {
        return this.listNhanVien;
    }

    public NhanVienDTO getByIndex(int index) {
        return this.listNhanVien.get(index);
    }

    public int getNhanVienByMaNV(int manv) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNhanVien.size() && vitri == -1) {
            if (listNhanVien.get(i).getManhanvien() == manv) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(NhanVienDTO kh) {
        boolean check = nvDAO.insert(kh) != 0;
        if (check) {
            this.listNhanVien.add(kh);
        }
        return check;
    }

    public Boolean delete(NhanVienDTO kh) {
        boolean check = nvDAO.delete(Integer.toString(kh.getManhanvien())) != 0;
        if (check) {
            this.listNhanVien = nvDAO.selectAll();
        }
        return check;
    }

    public Boolean update(NhanVienDTO kh) {
        boolean check = nvDAO.update(kh) != 0;
        if (check) {
            this.listNhanVien.set(getNhanVienByMaNV(kh.getManhanvien()), kh);
        }
        return check;
    }

    public NhanVienDTO loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }
        if (!Validation.isEmail(email)) {
            return null;
        }
        if (!NhanVienDAO.getInstance().selectByEmailExist(email)) {
            return null;
        }
        String hashedPassword = Validation.hashPassword(password);

        NhanVienDTO user = NhanVienDAO.getInstance().getUserByEmailAndPassword(email, hashedPassword);
        if (user == null) {
            return null;
        }
        if (user.getTrangthai() == 0) {
            return null;
        }
        if (!"ADMIN".equals(user.getVaitro()) && !"NHANVIEN".equals(user.getVaitro())) {
            return null;
        }
        return user;
    }

    public String registerUser(NhanVienDTO user) throws ParseException {
        if (Validation.isEmpty(user.getHoten())
                || Validation.isEmpty(user.getEmail())
                || Validation.isEmpty(user.getMatkhau())
                || Validation.isEmpty(user.getSodienthoai())) {
            return "Vui lòng nhập đầy đủ thông tin bắt buộc.";
        }

        if (!Validation.isEmail(user.getEmail())) {
            return "Email không đúng định dạng.";
        }
        if (!Validation.isValidPhone(String.valueOf(user.getSodienthoai()))) {
            return "Số điện thoại không đúng định dạng.";
        }

        if (NhanVienDAO.getInstance().selectByEmailExist(user.getEmail())) {
            return "Email đã tồn tại.";
        }
        if (NhanVienDAO.getInstance().selectByPhoneExist(String.valueOf(user.getSodienthoai()))) {
            return "Số điện thoại đã tồn tại.";
        }

        String hashedPassword = Validation.hashPassword(user.getMatkhau());
        user.setMatkhau(hashedPassword);

        user.setVaitro("USER");
        user.setTrangthai(2); // CHƯA XÁC NHẬN

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date defaultDate = sdf.parse("01/01/2000");
        user.setNgaysinh(defaultDate);

        int inserted = NhanVienDAO.getInstance().insert(user);
        return inserted > 0 ? "Đăng ký thành công! Vui lòng chờ xác nhận từ hệ thống." : "Đăng ký thất bại!";
    }

    public String updatePhone(NhanVienDTO nvDTO, String phone) {
        if (Validation.isEmpty(phone) || phone.length() != 10) {
            return "Số điện thoại không được để trống và có đủ 10 số";
        }
        if (!Validation.isValidPhone(String.valueOf(phone))) {
            return "Số điện thoại không đúng định dạng.";
        }
        boolean checkPhone = nvDAO.isPhoneNumberUnique(phone, nvDTO.getManhanvien());
        if (checkPhone) {
            return "Số điện thoại đã được sử dụng.";
        }
        nvDTO.setSodienthoai(phone);
        int result = NhanVienDAO.getInstance().update(nvDTO);
        return result > 0 ? "success" : "Cập nhật thất bại";
    }

    public String updateEmail(NhanVienDTO nvDTO, String email) {
        if (Validation.isEmpty(email) || !Validation.isEmail(email)) {
            return "Email không được để trống hoặc không đúng định dạng";
        }
        boolean checkEmail = nvDAO.isEmailUnique(email, nvDTO.getManhanvien());
        if (checkEmail) {
            return "Email đã được sử dụng.";
        }
        nvDTO.setEmail(email);
        int result = NhanVienDAO.getInstance().update(nvDTO);
        return result > 0 ? "success" : "Cập nhật thất bại";
    }

    public String updateUsername(NhanVienDTO nvDTO, String username) {
        if (Validation.isEmpty(username)) {
            return "Họ tên không được để trống";
        }
        nvDTO.setHoten(username);
        int result = NhanVienDAO.getInstance().update(nvDTO);
        return result > 0 ? "success" : "Cập nhật thất bại";
    }

    public String updatePassword(NhanVienDTO nvDTO, String currentPass, String newPass, String confirmPass) {
        if (Validation.isEmpty(currentPass)) {
            return "Mật khẩu hiện tại không được rỗng";
        }
        if (Validation.isEmpty(newPass) || newPass.length() < 6) {
            return "Mật khẩu mới không được rỗng và có ít nhất 6 ký tự";
        }
        if (Validation.isEmpty(confirmPass)) {
            return "Mật khẩu nhập lại không được rỗng";
        }
        if (!newPass.equals(confirmPass)) {
            return "Mật khẩu nhập lại không khớp với mật khẩu mới";
        }
        if (!Validation.hashPassword(currentPass).equals(nvDTO.getMatkhau())) {
            return "Mật khẩu hiện tại không đúng";
        }

        nvDTO.setMatkhau(Validation.hashPassword(newPass));
        int result = NhanVienDAO.getInstance().update(nvDTO);
        return result > 0 ? "success" : "Cập nhật thất bại";
    }

    public String validateNhanVien(String hoTen, String email, String soDienThoai, String vaiTro, String gioiTinh, Date ngaySinh, String trangThai, int currentID) {
        if (Validation.isEmpty(hoTen) || Validation.isEmpty(email)
                || Validation.isEmpty(soDienThoai) || Validation.isEmpty(vaiTro)) {
            return "Vui lòng điền đầy đủ thông tin";
        }

        if (trangThai == null || trangThai.isEmpty()) {
            return "Vui lòng chọn trạng thái";
        }
        if (ngaySinh == null) {
            return "Vui lòng chọn ngày sinh";
        }

        if (!Validation.isEmail(String.valueOf(email))) {
            return "Email không đúng định dạng.";
        }
        if (!Validation.isValidPhone(String.valueOf(soDienThoai))) {
            return "Số điện thoại không đúng định dạng.";
        }

        if (NhanVienDAO.getInstance().isEmailUnique(email, currentID)) {
            return "Email đã được sử dụng!";
        }
        if (NhanVienDAO.getInstance().isPhoneNumberUnique(soDienThoai, currentID)) {
            return "Số điện thoại đã được sử dụng!";
        }

        return "valid";
    }

    public ArrayList<NhanVienDTO> search(String text, String type) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (Integer.toString(i.getManhanvien()).toLowerCase().contains(text)
                            || i.getHoten().toLowerCase().contains(text)
                            || i.getEmail().toLowerCase().contains(text)
                            || i.getVaitro().toLowerCase().contains(text)
                            || i.getSodienthoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhân viên" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (Integer.toString(i.getManhanvien()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Họ tên" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (i.getHoten().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Email" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (i.getEmail().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (i.getSodienthoai().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Vai trò" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    if (i.getVaitro().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Trạng thái" -> {
                for (NhanVienDTO i : this.listNhanVien) {
                    String trangThai = i.getTrangthai() == 1 ? "Hoạt động" : "Dừng";
                    if (trangThai.toLowerCase().contains(text.toLowerCase())) {
                        result.add(i);
                    }
                }
            }

        }

        return result;
    }

}
