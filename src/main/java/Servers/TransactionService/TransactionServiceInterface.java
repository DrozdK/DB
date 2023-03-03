package Servers.TransactionService;

import Helper.MySQLException;
import Tables.TransactionsTable.Transaction;

public interface TransactionServiceInterface {

    boolean addTransaction(Transaction transaction) throws MySQLException;
}
