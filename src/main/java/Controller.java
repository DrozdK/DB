import Tables.AccountTable.Account;
import Tables.UsersTable.User;
import Servers.AccountService.AccountService;
import Servers.AccountService.AccountServiceInterface;
import Servers.UserServer.UserService;
import Servers.UserServer.UserServiceInterface;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public void getResult() throws Exception {
        UserServiceInterface userTable = new UserService();
        AccountServiceInterface accountTable = new AccountService();
        User user = new User();
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do? You can choose next operations:" + "\n"
        + "1. New user registration. Key 'addUser'" + "\n"
        + "2. Adding an account to a new user. Key 'addAccount'" + "\n"
        + "3. Top up an existing account. Key 'TopUpBalance'" + "\n"
        + "4. Withdrawing funds from an existing account. Key 'withdrawing'" + "\n"
        + "If you have registration yet, you can also choose next operation:" + "\n"
        + "1. Find information about user. Key 'showUser'" + "\n"
        + "2. Add/update information about user's address. Key 'updateAddress'" + "\n"
        + "3. Delete user. Key 'deleteUser'" + "\n"
        + "4. Delete account. Key 'deleteAccount'" + "\n"
        + "Write the operation key:");
        String key = scanner.nextLine();
        switch (key) {
            case "addUser":
                System.out.println("Enter user name:");
                String name = scanner.nextLine();
                user.setName(name);
                userTable.addUser(user);
                int id = userTable.selectUserByName(name).getId();
                System.out.println("Do you want to add some more information? Write '1' if YES or '2' if NO");
                int answer = scanner.nextInt();
                if (answer == 1) {
                    user.setId(id);
                    System.out.println("Enter address:");
                    String address = scanner.next();
                    userTable.updateUserAddress(user, address);
                    System.out.println("Thanks for using our app. User was added. Your id is " + id);
                } else {
                    System.out.println("Thanks for using our app. User was added. Your id is " + id);
                }
                break;
            case "addAccount":
                System.out.println("Enter balance:");
                double balance = scanner.nextDouble();
                account.setBalance(balance);
                System.out.println("Enter currency:");
                String currency = scanner.next();
                account.setCurrency(currency);
                System.out.println("Enter user's id:");
                int userId = scanner.nextInt();
                account.setUserId(userId);
                accountTable.createAccount(account);
                List<Account> accountId = accountTable.getAccountByUserId(userId);
                System.out.println("Success! You just created account. Now you have next accounts: " + "\n"
                        + accountId);
                break;
            case "TopUpBalance":
                System.out.println("Enter accountId:");
                int accountIdTopUp = scanner.nextInt();
                account.setId(accountIdTopUp);
                double currentBalance = accountTable.getAccountByAccountId(accountIdTopUp).getBalance();
                System.out.println("Enter a value, which you want add to the balance:");
                String money = scanner.next();
                double newBalance = currentBalance + Double.parseDouble(money);
                if (accountTable.topUpBalance(account, money, String.valueOf(newBalance))) {
                    List<Account> currentValue = Collections.singletonList(accountTable.getAccountByAccountId(accountIdTopUp));
                    System.out.println("Success! Your current balance is: " + "\n"
                    + currentValue);
                } else {
                    System.out.println("Transaction cannot be executed!");
                }
                break;
            case "withdrawing":
                System.out.println("Enter accountId:");
                int accountIdWithdraw = scanner.nextInt();
                account.setId(accountIdWithdraw);
                double currentBalance2 = accountTable.getAccountByAccountId(accountIdWithdraw).getBalance();
                System.out.println("Enter a value, which you want withdraw from the balance:");
                String moneyWithdraw = scanner.next();
                if (currentBalance2 < Double.parseDouble(moneyWithdraw)) {
                    System.out.println("Transaction cannot be executed! Your current balance is " + currentBalance2);
                } else {
                    double newBalance2 = currentBalance2 - Double.parseDouble(moneyWithdraw);
                    if (accountTable.withdrawMoneyFromAccount(account, moneyWithdraw, String.valueOf(newBalance2))) {
                        List<Account> currentValue = Collections.singletonList(accountTable.getAccountByAccountId(accountIdWithdraw));
                        System.out.println("Success! Your current balance is: " + "\n"
                                + currentValue);
                    } else {
                        System.out.println("Transaction cannot be executed!");
                    }
                }
                break;
            case "showUser":
                System.out.println("If you want to find user by id, write key '1'. If you want to find user by name, write key '2'." + "\n"
                + "Write a key:");
                String value = scanner.next();
                if ("1".equals(value)) {
                    System.out.println("Enter userId:");
                    int userID = scanner.nextInt();
                    User showUser = userTable.selectUser(userID);
                    System.out.println(showUser);
                } else if ("2".equals(value)) {
                    System.out.println("Enter name:");
                    String newUserName = scanner.next();
                    User showUser2 = userTable.selectUserByName(newUserName);
                    System.out.println(showUser2);
                }
                break;
            case "updateAddress":
                System.out.println("Enter userId");
                int userIdUpdateAddress = scanner.nextInt();
                user.setId(userIdUpdateAddress);
                System.out.println("Enter new address:");
                String newAddress = scanner.next();
                userTable.updateUserAddress(user, newAddress);
                System.out.println("Thanks for using our app. Address was updated. Your current address is " + newAddress);
                break;
            case "deleteUser":
                    System.out.println("Enter userId:");
                    int userIdDelete = scanner.nextInt();
                    user.setId(userIdDelete);
                    userTable.deleteUser(user);
                    System.out.println("Success! User was deleted");
                break;
            case "deleteAccount":
                System.out.println("Enter accountId:");
                int accountIdDelete = scanner.nextInt();
                account.setId(accountIdDelete);
                accountTable.deleteAccountByAccountId(accountIdDelete);
                System.out.println("Success! Account was deleted");
                break;
            default:
                System.out.println("Unknown key! Try again");
                getResult();
        }
    }
}
