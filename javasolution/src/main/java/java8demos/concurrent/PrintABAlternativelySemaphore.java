package java8demos.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Create two threads (A, B), print A and B alternatively. A-B-A-B-A-B...
 *
 */
public class PrintABAlternativelySemaphore {
  private int num;
  private Semaphore s1;
  private Semaphore s2;
  public PrintABAlternativelySemaphore(int num) {
    this.num = num;
    s1 = new Semaphore(0);
    s2 = new Semaphore(1);
  }

  class PrintA implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < num; i++) {
        try {
          s2.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread A-" + i);
        s1.release();
      }
    }
  }

  class PrintB implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < num; i++) {
        try {
          s1.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread B-" + i);
        s2.release();
      }
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
    PrintABAlternativelySemaphore test = new PrintABAlternativelySemaphore(4);
//    test.start();
    test.execute();
  }
}
