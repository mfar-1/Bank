package org.example;

import java.util.Scanner;

public interface Input {
    static int num(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
