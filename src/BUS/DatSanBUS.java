package BUS;

import DAO.DatSanDAO;
import DTO.DatSanDTO;
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

}
