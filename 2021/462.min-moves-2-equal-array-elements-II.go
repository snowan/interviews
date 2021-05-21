func minMoves2(nums []int) int {
    sort.Ints(nums)
    mid, res := nums[len(nums) / 2], 0
    for _, val := range nums {
        res += int(math.Abs(float64(val - mid)))
    }
    return res
}
