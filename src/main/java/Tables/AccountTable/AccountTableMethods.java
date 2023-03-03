package Tables.AccountTable;

import Helper.MySQLException;
import Connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static enums.Errors.*;
import static enums.Commands.*;

public class AccountTableMethods implements AccountTableInterface {

    Connection connection;

    @Override
    public void insert(Account account) throws MySQLException {
        try {
            connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNTS_TABLE_INSERT.getCommand());
            preparedStatement.setInt(1, account.getUserId());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setString(3, account.getCurrency().toUpperCase());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new MySQLException(ACCOUNTS_TABLE_ERROR_INSERT, e);
        }
    }

    @Override
    public Account select(Integer accountId) throws MySQLException {
        Account account = null;
        try {
            connection = new DBConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(ACCOUNTS_TABLE_SELECT_BY_ACCOUNTID.getCommand() + accountId);
            account = Account.builder()
                    .id(rs.getInt("accountId"))
                    .userId(rs.getInt("userId"))
                    .balance(rs.getDouble("balance"))
                    .currency(rs.getString("currency"))
                    .build();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new MySQLException(ACCOUNTS_TABLE_ERROR_SELECT_BY_ACCOUNTID, e);
        }
        return account;
    }

    @Override
    public List<Account> selectByUserId(int userId) throws MySQLException {
        List<Account> accounts;
        try {
            connection = new DBConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(ACCOUNTS_TABLE_SELECT_BY_USERID.getCommand() + userId);
            accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = Account.builder()
                        .id(rs.getInt("accountId"))
                        .userId(rs.getInt("userId"))
                        .balance(rs.getDouble("balance"))
                        .currency(rs.getString("currency"))
                        .build();
                accounts.add(account);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new MySQLException(ACCOUNTS_TABLE_ERROR_SELECT_BY_USERID, e);
        }
        return accounts;
    }

    @Override
    public void update(Account account) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(ACCOUNTS_TABLE_UPDATE.getCommand());
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        }catch (SQLException e) {
            throw new MySQLException(ACCOUNTS_TABLE_ERROR_UPDATE, e);
        }
    }

    @Override
    public void delete(Integer accountId) throws MySQLException {
        try{
            connection = new DBConnection().getConnection();
            PreparedStatement ps = connection.prepareStatement(ACCOUNTS_TABLE_DELETE_BY_ACCOUNTID.getCommand());
            ps.setInt(1, accountId);
            ps.executeUpdate();
            ps.close();
            connection.close();
        }catch (SQLException e) {
            throw new MySQLException(ACCOUNTS_TABLE_ERROR_DELETE_BY_ACCOUNTID, e);
        }
    }
}
