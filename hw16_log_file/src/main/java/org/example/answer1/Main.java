package org.example.answer1;

import org.example.answer1.entity.Account;
import org.example.answer1.entity.Card;
import org.example.answer1.entity.enumeration.AccountType;
import org.example.answer1.service.AccountService;
import org.example.answer1.service.CardService;
import org.example.answer1.util.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RandomNumber randomNumber = new RandomNumber();
    private static final SetAccountType setAccountType = new SetAccountType();
    private static final SetCreatedTime setCreateTime = new SetCreatedTime();
    private static final SetExpirationDate setExpiration = new SetExpirationDate();
    private static final PrintMenu PRINT_MENU = new PrintMenu();

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        AccountService accountService = new AccountService();
        CardService cardService = new CardService();

        boolean flag = true;

        while (flag) {
            PRINT_MENU.printMenu();
            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        create(accountService, cardService);
                        break;
                    case 2:
                        findAll(accountService);
                        break;
                    case 3:
                        findById(accountService);
                        break;
                    case 4:
                        update(accountService);
                        break;
                    case 5:
                        delete(accountService, cardService);
                        break;
                    case 6:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public static void create(AccountService accountService, CardService cardService) {
        String accountNumber = randomNumber.accountNumber();

        Long createDate = setCreateTime.setTime().getTime();

        AccountType accountType = setAccountType.accountType();

        Long balance = 5_000L;

        String cardNumber = randomNumber.cardNumber();

        Integer cvv2 = randomNumber.cvv2Number();

        Long expirationDate = setExpiration.ExpirationDate().getTime();

        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCvv2(cvv2);
        card.setExpirationDate(expirationDate);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setCreateDate(createDate);
        account.setBalance(balance);
        account.setCard(card);

        try {
            cardService.create(card);
            accountService.create(account);
            System.out.println("Account created successfully");
        } catch (Exception e) {
            System.out.println("Account not created :(");
        }
    }

    public static void findAll(AccountService accountService) {
        List<Account> accounts = accountService.findAll();
        if (accounts != null) {
            System.out.println("________________________________________________________________________________________________________________________________________");
            System.out.println("| Id  |  AccountNumber  |        CreateDate       | AccountType |  Balance       |    CardNumber    |  Cvv2  |     ExpirationDate      |");
            System.out.println("________________________________________________________________________________________________________________________________________");
            for (Account account : accounts) {
                System.out.printf("|  %-3s|  %-15s| %-24s|  %-11s|  %-14s| %-17s|  %-6s| %-24s|%n", account.getId(), account.getAccountNumber(), new Timestamp(account.getCreateDate()), account.getAccountType(), account.getBalance(), account.getCard().getCardNumber(), account.getCard().getCvv2(), new Timestamp(account.getCard().getExpirationDate()));
                System.out.println("________________________________________________________________________________________________________________________________________");
            }
        } else {
            System.out.println("you have no account :(");
        }
    }

    public static void findById(AccountService accountService) {
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the desired ID: ");
                long id = scanner.nextLong();

                List<Account> accounts = new ArrayList<>();
                accounts.add(accountService.findById(id));
                if (accounts.get(0) != null) {
                    System.out.println("________________________________________________________________________________________________________________________________________");
                    System.out.println("| Id  |  AccountNumber  |        CreateDate       | AccountType |  Balance       |    CardNumber    |  Cvv2  |     ExpirationDate      |");
                    System.out.println("________________________________________________________________________________________________________________________________________");
                    for (Account account : accounts) {
                        System.out.printf("|  %-3s|  %-15s| %-24s|  %-11s|  %-14s| %-17s|  %-6s| %-24s|%n",
                                account.getId(),
                                account.getAccountNumber(),
                                new Timestamp(account.getCreateDate()),
                                account.getAccountType(),
                                account.getBalance(),
                                account.getCard().getCardNumber(),
                                account.getCard().getCvv2(),
                                new Timestamp(account.getCard().getExpirationDate()));
                        System.out.println("________________________________________________________________________________________________________________________________________");
                    }
                } else {
                    System.out.println("An account with the desired ID was not found");
                }
                flag = false;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public static void update(AccountService accountService) {
        boolean flag = true;

        while (flag) {
            PRINT_MENU.printUpdateMenu();
            System.out.print("Enter an option: ");

            try {
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        deposit(accountService);
                        break;
                    case 2:
                        withdraw(accountService);
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public static void delete(AccountService accountService, CardService cardService) {
        findAll(accountService);

        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the ID of the account you want to delete: ");
                long id = scanner.nextLong();

                try {
                    Account account = accountService.findById(id);
                    Card card = cardService.findById(account.getCard().getId());

                    if (card != null) {
                        accountService.delete(account);
                        cardService.delete(card);
                    }
                    else {
                        System.out.println("Invalid id");
                    }
                } catch (NullPointerException e) {
                    System.out.println("Invalid id");
                }
                flag = false;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public static void deposit(AccountService accountService) {
        findAll(accountService);

        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the account ID to which you want to deposit: ");
                long id = scanner.nextLong();

                try {
                    Account account = accountService.findById(id);
                    List<Account> accounts = new ArrayList<>();
                    accounts.add(account);

                    if (accounts.get(0) != null) {

                        System.out.print("Enter the desired amount: ");
                        long money = scanner.nextLong();
                        if (money > 0) {
                            long balance = accountService.deposit(account.getBalance(), money);
                            account.setBalance(balance);
                            accountService.create(account);
                        }
                        else {
                            System.out.println("Amount not be negative :(");
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Invalid id");
                }
                flag = false;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public static void withdraw(AccountService accountService) {
        findAll(accountService);

        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the account ID to which you want to withdraw: ");
                long id = scanner.nextLong();

                try {
                    Account account = accountService.findById(id);
                    List<Account> accounts = new ArrayList<>();
                    accounts.add(account);

                    if (accounts.get(0) != null) {

                        System.out.print("Enter the desired amount: ");
                        long money = scanner.nextLong();

                        if (money > 0) {
                            long balance = accountService.withdraw(account.getBalance(), money);
                            account.setBalance(balance);
                            accountService.create(account);
                        }
                        else {
                            System.out.println("Amount not be negative :(");
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Invalid id");
                }
                flag = false;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }
}
