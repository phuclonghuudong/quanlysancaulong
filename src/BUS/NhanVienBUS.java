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

    public NhanVienDTO loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
//            return "Vui lòng nhập đầy đủ thông tin bắt buộc.";
            return null;
        }

        if (!Validation.isEmail(email)) {
//            return "Email không hợp lệ - không đúng định dạng.";
            return null;
        }

        if (!NhanVienDAO.getInstance().selectByEmailExist(email)) {
//            return "Email không tồn tại.";
            return null;
        }

        String hashedPassword = Validation.hashPassword(password);

        NhanVienDTO user = NhanVienDAO.getInstance().getUserByEmailAndPassword(email, hashedPassword);
        if (user == null) {
//            return "Sai email hoặc mật khẩu.";
            return null;
        }

        if (user.getTrangthai() == 0) {
//            return "Tài khoản của bạn đã bị khóa.";
            return null;
        }
        if (!"ADMIN".equals(user.getVaitro())) {
//            return "Tài khoản của bạn đã bị khóa.";
            return null;
        }

//        return "Đăng nhập thành công! Xin chào " + user.getHoten();
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
}
