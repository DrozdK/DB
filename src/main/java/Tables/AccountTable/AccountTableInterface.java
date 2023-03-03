package Tables.AccountTable;

import Helper.MySQLException;
import Tables.CRUDOperable;

import java.util.List;

public interface AccountTableInterface extends CRUDOperable<Account, Integer> {

    void insert(Account account) throws MySQLException;

    List<Account> selectByUserId(int userId) throws MySQLException;

    void update(Account account) throws MySQLException;
}
