package leetcode.array.LC1086;

import java.util.*;

/**
 * 1086. High Five
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 *
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.
 * The average score is calculated using integer division.
 *
 * Example 1:
 *
 * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The average of the student with id = 1 is 87.
 * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 *
 * Note:
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * The IDs of the students is between 1 to 1000
 * The score of the students is between 1 to 100
 * For each student, there are at least 5 scores
 */
public class LC1086HighFive {
  public static int[][] highFive(int[][] items) {
    int len = items.length;
    Arrays.sort(items, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
    List<int[]> list = new ArrayList<>();
    int index = 0;
    while (index < len) {
      int total = items[index][1];
      int count = 1;
      int id = items[index][0];
      index++;
      while (index < len && items[index][0] == id) {
        if (count < 5)
          total += items[index][1];
        count++;
        index++;
      }
      list.add(new int[] { id, total / 5 });
    }
    int[][] res = new int[list.size()][2];
    index = 0;
    for (int[] ints : list) res[index++] = ints;
    return res;
  }

  public static void main(String[] args) {
    LC1086HighFive.highFive(new int[][]{{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}});
  }
}
