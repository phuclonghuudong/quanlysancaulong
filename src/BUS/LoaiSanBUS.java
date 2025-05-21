package BUS;

import DAO.LoaiSanDAO;
import DTO.LoaiSanDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public ArrayList<LoaiSanDTO> getAllStatus() {
        return loaiSanDAO.selectAll();
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
            this.listLoaiSan.remove(getIndexByMaDV(kh.getMaloaisan()));
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
}
