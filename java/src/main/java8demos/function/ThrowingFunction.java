package main.java8demos.function;

/**
 * Function interface for single argument functions that may throw checked exceptions.
 *
 * @param <T> The type of the input of the function
 * @param <R> The return type of the function
 * @param <E> Exception type
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
  R apply(T input) throws E;
}
