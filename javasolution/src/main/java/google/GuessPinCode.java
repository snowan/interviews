package google;

import java.util.HashMap;
import java.util.Map;

/**
 * Guess pin code
 *
 * Given 2 strings pin and guess. Write a function to provide a hint that indicates how digits in guess match the pin. Use:
 * * - to indicate a number in the correct possition.
 * o - to indicate that a number is present in the pin code but in a different possition.
 * _ - to indicate that there's no such number in the pin code.
 *
 * Example 1:
 *
 * Input: pin = "1432", guess = "1246"
 * Output: "*oo_"
 * Explanation:
 * 1 is in the correct position so `*`
 * 2 and 4 are present in the pin code but in different positions thus `oo`
 * There's no 6 in the pin code thus `_`
 * Example 2:
 *
 * Input: pin = "1234", guess = "1234"
 * Output: "****"
 * Example 3:
 *
 * Input: pin = "1234", guess = "5678"
 * Output: "____"
 *
 * Example 4:
 *
 * Input: pin = "1224", guess = "5242"
 * Output: "_*oo"
 *
 * Example 5:
 *
 * Input: pin = "1234", guess = "1224"
 * Output: "**_*"
 *
 * Example 6:
 *
 * Input: pin = "2124", guess = "1224"
 * Output: "oo**"
 * Constraints:
 *
 * pin.length == input.length
 * The input strings contain only digits 0-9.
 */
public class GuessPinCode {
  /**
   * Solution: key point is to solve those exactly matching.
   * two pass,
   * one pass, record exactly matching into result, and map to track others counts for pin string.
   * second pass, to track number for guess is in count map or not.
   *
   * TC: O(n)
   * SC: O(n)
   */
  public static String guessPinCode(String pin, String guess) {
    int len = pin.length();
    Map<Character, Integer> countMap = new HashMap<>();
    char[] res = new char[len];
    for (int i = 0; i < len; i++) {
      char currP = pin.charAt(i);
      char currG = guess.charAt(i);
      if (currG == currP) {
        res[i] = '*';
        continue;
      }
      countMap.put(currP, countMap.getOrDefault(currP, 0) + 1);
    }
    for (int i = 0; i < len; i++) {
      if (res[i] == '*') continue;
      char currG = guess.charAt(i);
      int currCount = countMap.getOrDefault(currG, 0);
      res[i] = currCount> 0 ? 'o' : '_';
      if (currCount == 1) countMap.remove(currG);
    }
    System.out.println(String.valueOf(res));
    return String.valueOf(res);
  }

  public static void main(String[] args) {
    GuessPinCode.guessPinCode("1432", "1246");
    GuessPinCode.guessPinCode("1234", "1234");
    GuessPinCode.guessPinCode("1432", "5678");
    GuessPinCode.guessPinCode("1224", "5242");
    GuessPinCode.guessPinCode("1234", "1224");
    GuessPinCode.guessPinCode("2124", "1224");
    GuessPinCode.guessPinCode("1122", "1222");
  }
}
