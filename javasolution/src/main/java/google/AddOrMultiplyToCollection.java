package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddOrMultiplyToCollection {
  private final List<Integer> values;
  private final List<Integer> divisors;
  private int addition;
  private int factor;
  private int setToZero = -1;
  public AddOrMultiplyToCollection() {
    values = new ArrayList<>();
    divisors = new ArrayList<>();
    addition = 0;
    factor = 1;
  }
  public void append(int val) {
    values.add(val - addition);
    divisors.add(factor);
  }

  public int get(int index) {
    if (index < 0 || index >= values.size()) throw new IllegalArgumentException("Index is not valid");
    if (index <= setToZero) return addition;
    return values.get(index) * (factor / divisors.get(index)) + addition;
  }

  public void addToAll(int val) {
    addition += val;
  }

  public void multiplyToAll(int val) {
    if (val == 0) {
      addition = 0;
      factor = 1;
      setToZero = values.size() - 1;
    } else {
      addition *= val;
      factor *= val;
    }
  }

  public static void main(String[] args) {
    AddOrMultiplyToCollection test = new AddOrMultiplyToCollection();
    test.append(1);
    test.append(2);
    test.append(3);
    System.out.println("------------------------");
    System.out.println(Arrays.toString(test.values.toArray()));
    System.out.println(test.get(2));
    test.addToAll(1);
    test.multiplyToAll(4);
    System.out.println("------------------------");
    System.out.println(Arrays.toString(test.values.toArray()));
    System.out.println(test.get(2));
    test.addToAll(4);
    test.append(4);
    test.multiplyToAll(2);
    System.out.println("------------------------");
    System.out.println(Arrays.toString(test.values.toArray()));
    System.out.println(test.get(3));
    test.multiplyToAll(0);
    test.append(8);
    System.out.println("------------------------");
    System.out.println(Arrays.toString(test.values.toArray()));
    System.out.println(test.get(4));
    System.out.println(test.get(2));
  }
}
