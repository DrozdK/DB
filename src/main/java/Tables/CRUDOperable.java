package Tables;

import Helper.MySQLException;

public interface CRUDOperable<T, N> {

    void insert(T t) throws MySQLException;

    T select(N value) throws MySQLException;

    void delete(N value) throws MySQLException;

}
