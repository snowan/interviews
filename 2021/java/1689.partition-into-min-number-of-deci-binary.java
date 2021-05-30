class Solution {
    public int minPartitions(String n) {
        int res = 0;
        for (int i = 0; i < n.length(); i++) {
            res = Math.max(res, n.charAt(i) - '0');
            if (res == 9) return res;
        }
        return res;
    }
}
