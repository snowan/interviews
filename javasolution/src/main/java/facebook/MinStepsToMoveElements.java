package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of numbers, remove the elements according to following conditions
 * remove an element arr[i] if and only if arr[i-1] < arr[i]
 * You might get the resulting array after removing the elements. Repeat the process until array remains unchanged.
 * Return the number of steps in which the array remains unchanged.
 *
 * Eg:
 * [6,3,1,8,9,4,3,2,8,9]
 *
 * after 1st iteration we get
 *
 * Step 1: [6,3,1,4,3,2] ---> [8,9] is removed since 1<8<9 && 2<8<9
 * Step 2: [6,3,1,3,2] ---> [4] is removed since 1<4
 * Step 3: [6,3,1,2] ---> [3] is removed since 1<3
 * Step 4: [6,3,1] ---> [2] is removed since 1<2
 *
 * Answer: 4 (Total no. of steps are 4)
 */
public class MinStepsToMoveElements {
  public static int minStepsMoveElem(int[] arr) {
    if (arr == null || arr.length == 0) return 0;
    int minSteps = 0;
    List<Integer> origin = Arrays.stream(arr).boxed().collect(Collectors.toList());
    List<Integer> next = new ArrayList<>();
    int size = 0;
    while (true) {
      System.out.println("Arrays step: " + Arrays.toString(origin.toArray()));
      next.add(origin.get(0));
      for (int i = 1; i < origin.size(); i++) {
        if (origin.get(i - 1) >= origin.get(i)) {
          next.add(origin.get(i));
        }
      }
      size = next.size();
      if (size == origin.size()) {
        System.out.println("min steps - " + minSteps);
        return minSteps;
      }
      origin = next;
      next = new ArrayList<>();
      minSteps++;
    }
  }

  public static void main(String[] args) {
    MinStepsToMoveElements.minStepsMoveElem(new int[]{6,3,1,8,9,4,3,2,8,9});
    MinStepsToMoveElements.minStepsMoveElem(new int[]{6,5,4,1,2,5,4,6,8,11,9,12,13,2});
  }
}
