package org.example.answer1.util;

public class PrintMenu {
    public void printMenu() {
        System.out.println(
                "――――― Welcome To The Online Bank ――――――\n" +
                        "✎ 1. Create A New Account\n" +
                        "✎ 2. Find All Account\n" +
                        "✎ 3. Find Account By Id\n" +
                        "✎ 4. Update Account\n" +
                        "✎ 5. Delete Account\n" +
                        "✎ 6. Exit PrintMenu"
        );
    }

    public void printUpdateMenu() {
        System.out.println(
                "――――― Welcome To The Transaction Page ――――――\n" +
                        "✎ 1. Deposit\n" +
                        "✎ 2. Withdraw\n" +
                        "✎ 3. Exit PrintMenu"
        );
    }

    public void printAccountTypeMenu() {
        System.out.print(
                "――――― Enter the desired number to select the type of account ――――――\n" +
                        "✎ 1. LOAN\n" +
                        "✎ 2. CURRENT\n" +
                        "✎ 3. SHORT_TERM\n" +
                        "✎ 4. LONG_TERM: "
        );
    }
}
