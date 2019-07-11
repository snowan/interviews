package java8demos.function;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExceptionsHandlingTest {

  @Test
  public void testHandleFunctionWrapper() {
    Function<Integer, Integer> function = ExceptionsHandling.handleFunctionWrapper(0, (Integer x) -> {
      throw new Exception();
    });
    assertEquals("Value should be 0.", (Integer) 0, function.apply(2));
  }

  @Test
  public void testHandleSupplierWrapper() {
    Supplier<Integer> supplier = ExceptionsHandling.handleSupplierWrapper(0, () -> {
      throw new Exception();
    });
    assertEquals("value should be 0.", (Integer) 0, supplier.get());
  }

  @Test
  public void testHandlePredicateWrapper() {
    Predicate<Integer> predicate = ExceptionsHandling.handlePredicateWrapper(false, (Integer x) -> {
      throw new Exception();
    });
    boolean expected = predicate.test(1);
    assertFalse("Value should be false", expected);
  }

  @Test
  public void testHandleRunnableWrapper() {
    Runnable runnable = ExceptionsHandling.handleRunnableWrapper("error", () -> {
      throw new Exception();
    });
    runnable.run();
  }

  @Test
  public void testHandleConsumerWrapper() {
    Consumer<Integer> consumer = ExceptionsHandling.handleConsumerWrapper("Error",
        i -> {
          throw new Exception();
        });
    consumer.accept(1);
  }
}