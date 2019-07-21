## Recommend Friends

### Problem
```
You are given a network of friends from LinkedIn or Facebook as an array.
 Each element of this array is a pair [idX, idY] which means user X and user Y are friends. 
 You also given the id of the current user. Find the person who

1. Is not your friend.
2. Has the most common friends with you.

Example 1:
Input: friends = [[1, 2], [1, 3], [2, 4], [2, 5], [3, 4]], id = 1
Output: 4

Explanation:
User 4 has 2 (2, 3) common friends with user 1.
User 5 has 1 (3) common friend with user 1.
So return 4.
```

### 题意
题意是给一个朋友网络，给一个人的ID， 然后推荐一个朋友给这个ID的人，满足两个条件， 

    1，推荐的人跟给定的人不是朋友

    2，推荐的人跟给定的人有最多的共同朋友

### 分析解答
这道题就是找二级朋友关联的题， 构建一个图， 看谁的一级朋友跟给定user Id 的一级朋友有最多的交集。 

- 构建一个朋友级联关系图，
- 然后找有最多相同一级朋友的二级朋友。

如下图：
![alt text](../.../../../../resources/img/recommend_friend.png)

**Java Code 实现：**

```java
class RecommendFriend {
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
        return res;
      }
  }
```
