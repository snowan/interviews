package google;

import java.util.*;

public class RecommendFriend {
  // Solution:
  // 1. build a Map, key is id, value is friends id set.
  // 2. scan map, compare the non friends id, friends set, track which id has the most common friends.
  // 3. return value.
  // for example:
  // friends = [[1,2],[1,3],[2,4],[3,4],[2,5]], id = 1
  // 1. build a friend graph map:
  //    {
  //     1, [2, 3],
  //     2, [1, 4, 5],
  //     3, [1, 4],
  //     4, [2, 3],
  //     5, [2]
  //    }
  // 2. scan map, skip id = 1, and friends(2, 3) with id=1.
  // 3. id = 4, has 2 common friends with id=1,[2,3]
  // 4. return 4
  //
  public static int recommentFriend(int[][] friends, int id) {
    if (friends == null || friends.length == 0) return -1;
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int[] f : friends) {
      graph.computeIfAbsent(f[0], c -> new HashSet<>()).add(f[1]);
      graph.computeIfAbsent(f[1], c -> new HashSet<>()).add(f[0]);
    }
    if (!graph.containsKey(id)) return -1;
    int max = -1;
    int res = -1;
    for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
      Set<Integer> comm = graph.get(id);
      if (comm.contains(entry.getKey()) || entry.getKey() == id) {
        continue;
      }
      comm.retainAll(entry.getValue());
      if (comm.size() > max) {
        max = comm.size();
        res = entry.getKey();
      }
    }
    System.out.println("Most common friends: " + res);
    return res;
  }

  public static void main(String[] args) {
    int[][] friends = {{1,2},{1,3},{2,4},{3,4},{2,5}};
    RecommendFriend.recommentFriend(friends, 1);
  }
}
