package BUS;

import DAO.ChiTietDatSanDAO;
import DTO.ChiTietDatSanDTO;
import java.util.ArrayList;

/**
 *
 * @author phucp
 */
public class ChiTietDatSanBUS {

    private final ChiTietDatSanDAO dsDAO = new ChiTietDatSanDAO();
    public ArrayList<ChiTietDatSanDTO> listDS = new ArrayList<>();

    public ChiTietDatSanBUS() {
        listDS = dsDAO.selectAll();
    }
}
