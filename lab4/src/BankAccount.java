import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance;
    private ReentrantLock lock =  new ReentrantLock();

    public BankAccount(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
