func longestStrChain(words []string) int {
    // sort array by word length
    sort.Slice(words, func(i, j int) bool {
        return len(words[i]) < len(words[j])
    })
    
    countMap, max := make(map[string]int, len(words)), 1
    
    for _, word := range words {
        countMap[word] = 1
        for i := 0; i < len(word); i++ {
            prevWord := word[:i] + word[i + 1:]
            preCount, ok := countMap[prevWord]
            if ok && preCount + 1 > countMap[word] {
                countMap[word] = countMap[prevWord] + 1
            }
        }
        if max < countMap[word] {
            max = countMap[word]
        }
    }
    
    
    return max
}
