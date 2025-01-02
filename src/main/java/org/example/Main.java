package org.example;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        while (true) {
            System.out.println("""
                    1 - Kirish
                    2 - Chiqish
                    3 - Balansni korish.
                    """);
            switch (Input.num("Choose: ")){
                case 1:
                    account.getMoney();
                    break;
                case 2:
                    account.withdrawMoney();
                    break;
                case 3:
                    account.showBalance();
                    break;
                default:
                    System.out.println("Iltimos, 1, 2, v ya 3 tugmasini tanlang!");
            }
        }
    }
}