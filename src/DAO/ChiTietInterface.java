package DAO;

import java.util.ArrayList;

/**
 *
 * @author phucp
 */
public interface ChiTietInterface<T> {

    public int insert(ArrayList<T> t);

    public int delete(String t);

    public int update(ArrayList<T> t, String pk);

    public ArrayList<T> selectAll(String t);
}
