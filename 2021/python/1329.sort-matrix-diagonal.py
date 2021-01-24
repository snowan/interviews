"""
1329. Sort the Matrix Diagonally

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

Example 1:

Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
"""
"""
Solution: intuitive solution, list of diagonal values, and sort, replace back into matrix.
the same diagonal m[r][c] has same value of r - c.

"""
def diagonalSort(mat: List[List[int]]) -> List[List[int]]:
    if not mat or len(mat) == 0 or len(mat[0]) == 0:
        return mat
    m, n = len(mat), len(mat[0])
    # dictionary to store same r-c into list
    diag_map = {}
    for r in range(m):
        for c in range(n):
            diag_map[r - c].append(mat[r][c])
    # sort each diag list
    for key in diag_map:
        diag_map[key] = sorted(diag_map[key], reverse=True)
    # replace sorted value back to matrix
    for r in range(m):
        for c in range(n):
            mat[r][c] = diag_map.get(r - c).pop()
    return mat
