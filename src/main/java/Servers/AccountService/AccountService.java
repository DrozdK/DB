package Servers.AccountService;

import Servers.TransactionService.TransactionService;
import Servers.TransactionService.TransactionServiceInterface;
import Helper.MySQLException;
import Tables.AccountTable.Account;
import Tables.TransactionsTable.Transaction;
import Tables.AccountTable.AccountTableMethods;
import Tables.AccountTable.AccountTableInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.length.LENGTH;
import static constants.Constants.moneyValue.*;

public class AccountService implements AccountServiceInterface {

    AccountTableInterface accountTable = new AccountTableMethods();

    @Override
    public boolean createAccount(Account account) throws MySQLException {
        List<Account> userAccounts = accountTable.selectByUserId(account.getUserId());
        Set<String> currencies = new HashSet<>();
        for (Account userAccount : userAccounts) {
            currencies.add(userAccount.getCurrency());
        }
        if (!currencies.contains(account.getCurrency().toUpperCase())) {
            accountTable.insert(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean topUpBalance(Account account, String amount, String newBalance) {
        if (amount.isEmpty() || !checkAmount(amount) || !checkLengthOfString(amount)) {
            return false;
        }
        double moneyValue = Double.parseDouble(amount);
        if (!checkAcceptable(moneyValue)) {
            return false;
        }
        if (account.getBalance() + moneyValue >= MAX_BALANCE) {
            return false;
        }
        String strValueToTransaction = String.valueOf(moneyValue);
        try {
            TransactionServiceInterface transactionTable = new TransactionService();
            transactionTable.addTransaction(
                    Transaction.builder()
                            .amount(strValueToTransaction)
                            .accountId(account.getId())
                            .build());
            account.setBalance(Double.parseDouble(newBalance));
            accountTable.update(account);
            return true;
        } catch (MySQLException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getError());
        }
        return false;
    }

    public boolean withdrawMoneyFromAccount(Account account, String amount, String newBalance) {
        if (amount.isEmpty() || !checkAmount(amount) || !checkLengthOfString(amount)) {
            return false;
        }
        double moneyValue = Double.parseDouble(amount);
        if (!checkAcceptable(moneyValue)) {
            return false;
        }
        String strValueToTransaction = "-" + moneyValue;
        try {
            TransactionServiceInterface transactionService = new TransactionService();
            transactionService.addTransaction(
                    Transaction.builder()
                            .amount(strValueToTransaction)
                            .accountId(account.getId())
                            .build());
            account.setBalance(Double.parseDouble(newBalance));
            accountTable.update(account);
            return true;
        } catch (MySQLException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getError());
        }
        return false;
    }

    private boolean checkAmount(String amount) {
        Pattern pattern = Pattern.compile("\\d*\\.?\\d{0,3}");
        Matcher matcher = pattern.matcher(amount);
        return matcher.matches();
    }

    private boolean checkLengthOfString(String amount) {
        return (amount.length() <= LENGTH);
    }

    private boolean checkAcceptable(Double amount) {
        return (amount > ZERO && amount <= MAX_TRANSACTION_VALUE);
    }

    public List<Account> getAccountByUserId(int userId) throws MySQLException {
        return accountTable.selectByUserId(userId);
    }

    public Account getAccountByAccountId(int accountId) throws MySQLException {
        return accountTable.select(accountId);
    }

    public boolean deleteAccountByAccountId(int accountId) throws MySQLException {
        Account account = getAccountByAccountId(accountId);
        if (account.getBalance() == ZERO) {
            accountTable.delete(accountId);
            return true;
        }
        return false;
    }
}