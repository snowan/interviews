package google;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/348160
 *
 * List with setAll API
 *
 * All methods should work in O(1) time.
 *
 * example:
 * CustomList list = new CustomList(3);
 * list.get(0); // returns 0
 * list.get(1); // returns 0
 * list.get(2); // returns 0
 * list.setAll(5);
 * list.get(0); // returns 5
 * list.get(1); // returns 5
 * list.get(2); // returns 5
 * list.set(1, 10);
 * list.get(0); // returns 5
 * list.get(1); // returns 10
 * list.get(2); // returns 5
 */
public class CustomListSetAll {
  /**
   * Constructs a list with the specified length. All elements are initially 0s.
   */
  private Map<Integer, Customer> customerMap;
  private int currVersion;
  private Integer setAllVal;
  private int size;
  public CustomListSetAll(int n) {
    this.currVersion = 0;
    this.setAllVal = null;
    customerMap = new HashMap<>();
    this.size= n;
  }

  /**
   * Replaces the element at the specified position in this list with the specified value.
   */
  public void set(int index, int value) {
    if (index < 0 || index > size) throw new IllegalArgumentException("Invalid index on set");
    Customer customer = customerMap.getOrDefault(index, new Customer(index, value, currVersion));
    customer.val = value;
    customer.version = currVersion + 1;
    customerMap.put(index, customer);
  }

  /**
   * Returns the element at the specified position in this list.
   */
  public int get(int index) {
    if (index < 0 || index > size) throw new IllegalArgumentException("Invalid index on get");
    boolean exist = customerMap.containsKey(index);
    if (!exist && setAllVal == null) {
      return 0;
//      throw new NoSuchElementException("Value not exist for index : " + index);
    }
    if (!exist) return setAllVal;
    Customer cust = customerMap.get(index);
    return cust.version == currVersion + 1 ? cust.val : setAllVal;
  }

  /**
   * Replaces all elements in this list with the specified value.
   */
  public void setAll(int value) {
    setAllVal = value;
    currVersion++;
  }

  class Customer {
    int index;
    int val;
    int version;

    public Customer(int index, int val, int version) {
      this.index = index;
      this.val = val;
      this.version = version;
    }
  }

  public static void main(String[] args) {
    CustomListSetAll customListSetAll = new CustomListSetAll(3);
    System.out.println(customListSetAll.get(0)); // returns 0
    System.out.println(customListSetAll.get(1)); // returns 0
    System.out.println(customListSetAll.get(2)); // returns 0
    customListSetAll.setAll(5);
    System.out.println(customListSetAll.get(0)); // returns 5
    System.out.println(customListSetAll.get(1)); // returns 5
    customListSetAll.set(1, 8);
    System.out.println(customListSetAll.get(1)); // return 8
    System.out.println(customListSetAll.get(2)); // returns 5
    customListSetAll.set(1, 10);
    System.out.println(customListSetAll.get(0)); // returns 5
    System.out.println(customListSetAll.get(1)); // returns 10
    customListSetAll.set(2, 15);
    System.out.println(customListSetAll.get(2)); // returns 15
  }
}
