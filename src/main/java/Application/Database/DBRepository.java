package Application.Database;

public interface DBRepository<T> {

    Iterable<T> findAll();

    T findById(String id);

    T save(T object);
}
