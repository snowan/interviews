class Solution {
    public void rotate(int[][] matrix) {
        // 1. first reverse up to bottom. 
        // 2. swap symmetry 
        // e.g 1 2 3                        7 8 9                     7 4 1
        //     4 5 6   -> (step 1)reverse   4 5 6   -> (step 2) swap  8 5 2
        //     7 8 9                        1 2 3                     9 6 3
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        reverse(matrix, rows);
        symmetrySwap(matrix, rows, cols);
    }
        
    private void reverse(int[][] matrix, int rows) {
        int startRow = 0;
        int endRow = rows - 1;
        while (startRow < endRow) {
            int[] tmp = matrix[startRow];
            matrix[startRow] = matrix[endRow];
            matrix[endRow] = tmp;
            startRow++;
            endRow--;
        }
    }
    
    private void symmetrySwap(int[][] matrix, int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = r + 1; c < cols; c++) {
                swap(matrix, r, c);
            }
        }
    }
    private void swap(int[][] matrix, int r, int c) {
        int tmp = matrix[r][c];
        matrix[r][c] = matrix[c][r];
        matrix[c][r] = tmp;
    }
    
}
