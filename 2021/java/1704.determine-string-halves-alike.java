
class Solution {
   // easy, straightforward
   // two pointers, l from left -> right, r from right -> left
   // count left and right vowels 
   // compare counts 
    public boolean halvesAreAlike(String s) {
        Set<Character> set =  Set.of('a', 'e', 'i','u','o', 'A', 'E', 'I','U','O');
        int a = 0;
        int b = 0;
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            a += set.contains(s.charAt(l++)) ? 1 : 0;
            b += set.contains(s.charAt(r--)) ? 1 : 0;
        }
        return a == b;
    }
}
