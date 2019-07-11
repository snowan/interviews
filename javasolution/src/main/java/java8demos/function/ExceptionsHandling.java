package java8demos.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsHandling {
  private static final Logger LOG = Logger.getLogger(ExceptionsHandling.class.getSimpleName());

  public static <T, R, E extends Exception> Function<T, R> handleFunctionWrapper(
      R onError, ThrowingFunction<T, R, E> throwingFunction) {
    return input -> {
      try {
        return throwingFunction.apply(input);
      } catch (RuntimeException re) {
        throw re;
      } catch (Exception e) {
        return onError;
      }
    };
  }

  public static <T, E extends Exception> Supplier<T> handleSupplierWrapper(
      T onError, ThrowingSupplier<T, E> throwingSupplier) {
    return () -> {
      try {
        return throwingSupplier.get();
      } catch (RuntimeException re) {
        throw re;
      } catch (Exception e) {
        return onError;
      }
    };
  }

  public static <T, E extends Exception> Predicate<T> handlePredicateWrapper(
      boolean onError, ThrowingPredicate<T, E> throwingPredicate) {
    return input -> {
      try {
        return throwingPredicate.test(input);
      } catch (RuntimeException re) {
        throw re;
      } catch (Exception e) {
        return onError;
      }
    };
  }

  public static <E extends Exception> Runnable handleRunnableWrapper(
      String errMsg, ThrowingRunnable<E> throwingRunnable) {
    return () -> {
      try {
        throwingRunnable.run();
      } catch (RuntimeException re) {
        throw re;
      } catch (Throwable t) {
        LOG.log(Level.INFO, errMsg, t);
      }
    };
  }

  public static <T, E extends Exception> Consumer<T> handleConsumerWrapper(
      String errMsg, ThrowingConsumer<T, E> throwingConsumer) {
    return input -> {
      try {
        throwingConsumer.accept(input);
      } catch (RuntimeException re) {
        throw re;
      } catch (Throwable t) {
        LOG.log(Level.INFO, errMsg, t);
      }
    };
  }
}
