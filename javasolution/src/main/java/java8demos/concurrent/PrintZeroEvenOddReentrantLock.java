package java8demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create 3 thread, print zero, odd, even number alternatively, for example: 0102030405...
 * thread 0 - print 0
 * thread 1 - print odd number
 * thread 2 - print even number
 */
public class PrintZeroEvenOddReentrantLock {
  private int num;

  private ReentrantLock lock = new ReentrantLock();
  private Condition zero = lock.newCondition();
  private Condition odd = lock.newCondition();
  private Condition even = lock.newCondition();
  private volatile boolean isStartZero = true;
  private volatile boolean isStartOdd = false;
  private volatile boolean isStartEven = false;
  public PrintZeroEvenOddReentrantLock(int num) {
    this.num = num;
  }
  class PrintZero implements Runnable {
    @Override
    public void run() {
      lock.lock();
      for (int i = 1; i <= num; i++) {
        try {
          while (!isStartZero) zero.await();
          System.out.println("Thread zero - " + 0);
          isStartZero = false;
          if (i % 2 == 0) {
            isStartEven = true;
            even.signal();
          } else {
            isStartOdd = true;
            odd.signal();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    }
  }
  class PrintOdd implements Runnable {
    @Override
    public void run() {
      lock.lock();
      for (int i = 1; i <= num; i += 2) {
        try {
          while (!isStartOdd) odd.await();
          System.out.println("Thread odd - " + i);
          isStartOdd = false;
          isStartZero = true;
          zero.signal();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    }
  }
  class PrintEven implements Runnable {
    @Override
    public void run() {
      lock.lock();
      for (int i = 2; i <= num; i += 2) {
        try {
          while (!isStartEven) even.await();
          System.out.println("Thread even - " + i);
          isStartEven = false;
          isStartZero = true;
          zero.signal();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    }
  }

  private void printNumber() {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.execute(new PrintZero());
    executorService.execute(new PrintOdd());
    executorService.execute(new PrintEven());
    executorService.shutdown();
    while (!executorService.isTerminated()) {}
  }

  public static void main(String[] args) {
    PrintZeroEvenOddReentrantLock test = new PrintZeroEvenOddReentrantLock(5);
    test.printNumber();
  }
}
