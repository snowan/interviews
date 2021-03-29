class Solution {
    public String originalDigits(String s) {
        if (s == null || s.length() == 0) return "";
        int[] count = new int[10]; // 0 - 9
        for (char ch : s.toCharArray()) {
            switch(ch) {
                case 'z': {
                    count[0]++;
                    break;
                } case 'w': {
                    count[2]++;
                    break;
                } case 'u': {
                    count[4]++;
                    break;
                } case 'x': {
                    count[6]++;
                    break;
                } case 'g': {
                    count[8]++;
                    break;
                } case 'h': {
                    count[3]++;
                    break;
                } case 'f': {
                    count[5]++;
                    break;
                } case 's': {
                    count[7]++;
                    break;
                } case 'o': {
                    count[1]++;
                    break;
                } case 'i': {
                    count[9]++;
                    break;
                }
            }
        }
        // 3 = 3-8
        count[3] -= count[8];
        // 5 = 5-4
        count[5] -=count[4];
        // 7 = 7-6
        count[7] -= count[6];
        // 9 = 9-5-6-8
        count[9] = count[9] - count[5] - count[6] - count[8];
        // 1 = 1-0-2-4
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            while (count[i]-- > 0) sb.append(i);
        }
        return sb.toString();
        
    }
}
// zero - z = 0
// two - w = 2
// three - h = 3 - 8
// four - u = 4
// five - f - u = 5 - 4
// six - x = 6
// seven - s - x = 7 - 6
// eight - g = 8
// nine - i = 9 - 5 - 6 - 8 
// one - o  = 1 - 0 - 2 - 4
