package leetcode.hashtable.LC1178;

import java.util.*;
import java.util.stream.Collectors;

public class LC1178NumValidWordsForEachPuzzle {
  /**
   * Solution #1: brute-force
   * TC: O(
   */
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    Map<Integer, Set<Integer>> wordMap = new HashMap<>();
    int n = words.length;
    // O(n * len(word))
    for (int i = 0; i < n; i++) {
      wordMap.put(i, new HashSet<>());
      for (char ch : words[i].toCharArray()) {
        wordMap.get(i).add(ch - 'a');
      }
    }
    int count = 0;
    List<Integer> res = new ArrayList<>();
    Set<Integer> puzzleSet = new HashSet<>();
    // O(m * len(puzzle))
    for (String p : puzzles) {
      puzzleSet.clear();
      for (char ch : p.toCharArray()) {
        puzzleSet.add(ch - 'a');
      }
      count = getNumValidWords(wordMap, puzzleSet, p.charAt(0) - 'a');
      res.add(count);
    }

    System.out.println("res = " + res.toString());
    return res;
  }

  // O(
  private int getNumValidWords(Map<Integer, Set<Integer>> wordMap, Set<Integer> puzzle, int first) {
    int count = 0;
    for (Set<Integer> word : wordMap.values()) {
      if (!word.contains(first) || !puzzle.containsAll(word)) continue;
      count++;
    }
    return count;
  }

  /**
   * Solution #2:
   */
  public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
    Map<Integer, BitSet> wordMap = new HashMap<>();
    int n = words.length;
    // O(n * len(word))
    for (int i = 0; i < n; i++) {
      wordMap.put(i, new BitSet());
      for (char ch : words[i].toCharArray()) {
        wordMap.get(i).set(ch - 'a');
      }
    }
    int count = 0;
    List<Integer> res = new ArrayList<>();
    int first = 0;
    // O(m * len(puzzle))
    for (String p : puzzles) {
      count = 0;
      first = p.charAt(0) - 'a';
      BitSet puzzle = new BitSet();
      for (char ch : p.toCharArray()) {
        puzzle.set(ch - 'a');
      }
      for (BitSet word : wordMap.values()) {
        // not include first letter, skip
        if (!word.get(first)) continue;
        boolean validWord = true;
        for (int i = 0; i < word.length(); i++) {
          if (word.get(i) && !puzzle.get(i)) {
            validWord = false;
            break;
          }
        }
        if (validWord) count++;
      }
      res.add(count);
    }

    System.out.println("#2 res = " + res);
    return res;
  }

  /**
   * Solution #3: bit operation with all possible subsets.
   * since puzzles length at most 7, so at most all possible subsets are 2^7 = 128.
   *
   * TC: O(128 * n + m * k), n is puzzles length, m is words length, k is avg of single word length
   */
  public List<Integer> findNumOfValidWords3(String[] words, String[] puzzles) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (String word : words) {
      int mask = getMask(word);
      freqMap.put(mask, freqMap.getOrDefault(mask, 0) + 1);
    }
    List<Integer> res = new ArrayList<>();
    int count = 0;
    for (String p : puzzles) {
      int mask = getMask(p);
      int first = 1 << (p.charAt(0) - 'a');
      count = 0;
      int curr = mask;
      while (curr > 0) {
        if ((curr & first) == first && freqMap.containsKey(curr)) {
          count += freqMap.get(curr);
        }
        // next subset
        curr = (curr - 1) & mask;
      }
      res.add(count);
    }
    System.out.println("#3 res= " + res);
    return res;
  }

  private int getMask(String s) {
    int mask = 0;
    for (char ch : s.toCharArray()) {
      mask |= 1 << (ch - 'a');
    }
    return mask;
  }

  /**
   * Solution #4. trie
   */

  public List<Integer> findNumOfValidWords4(String[] words, String[] puzzles) {
    Trie root = buildTrieTree(words);
    List<Integer> res = new ArrayList<>();
    for (String p : puzzles) {
      Set<Character> puzzleSet = buildSetChar(p);
      char first = p.charAt(0);
      // find counts
//      res.add(findNumValidWordsRec(root, puzzleSet, first, false));
      res.add(findNumValidWordsRec(root, puzzleSet, first, false));
    }
    System.out.println("#4 res=" + res.toString());
    return res;
  }

  private int findNumValidWordsRec(Trie curr, Set<Character> puzzleSet, char first, boolean firstSeen) {
    if (curr == null) return 0;
    int count = 0;
    if (firstSeen) count += curr.count;
    for (char ch : puzzleSet) {
      if (curr.children[ch - 'a'] == null) continue;
      if (ch == first) {
        count += findNumValidWordsRec(curr.children[ch - 'a'], puzzleSet, first, true);
      } else {
        count += findNumValidWordsRec(curr.children[ch - 'a'], puzzleSet, first, firstSeen);
      }
    }
    return count;
  }

  // O(len(word))
  private Set<Character> buildSetChar(String word) {
    Set<Character> charSet = new TreeSet<>();
    for (char ch : word.toCharArray()) {
      charSet.add(ch);
    }
    return charSet;
  }

  // O(n * len(word))
  private Trie buildTrieTree(String[] words) {
    Trie root = new Trie();
    for (String word : words) {
      Set<Character> wordSet = buildSetChar(word);
      Trie curr = root;
      for (char ch : wordSet) {
        if (curr.children[ch - 'a'] == null) {
          curr.children[ch - 'a'] = new Trie();
        }
        curr = curr.children[ch - 'a'];
      }
      curr.count++;
    }
    return root;
  }

  class Trie {
    Trie[] children;
    int count;
    Trie() {
      children = new Trie[26];
      count = 0;
    }
  }

  public static void main(String[] args) {
    System.out.println(~17);
    String[] words = new String[]{"aaaa","asas","able","ability","actt","actor","access"};
    String[] puzzles = new String[]{"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
    LC1178NumValidWordsForEachPuzzle test = new LC1178NumValidWordsForEachPuzzle();
    test.findNumOfValidWords(words, puzzles);
    test.findNumOfValidWords2(words, puzzles);
    test.findNumOfValidWords3(words, puzzles);
    test.findNumOfValidWords4(words, puzzles);
  }
}
