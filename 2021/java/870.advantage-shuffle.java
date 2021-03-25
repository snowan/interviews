/**
idea is to use TreeMap, sorted by key. key: element in A, value: frequent for each element
to match element B, for each B, binary find upperBound, if found, frequent - 1, if not found, assign smallest value.

*/
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int a : A) map.put(a, map.getOrDefault(a, 0) + 1);
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            Integer upperBound = map.higherKey(B[i]);
            if (upperBound == null) upperBound = map.firstKey();
            map.put(upperBound, map.get(upperBound) - 1);
            if (map.get(upperBound) == 0) map.remove(upperBound);
            res[i] = upperBound;
        }
        return res;
    }
}
