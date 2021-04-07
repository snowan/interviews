class Solution {
    public int minOperations(int n) {
        if (n % 2 == 1) {
            n /= 2;
            return n * (n + 1);
        }
        n /= 2;
        return n * n;
    }
}

// if n is odd, n = 5
// eg. 1,3,5,7,9
// mid: 5
// take 4 from 9, add to 1
// take 2 from 7, add yo 3,
// operation: 4 + 2= 6

// if n is even, n = 6
// e.g 1,3,5,7,9,11
// mid: (5 + 7) / 2 = 6
// take 5 from 11, add to 1
// take 3 from 9, add to 3
// take 1 from 7, add to 5
// operations: 5+3+1=9
