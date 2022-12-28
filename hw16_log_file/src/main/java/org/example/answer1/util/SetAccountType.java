package org.example.answer1.util;

import org.example.answer1.entity.enumeration.AccountType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SetAccountType {
    private static final Scanner scanner = new Scanner(System.in);
    public AccountType accountType() {
        PrintMenu printMenu = new PrintMenu();
        AccountType accountType;

        while (true) {
            printMenu.printAccountTypeMenu();

            try {
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        accountType = AccountType.LOAN;
                        break;
                    case 2:
                        accountType = AccountType.CURRENT;
                        break;
                    case 3:
                        accountType = AccountType.SHORT_TERM;
                        break;
                    case 4:
                        accountType = AccountType.LONG_TERM;
                        break;
                    default:
                        continue;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
            return accountType;
        }
    }
}
