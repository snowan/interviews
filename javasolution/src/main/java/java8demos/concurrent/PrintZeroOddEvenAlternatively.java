package java8demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrintZeroOddEvenAlternatively {
  private int num;
  private Semaphore zero;
  private Semaphore odd;
  private Semaphore even;
  public PrintZeroOddEvenAlternatively(int num) {
    this.num = num;
    zero = new Semaphore(1);
    odd = new Semaphore(0);
    even = new Semaphore(0);
  }

  class PrintZero implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < num; i++) {
//        System.out.println("Current Thread - " + Thread.currentThread().getName());
        try {
          zero.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.print("0");
        if (i % 2 != 0) even.release();
        else odd.release();
      }
    }
  }

  class PrintOdd implements Runnable {
    @Override
    public void run() {
      for (int i = 1; i <= num; i += 2) {
//        System.out.println("Current Thread - " + Thread.currentThread().getName());
        try {
          odd.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.print(i);
        zero.release();
      }
    }
  }

  class PrintEven implements Runnable {
    @Override
    public void run() {
      for (int i = 2; i <= num; i += 2) {
//        System.out.println("Current Thread - " + Thread.currentThread().getName());
        try {
          even.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(i);
        zero.release();
      }
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
    PrintZeroOddEvenAlternatively test = new PrintZeroOddEvenAlternatively(5);
    test.printNumber();
  }
}
