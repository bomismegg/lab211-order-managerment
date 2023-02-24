package dataservice;

import java.util.List;

/**
 *
 * @author Minh Tri
 */
public interface IDatabaseService {

    List<String> loadData();

    boolean saveData(List<String> entityStringList);

    boolean insert(String entityString);

    boolean update(String entityString);

    boolean delete(String id);
}
