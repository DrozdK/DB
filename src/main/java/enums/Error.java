package enums;

import lombok.Getter;

@Getter
public enum Error {
    //User Table Errors
    USERS_TABLE_ERROR_INSERT("Insert method"),
    USERS_TABLE_ERROR_SELECT_BY_USERID("Select by userId method"),
    USERS_TABLE_ERROR_SELECT_BY_NAME("Select by name method"),
    USERS_TABLE_ERROR_UPDATE("Update method"),
    USERS_TABLE_ERROR_DELETE_BY_USERID("Delete by userId method"),

    //Account Table Errors
    ACCOUNTS_TABLE_ERROR_INSERT("Insert method"),
    ACCOUNTS_TABLE_ERROR_SELECT_BY_ACCOUNTID("Select by accountId method"),
    ACCOUNTS_TABLE_ERROR_SELECT_BY_USERID("Select by userId method"),
    ACCOUNTS_TABLE_ERROR_UPDATE("Update method"),
    ACCOUNTS_TABLE_ERROR_DELETE_BY_ACCOUNTID("Delete by accountId method"),

    //Transaction Table Errors
    TRANSACTIONS_TABLE_ERROR_INSERT("Insert method");

    private final String error;

    Error(String error) {
        this.error = error;
    }
}
