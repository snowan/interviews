class Solution {
    // https://leetcode.com/problems/stamping-the-sequence/discuss/201546/12ms-Java-Solution-Beats-100
    /**
    The idea is the same as the most upvoted post. Think reversely!
    Let's take an example to illustrate.
    Stamp = "abc", Target = "ababcbc`

    Target = ab***bc
    Target = ab*****
    Target = *******
    We will go through the whole Target string to find if there exists any substring equals to Stamp. And then replace the whole substring with *. You can see in the step 1, we replace substring abc with ***. Then we keep doing the same thing. In the step 2, you will find we replace substring *bc to ***. * can match to any character because * will be override by the next stamp. Finally we get the result and output the reversed result. When # of stars equals to target.length(), we will return the result. If during one scan, we are not able to replace even one substring with *, directly return empty array.

    Comments for two helper functions:
    - canReplace(char[] T, int p, char[] S) is used to check if any substring from Target is existing to be replaced with *.
    - doReplace(char[] T, int p, int len, int count) is used to replace the substring with * and return the total number of * we have now.
    */
    public int[] movesToStamp(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[T.length];
        int stars = 0;
        
        while (stars < T.length) {
            boolean doneReplace = false;
            for (int i = 0; i <= T.length - S.length; i++) {
                if (!visited[i] && canReplace(T, i, S)) {
                    stars = doReplace(T, i, S.length, stars);
                    doneReplace = true;
                    visited[i] = true;
                    res.add(i);
                    if (stars == T.length) {
                        break;
                    }
                }
            }
            
            if (!doneReplace) {
                return new int[0];
            }
        }
        
        int[] resArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(res.size() - i - 1);
        }
        return resArray;
    }
    
    private boolean canReplace(char[] T, int p, char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (T[i + p] != '*' && T[i + p] != S[i]) {
                return false;
            }
        }
        return true;
    }
    
    private int doReplace(char[] T, int p, int len, int count) {
        for (int i = 0; i < len; i++) {
            if (T[i + p] != '*') {
                T[i + p] = '*';
                count++;
            }
        }
        return count;
    
    }
}
