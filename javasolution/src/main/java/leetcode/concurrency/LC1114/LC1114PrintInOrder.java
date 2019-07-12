package leetcode.concurrency.LC1114;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 1114. Print in Order
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call one(), thread B will call two(), and thread C will call three().
 * Design a mechanism and modify the program to ensure that two() is executed after one(),
 * and three() is executed after two().
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: "onetwothree"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls one(),
 * thread B calls two(), and thread C calls three(). "onetwothree" is the correct output.
 * Example 2:
 *
 * Input: [1,3,2]
 * Output: "onetwothree"
 * Explanation: The input [1,3,2] means thread A calls one(), thread B calls three(), and thread C calls two().
 * "onetwothree" is the correct output.
 *
 *
 * Note:
 *
 * We do not know how the threads will be scheduled in the operating system, even though the numbers in the input
 * seems to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.
 */
public class LC1114PrintInOrder {
  /**
   * A counting semaphore.  Conceptually, a semaphore maintains a set of
   * permits.  Each acquire blocks if necessary until a permit is
   * available, and then takes it.  Each release adds a permit,
   * potentially releasing a blocking acquirer.
   * However, no actual permit objects are used; the Semaphore just
   * keeps a count of the number available and acts accordingly.
   *
   * <p>Semaphores are often used to restrict the number of threads than can
   * access some (physical or logical) resource.
   */
  private Semaphore s1;
  private Semaphore s2;
  public LC1114PrintInOrder() {
    s1 = new Semaphore(0);
    s2 = new Semaphore(0);
  }

  public void first(Runnable printFirst) throws InterruptedException {
    System.out.println("first ");
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    s1.release();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    s1.acquire();
    System.out.println("second ");
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
    s2.release();
  }

  public void third(Runnable printThird) throws InterruptedException {
    s2.acquire();
    System.out.println("third ");
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
  }

  /**
   * Solution #2: using CountDownLatch
   * refer to definition of CountDownLatch:
   * <p>A  CountDownLatch is initialized with a given <em>count</em>.
   *  * The await await methods block until the current count reaches
   *  * zero due to invocations of the countDown method, after which
   *  * all waiting threads are released and any subsequent invocations of
   *  * await await return immediately.  This is a one-shot phenomenon
   *  * -- the count cannot be reset.  If you need a version that resets the
   *  * count, consider using a  CyclicBarrier.
   *  *
   *  * <p>A  CountDownLatch is a versatile synchronization tool
   *  * and can be used for a number of purposes.  A
   *  *  CountDownLatch initialized with a count of one serves as a
   *  * simple on/off latch, or gate: all threads invoking await await
   *  * wait at the gate until it is opened by a thread invoking
   *  * #countDown.  A  CountDownLatch initialized to <em>N</em>
   *  * can be used to make one thread wait until <em>N</em> threads have
   *  * completed some action, or some action has been completed N times.
   *  *
   *  * <p>A useful property of a  CountDownLatch is that it
   *  * doesn't require that threads calling  countDown wait for
   *  * the count to reach zero before proceeding, it simply prevents any
   *  * thread from proceeding past an await await until all
   *  * threads could pass.
   */
  private CountDownLatch c1;
  private CountDownLatch c2;
  public LC1114PrintInOrder(int value) {
    c1 = new CountDownLatch(1);
    c2 = new CountDownLatch(1);
  }

  public void first(String msg, Runnable printFirst) throws InterruptedException {

    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    c1.countDown();
  }

  public void second(String msg, Runnable printSecond) throws InterruptedException {
    c1.await();
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
    c2.countDown();
  }

  public void third(String msg, Runnable printThird) throws InterruptedException {
    c2.await();
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
  }
}
