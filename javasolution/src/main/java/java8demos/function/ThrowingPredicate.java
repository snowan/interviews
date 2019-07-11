package java8demos.function;

@FunctionalInterface
public interface ThrowingPredicate<T, E extends Exception> {
  boolean test(T t) throws E;
}
