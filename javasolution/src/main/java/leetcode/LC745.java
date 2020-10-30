/**

Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
 */
class WordFilter {
    TrieNode trie;
    public WordFilter(String[] words) {
        trie = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // insert word 
            for (int j = 0; j <= word.length(); j++) {
                insert(word.substring(j) + '#' + word, i);
            }
        }
    }
    
    private void insert(String word, int index) {
        TrieNode curr = trie;
        // System.out.println(word + ", index= " + index);
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
            curr.index = index;
        }
    }
    
    private int search(String prefix) {
        TrieNode curr = trie;
        for (char ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) return -1;
            curr = curr.children.get(ch);
        }
        return curr.index;
    }
    public int f(String prefix, String suffix) {
        return search(suffix + '#' + prefix);
    }
}
class TrieNode {
    Map<Character, TrieNode> children;
    int index;
    public TrieNode() {
        children = new HashMap<>();
        index = 0;
    }
}



/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */