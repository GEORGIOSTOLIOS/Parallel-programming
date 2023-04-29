import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        for (int i = 0; i < 2000; i++){
            BankAccount account = new BankAccount(0);
            accounts.add(account);
        }
        ReadThread readThread = new ReadThread(accounts);
        WriteThread writeThread = new WriteThread(accounts);

        readThread.start();
        writeThread.start();

        readThread.join();
        writeThread.join();
    }

    public static class ReadThread extends Thread {
        private ArrayList<BankAccount> accounts;

        public ReadThread(ArrayList<BankAccount> accounts) {
            this.accounts = accounts;
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                int index = (int) (Math.random() * accounts.size());
                accounts.get(index).getLock().lock();
                try {
                    accounts.get(index).getBalance();
                    delay(5);
                }
                finally {
                    accounts.get(index).getLock().unlock();
                }



            }
        }
    }

    public static class WriteThread extends Thread {
        private ArrayList<BankAccount> accounts;

        public WriteThread(ArrayList<BankAccount> accounts) {
            this.accounts = accounts;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                int index = (int) (Math.random() * accounts.size());
                accounts.get(index).getLock().lock();
                try {
                    accounts.get(index).deposit(100);
                } finally {
                    delay(100);
                    accounts.get(index).getLock().unlock();
                }
                delay(2000);
            }
        }


    }

    public static void delay(int d) {
        try {
            Thread.sleep((int) (d));
        } catch (InterruptedException e) {
        }
    }
}
