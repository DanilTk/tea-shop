package resources.DAO;

import java.util.List;

public interface DAOFunctionality<T> {

    T add(T domain);

    List<T> getAll();

    T update(Long id, T domain);

    Long delete(Long id);

}