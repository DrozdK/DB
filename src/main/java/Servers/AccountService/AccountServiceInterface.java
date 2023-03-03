package Servers.AccountService;

import Tables.AccountTable.Account;

import java.util.List;

public interface AccountServiceInterface {

    boolean createAccount(Account account) throws Exception;

    boolean topUpBalance(Account account, String amount, String newBalance) throws Exception;

    boolean withdrawMoneyFromAccount(Account account, String amount, String newBalance) throws Exception;

    List<Account> getAccountByUserId(int userId) throws Exception;

    Account getAccountByAccountId(int accountId) throws Exception;

    boolean deleteAccountByAccountId(int accountId)throws Exception;
}