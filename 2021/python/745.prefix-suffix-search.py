# Create a Trie. Each TrieNode has idx dictionary to store the index of the word.
class TrieNode:
    def __init__(self):
        self.nodes = defaultdict(TrieNode)
        self.isWordEnd = False
        self.idx = {}
    
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
	# Insert the word into trie
    def insert(self, word, idx):
        curr = self.root
        for char in word:
            curr.idx[char] = idx
            curr = curr.nodes[char]
        curr.isWordEnd = True
     
    # Search for the prefix and keep updating the idx  
    def prefixSearch(self, prefix):
        curr = self.root
        prefixIdx = -1
        for char in prefix:
            if char not in curr.nodes:
                break
            else:
                prefixIdx = curr.idx[char]
                curr = curr.nodes[char]
        return prefixIdx
        
class WordFilter:
    def __init__(self, words: List[str]):
        self.wordMap = {}
        self.trie = Trie()
        
		# For each word create string using the suffix
		# apple => #apple, e#apple, le#apple, ple#apple, pple#apple, apple#apple 
        for i in range(len(words)):
            word = words[i]
            self.trie.insert('#'+word, i)
            for j in range(1, len(word)+1):
                newWord = word[-j:] + '#' + word
                self.trie.insert(newWord, i)
            
        
    def f(self, prefix: str, suffix: str) -> int:
		# generate the prefix to search
        searchKey = suffix + '#' + prefix
        return self.trie.prefixSearch(searchKey)
