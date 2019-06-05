import collections

class Solution:
    def numMatchingSubseq(self, S: str, words: List[str]) -> int:
        word_dict = collections.defaultdict(list)
        for word in words:
            word_dict[word[0]].append(word[1:])
        for c in S:
            for word in word_dict.pop(c, []):
                if not word:
                    word_dict[''].append('')
                else:
                    word_dict[word[0]].append(word[1:])
        return len(word_dict[''])
