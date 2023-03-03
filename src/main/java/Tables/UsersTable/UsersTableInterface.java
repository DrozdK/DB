package Tables.UsersTable;

import Helper.MySQLException;
import Tables.CRUDOperable;

public interface UsersTableInterface extends CRUDOperable<User, Integer> {

    User selectByName(String name) throws MySQLException;

    void update(User user, String address) throws MySQLException;

}
