package java8demos.LambadaExceptionHandling;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionHandleInLambdaExpression {
  private static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(
      ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
    return i -> {
      try {
        throwingConsumer.accept(i);
      } catch (Exception ex) {
        try {
          E exCast = exceptionClass.cast(ex);
          System.err.println(
              "Exception occurred : " + exCast.getMessage());
        } catch (ClassCastException ccEx) {
          throw new RuntimeException(ex);
        }
      }
    };
  }

  static void writeToFile(Integer integer) throws IOException {
    // logic to write to file which throws IOException
  }

  public static void main(String[] args) {
    List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20, 0);
//    integers.forEach(handlingConsumerWrapper(ExceptionHandleInLambdaExpression::writeToFile, IOException.class));
    integers.forEach(handlingConsumerWrapper(i -> System.out.println(50 / i), ArithmeticException.class));
  }
}
