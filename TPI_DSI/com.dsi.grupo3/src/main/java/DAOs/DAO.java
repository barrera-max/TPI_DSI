package DAOs;

import java.util.List;

public interface DAO<T, ID>{

    void create(T t);

    T findById(ID id);

    void update(T t);

    void delete(ID id);

    List<T> findAll();



}
