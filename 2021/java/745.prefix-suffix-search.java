
/**
Take "apple" as an example, we will insert add "apple#apple", "pple#apple", "ple#apple", "le#apple", "e#apple", "#apple" into the Trie Tree.
If the query is: prefix = "app", suffix = "le", we can find it by querying our trie for
"le#app".
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
