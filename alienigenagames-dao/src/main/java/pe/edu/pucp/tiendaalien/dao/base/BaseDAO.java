package pe.edu.pucp.tiendaalien.dao.base;

public interface BaseDAO <T, ID> {
    T loadById(ID id);
    T save(T t);
    T update(T t);
    void remove(T t);
}
