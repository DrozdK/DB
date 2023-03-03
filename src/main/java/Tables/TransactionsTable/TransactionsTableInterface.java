package Tables.TransactionsTable;

import Helper.MySQLException;

public interface TransactionsTableInterface {

    boolean insert(Transaction transaction) throws MySQLException;
}
