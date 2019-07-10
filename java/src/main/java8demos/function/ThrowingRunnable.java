package main.java8demos.function;

@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
  void run() throws E;
}
