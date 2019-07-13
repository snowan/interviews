package leetcode.concurrency.LC1115;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. Print FooBar Alternately
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will
 * call bar(). Modify the given program to output "foobar" n times.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
 * "foobar" is being output 1 time.
 * Example 2:
 *
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 */
public class LC1115PrintFooBarAlternatively {
  private int n;

  private Semaphore s1;
  private Semaphore s2;
  public LC1115PrintFooBarAlternatively(int n) {
    this.n = n;
    s1 = new Semaphore(0);
    s2 = new Semaphore(1);
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      s2.acquire();
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
      s1.release();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      s1.acquire();
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
      s2.release();
    }
  }

  /**
   * Solution #2. ReentrantLock + condition
   *
   */
  private int num;
  private ReentrantLock lock = new ReentrantLock();
  private Condition aCondition = lock.newCondition();
  private Condition bCondition = lock.newCondition();
  private volatile boolean isStartFoo = true;
  private volatile boolean isStartBar = false;
  public LC1115PrintFooBarAlternatively(int num, String msg) {
    this.num = num;
  }

  public void foo1(Runnable printFoo) throws InterruptedException {
    lock.lock();
    for (int i = 0; i < n; i++) {
      while (!isStartFoo) aCondition.await();
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
      isStartFoo = false;
      isStartBar = true;
      bCondition.signal();
    }
    lock.unlock();
  }

  public void bar1(Runnable printBar) throws InterruptedException {
    lock.lock();
    for (int i = 0; i < n; i++) {
      while (!isStartBar) bCondition.await();
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
      isStartFoo = true;
      isStartBar = false;
      aCondition.signal();
    }
    lock.unlock();
  }
}
