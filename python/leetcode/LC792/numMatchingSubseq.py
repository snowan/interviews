import collections

class Solution:
    def numMatchingSubseq(self, S: str, words: List[str]) -> int:
        word_dict = collections.defaultdict(list)
        for word in words:
            word_dict[word[0]].append(iter(word[1:]))
        for c in S:
            for word_it in word_dict.pop(c, []):
                word_dict[next(word_it, None)].append(word_it)
        return len(word_dict[None])
