package leetcode.concurrency.LC1116;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the thread is given a printNumber method to output an integer. Modify the given program to output
 * the series 010203040506... where the length of the series must be 2n.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls
 * even(), and the last one calls odd(). "0102" is the correct output.
 * Example 2:
 *
 * Input: n = 5
 * Output: "0102030405"
 */
public class LC1116PrintZeroEvenOdd {
  private int n;
  private Semaphore zero;
  private Semaphore odd;
  private Semaphore even;
  public LC1116PrintZeroEvenOdd(int n) {
    this.n = n;
    zero = new Semaphore(1);
    odd = new Semaphore(0);
    even = new Semaphore(0);
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      zero.acquire();
      printNumber.accept(0);
      if (i % 2 == 0) odd.release();
      else even.release();
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for (int i = 2; i <= n; i += 2) {
      even.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i <= n; i += 2) {
      odd.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }

  /**
   * Solution #2: ReentrantLock + Conditions
   *
   */
  private int num;
  private ReentrantLock lock = new ReentrantLock();
  private Condition zero1 = lock.newCondition();
  private Condition odd1 = lock.newCondition();
  private Condition even1 = lock.newCondition();
  private volatile boolean isStartZero = true;
  private volatile boolean isStartOdd = false;
  private volatile boolean isStartEven = false;
  public LC1116PrintZeroEvenOdd(int num, String msg) {
    this.num = num;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero1(IntConsumer printNumber) throws InterruptedException {
    lock.lock();
    for (int i = 1; i <= n; i++) {
      while (!isStartZero) zero1.await();
      printNumber.accept(0);
      isStartZero = false;
      if (i % 2 == 0) {
        isStartOdd = false;
        isStartEven = true;
        even1.signal();
      } else {
        isStartOdd = true;
        isStartEven = false;
        odd1.signal();
      }
    }
    lock.unlock();
  }

  public void even1(IntConsumer printNumber) throws InterruptedException {
    lock.lock();
    for (int i = 2; i <= n; i += 2) {
      while (!isStartEven) even1.await();
      printNumber.accept(i);
      isStartZero = true;
      isStartEven = false;
      zero1.signal();
    }
    lock.unlock();
  }

  public void odd1(IntConsumer printNumber) throws InterruptedException {
    lock.lock();
    for (int i = 1; i <= n; i += 2) {
      while (!isStartOdd) odd1.await();
      printNumber.accept(i);
      isStartZero = true;
      isStartOdd = false;
      zero1.signal();
    }
    lock.unlock();
  }
}
