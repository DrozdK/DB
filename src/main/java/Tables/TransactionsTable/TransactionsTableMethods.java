package Tables.TransactionsTable;

import Helper.MySQLException;
import Connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static enums.Errors.*;
import static enums.Commands.*;

public class TransactionsTableMethods implements TransactionsTableInterface {
    Connection connection;

    @Override
    public boolean insert(Transaction transaction) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TRANSACTIONS_TABLE_INSERT.getCommand());
            preparedStatement.setInt(1, transaction.getAccountId());
            preparedStatement.setString(2, transaction.getAmount());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }catch(SQLException e){
            throw new MySQLException(TRANSACTIONS_TABLE_ERROR_INSERT, e);
        }
    }
    }
