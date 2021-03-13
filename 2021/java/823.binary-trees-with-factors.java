/**
     * sort the array
     *
     * and use HashMap to record each Integer, and the number of trees with that Integer as root
     * (1) each integer arr[i] will always have one tree with only itself
     * (2) if arr[i] has divisor (arr[j] (j < i) in the map, and if arr[i]/arr[j] also in the map
     *     then a can be the root of its left subtree, and arr[i]/arr[j] can be the root of its right subtree;
     *     the number of such tree is map.get(a) * map.get(arr[i]/arr[j])
     * (3) res to sum each arr[i] 
     */
    public int numFactoredBinaryTrees(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int mod = (int)1e9 + 7;
        int len = arr.length;
        long res = 0;
        Arrays.sort(arr);
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(arr[i], 1L);
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    map.put(arr[i], 
                            (map.get(arr[i]) + map.get(arr[j]) * map.getOrDefault(arr[i] / arr[j], 0L)) % mod);
                }
                
            }
            res = (res + map.get(arr[i])) % mod;
        }
        return (int) res;
    }
}
