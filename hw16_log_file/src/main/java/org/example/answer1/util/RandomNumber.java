package org.example.answer1.util;


public class RandomNumber {
    public String accountNumber() {
        while (true) {
            long number = (long) (Math.random() * 10000000000000L);
            if (String.valueOf(number).length() == 13)
                return String.valueOf(number);
        }
    }

    public String cardNumber() {
        while (true) {
            long number = (long) (Math.random() * 10000000000000000L);
            if (String.valueOf(number).length() == 16)
                return String.valueOf(number);
        }
    }

    public Integer cvv2Number() {
        return (int) (Math.random() * 10000);
    }
}
