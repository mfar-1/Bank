package org.example;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    static int balance = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void getMoney() {
        System.out.println("Umumiy balances: " + balance);
        int num = Input.num("0-Back.\nQancha to'ldirasz: ");
        if (num == 0) {
            return;
        }
        balance += num;
        DB.list.add("qabul qilish " + num + new Date());
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }
    public void withdrawMoney() {
        System.out.println("Qancha pul yechib olmoqchisz: ");
        int sum = Input.num("0-Back.\nMiqdorini iriting: ");
        if (sum == 0) {
            return;
        }
        Thread thread = new Thread(() ->{
           DB.list.add("olib tashlash " + sum + new Date());
           while (true){
               if (balance < sum){
                   lock.lock();
                   try {
                       condition.await();
                   }catch (InterruptedException e){
                       throw new RuntimeException(e);
                   }
                   lock.unlock();
               }else {
                   break;
               }
           }

           lock.lock();
           balance -= sum;
            System.out.println(sum + "yechildi.");
            lock.unlock();
        });
        thread.start();
    }
    public void showBalance(){
        System.out.println("Umumiy balances: " + balance);
        System.out.println("---------------------------------------");
    }
}
