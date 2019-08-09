package google;

/**
 * Number of Valid String
 *
 * Given an int n, output number of valid strings of length n consisting of letters A, B and C.
 * any continuous three letters containing all three letters A, B, C make the whole string invalid.
 * For example, BACCA is not a valid string because of the first three characters, but CCCACCAABBB is a valid string.
 *
 * Example 1:
 *
 * Input: n = 0
 * Output: 0
 * Example 2:
 *
 * Input: n = 1
 * Output: 3
 * Explanation: A, B, C
 * Example 3:
 *
 * Input: n = 2
 * Output: 9
 * Explanation: AA, AB, AC, BA, BB, BC, CA, CB, CC
 * Example 4:
 *
 * Input: n = 3
 * Output: 21
 * Explanation: 3^3 - 6 (ABC, ACB, BAC, BCA, CBA, CAB)
 * Example 5:
 *
 * Input: n = 4
 * Output: 51
 *
 * Challenge:
 * O(1) space solution.
 */
public class NumOfValidStr {
  /**
   * Solution: valid string rules, any continuous3 letters cannot containing 3 different letters(A,B,C).
   * so key point here is count ending with 2 same letters or 2 different letters,
   * if ending with 2 same letters, then we can add 3 different letters to the end when len + 1.
   * if ending with 2 diff letters, then we only can add 2 different letters to the end when len + 1.
   *
   * if we list some examples, we can get some patterns from counts ending with 2 different letters
   * or ending with 2 same letters.
   *
   * since here we require ending with 2 letters, so we will start with n = 2.
   *
   * for example:
   * n = 2,
   * ending with 2 same, (AA, BB, CC), - count same: 3
   * ending with 2 diff, (AB, BA, AC, CA, BC, CB), - count diff: 6
   * total = same + diff = 9
   *
   * n = 3,
   * ending with 2 same, (AAA, BBB, CCC, ABB, BAA, ACC, CAA, BCC, CBB), - count same: 9
   * ending with 2 diff, (AAB, AAC, BBA, BBC, CCA, CCB, ABA, BAB, ACA, ACB, CAC, CBC), - count diff: 12
   *
   * n = 4,
   * ending with 2 same, (AAAA, BBBB, CCCC, ABBB, BAAA, ACCC, CAAA, BCCC, CBBB, AABB, AACC, BBAA, BBCC, CCAA, CCBB, ABAA, BABB, ACAA, ACBB, CACC, CBCC) - count same: 21
   * ending with 2 diff, (....) - count diff = 30
   *
   * notice that, if we have n, and
   * sameN - number of strings with ending with 2 same letters
   * diffN - number of strings with ending with 2 diff letters
   *
   * for n+1, ending with 2 same letters, we can easily tell that
   * sameN+1 = sameN + diffN (we must add the same ending letter for prev Strings to form strings with ending 2 same)
   * diffN+1 = 2 * sameN + diffN (to make last 2 letters different, previous ending with 2 same letters, we can add 2 different letters,
   *                              for previous ending with 2 different letters, we only can add 1 different letters,
   *                              because another one will make it 3 different continuous letters, which is invalid).
   *
   * TC: O(n)
   * SC: O(n)
   */
  public static int numOfValidStrings(int n) {
    if (n == 1) return 3;
    if (n == 2) return 9;
    int[] diff = new int[n + 1];
    int[] same = new int[n + 1];
    diff[2] = 6;
    same[2] = 3;
    for (int i = 3; i <= n; i++) {
      same[i] = same[i - 1] + diff[i - 1];
      diff[i] = 2 * same[i - 1] + diff[i - 1];
    }
    return same[n] + diff[n];
  }

  /**
   * TC: O(n)
   * SC: O(1)
   */
  public static int numOfValidStrings2(int n) {
    if (n == 1) return 3;
    if (n == 2) return 9;
    int diff = 6;
    int same = 3;
    int prevSame = same;
    int prevDiff = diff;
    for (int i = 3; i <= n; i++) {
      same = prevSame + prevDiff;
      diff = 2 * prevSame + prevDiff;
      prevSame = same;
      prevDiff = diff;
    }
    return same + diff;
  }

  public static void main(String[] args) {
    System.out.println(NumOfValidStr.numOfValidStrings(1));
    System.out.println(NumOfValidStr.numOfValidStrings(2));
    System.out.println(NumOfValidStr.numOfValidStrings(3));
    System.out.println(NumOfValidStr.numOfValidStrings(4));
    System.out.println(NumOfValidStr.numOfValidStrings(5));
    System.out.println(NumOfValidStr.numOfValidStrings(6));
    System.out.println(NumOfValidStr.numOfValidStrings(7));
    System.out.println("-------------------------------------");
    System.out.println(NumOfValidStr.numOfValidStrings2(1));
    System.out.println(NumOfValidStr.numOfValidStrings2(2));
    System.out.println(NumOfValidStr.numOfValidStrings2(3));
    System.out.println(NumOfValidStr.numOfValidStrings2(4));
    System.out.println(NumOfValidStr.numOfValidStrings2(5));
    System.out.println(NumOfValidStr.numOfValidStrings2(6));
    System.out.println(NumOfValidStr.numOfValidStrings2(7));
  }
}
