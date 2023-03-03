package Servers.TransactionService;

import Helper.MySQLException;
import Tables.TransactionsTable.Transaction;
import Tables.TransactionsTable.TransactionsTableMethods;
import Tables.TransactionsTable.TransactionsTableInterface;

public class TransactionService implements TransactionServiceInterface {

    TransactionsTableInterface transactionsTable = new TransactionsTableMethods();

    @Override
    public boolean addTransaction(Transaction transaction) throws MySQLException {
        return transactionsTable.insert(transaction);
    }
}
