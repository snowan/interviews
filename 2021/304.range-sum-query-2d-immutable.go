
type NumMatrix struct {
    presum [][]int
}


func Constructor(matrix [][]int) NumMatrix {
    return NumMatrix {
        presum: buildPresum(matrix),
    }
}


func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
    return this.presum[row2 + 1][col2 + 1] - this.presum[row2 + 1][col1] - this.presum[row1][col2 + 1] + this.presum[row1][col1]
}

func buildPresum(matrix [][]int) [][]int {
    m, n := len(matrix), len(matrix[0])
    res := make([][]int, m + 1)
    for i := 0; i <= m; i++ {
        res[i] = make([]int, n + 1)
    }
    
    for r := 1; r <= m; r++ {
        for c := 1; c <= n; c++ {
            res[r][c] = res[r - 1][c] + res[r][c - 1] + matrix[r - 1][c - 1] - res[r - 1][c - 1]
        }
    }

    return res
}


/**
 * Your NumMatrix object will be instantiated and called as such:
 * obj := Constructor(matrix);
 * param_1 := obj.SumRegion(row1,col1,row2,col2);
 */
