package enums;

import lombok.Getter;

@Getter
public enum Command {
    //User Table
    USERS_TABLE_SELECT_BY_USERID("SELECT * FROM Users WHERE userId = "),
    USERS_TABLE_SELECT_BY_NAME("SELECT * FROM Users WHERE name = '"),
    USERS_TABLE_INSERT("INSERT INTO Users (name, address) VALUES (?, ?)"),
    USERS_TABLE_UPDATE("UPDATE Users SET address = ? WHERE userId = ?"),
    USERS_TABLE_DELETE_BY_USERID("DELETE FROM Users WHERE userId = ?"),

    //Account Table
    ACCOUNTS_TABLE_SELECT_BY_ACCOUNTID("SELECT * FROM Accounts WHERE accountId = "),
    ACCOUNTS_TABLE_SELECT_BY_USERID("SELECT * FROM Accounts WHERE userId = "),
    ACCOUNTS_TABLE_INSERT("INSERT INTO Accounts (userId, balance, currency) VALUES (?, ?, ?)"),
    ACCOUNTS_TABLE_UPDATE("UPDATE Accounts SET balance = ? WHERE accountId = ?"),
    ACCOUNTS_TABLE_DELETE_BY_ACCOUNTID("DELETE FROM Accounts WHERE accountId = ?"),

    //Transaction Table
    TRANSACTIONS_TABLE_INSERT("INSERT INTO Transactions (accountId, amount) VALUES (?, ?)");

    private final String command;

    Command(String command) {
        this.command = command;
    }
}
