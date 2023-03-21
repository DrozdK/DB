package Servers.UserServer;

import Helper.MySQLException;
import Tables.AccountTable.Account;
import Tables.UsersTable.User;
import Tables.AccountTable.AccountTableMethods;
import Tables.AccountTable.AccountTableInterface;
import Tables.UsersTable.UsersTableMethods;
import Tables.UsersTable.UsersTableInterface;

import java.util.List;

import static constants.Constants.moneyValue.DOUBLE_ZERO;
import static constants.Constants.moneyValue.ZERO;

public class UserService implements UserServiceInterface {

    UsersTableInterface usersTable = new UsersTableMethods();

    @Override
    public User addUser(User user) {
        User users = null;
        try {
            usersTable.insert(user);
            users = usersTable.selectByName(user.getName());
            return users;
        } catch (MySQLException e) {
            System.err.println(e.getError() + ": " + e.getError().getError());
            System.err.println(e.getCause());
        }
        return users;
    }

    @Override
    public User selectUser(int userId) throws java.lang.Exception {
        return usersTable.select(userId);
    }

    @Override
    public User selectUserByName(String userName) throws java.lang.Exception {
        return usersTable.selectByName(userName);
    }

    @Override
    public boolean updateUserAddress(User user, String address) throws java.lang.Exception {
        usersTable.update(user, address);
        return true;
    }

    @Override
    public boolean deleteUser(User user) throws Exception {
        AccountTableInterface accountTable = new AccountTableMethods();
        List<Account> userAccounts = accountTable.selectByUserId(user.getId());
        double balance = DOUBLE_ZERO;
        for (Account account : userAccounts) {
            balance = +account.getBalance();
        }
        if (balance == ZERO) {
            usersTable.delete(user.getId());
            return true;
        }
        return false;
    }
}