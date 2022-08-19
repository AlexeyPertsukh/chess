package com.company.view;

import java.util.Scanner;

public class ConsoleReader implements Reader{
    private final Scanner sc = new Scanner(System.in);

    @Override
    public String next() {
        return sc.next();
    }
}
