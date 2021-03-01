class FreqStack {
    // keep track frequency of value
    Map<Integer, Integer> freq;
    // <key, value> - frequency as key, values with same frequency push into stack
    Map<Integer, Stack<Integer>> map;

    public FreqStack() {
        freq = new HashMap<>();
        map = new HashMap<>();
    }
    
    public void push(int x) {
        freq.put(x, freq.getOrDefault(x, 0) + 1);
        map.computeIfAbsent(freq.get(x), s -> new Stack<Integer>()).push(x);
    }
    
    public int pop() {
        int maxFreq = map.size();
        int top = map.get(maxFreq).pop();
        freq.put(top, freq.get(top) - 1);
        // remove empty stack
        if (map.get(maxFreq).size() == 0) {
            map.remove(maxFreq);
        }
        return top;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
