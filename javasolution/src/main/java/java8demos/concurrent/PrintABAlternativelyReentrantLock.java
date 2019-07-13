package java8demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create two threads (A, B), print A and B alternatively. A-B-A-B-A-B...
 *
 */
public class PrintABAlternativelyReentrantLock {
  private int num;
  private ReentrantLock lock = new ReentrantLock();
  private Condition aCondition = lock.newCondition();
  private Condition bCondition = lock.newCondition();
  private volatile boolean isStartA = true;
  private volatile boolean isStartB = false;
  public PrintABAlternativelyReentrantLock(int num) {
    this.num = num;
  }

  class PrintA implements Runnable {
    @Override
    public void run() {
      lock.lock();
      for (int i = 0; i < num; i++) {
        while (!isStartA) {
          try {
            aCondition.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        isStartA = false;
        isStartB = true;
        System.out.println("Thread A-" + i);
        bCondition.signal();
      }
      lock.unlock();
    }
  }

  class PrintB implements Runnable {
    @Override
    public void run() {
      lock.lock();
      for (int i = 0; i < num; i++) {
        while (!isStartB) {
          try {
            bCondition.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        isStartA = true;
        isStartB = false;
        System.out.println("Thread B-" + i);
        aCondition.signal();
      }
      lock.unlock();
    }
  }

  public void start() {
    new Thread(new PrintA()).start();
    new Thread(new PrintB()).start();
  }

  public void execute() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new PrintA());
    executorService.submit(new PrintB());
    executorService.shutdown();
    while (!executorService.isTerminated()) {}
    System.out.println("Threads Terminated");

  }

  public static void main(String[] args) throws InterruptedException {
    PrintABAlternativelyReentrantLock test = new PrintABAlternativelyReentrantLock(4);
//    test.start();
    test.execute();
  }
}
