package leetcode.hashtable.LC1152;

import java.util.*;

/**
 * 1152. Analyze User Website Visit Pattern
 * You are given three arrays username, timestamp and website of the same length N where the ith tuple means that
 * the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of not necessarily different websites of length 3 sorted in ascending order by the time of their visits.
 *
 * Find the 3-sequence visited at least once by the largest number of users. If there is more than one solution,
 * return the lexicographically minimum solution.
 *
 * A 3-sequence X is lexicographically smaller than a 3-sequence Y if X[0] < Y[0] or X[0] == Y[0]
 * and (X[1] < Y[1] or X[1] == Y[1] and X[2] < Y[2]).
 *
 * It is guaranteed that there is at least one user who visited at least 3 websites. No user visits two websites at the same time.
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 * Note:
 *
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 */
public class LC1152AnalyzeUserWebsiteVisitePattern {
  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    if (username == null || username.length == 0) return new ArrayList<>();
    int len = username.length;
    List<UserPattern> userPatterns = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      userPatterns.add(new UserPattern(username[i], timestamp[i], website[i]));
    }
    // sort users visited website with timestamp in ascending order
    Collections.sort(userPatterns, (a, b) -> a.timestamp - b.timestamp);
    Map<String, List<String>> userWebsiteMap = new HashMap<>();
    for (UserPattern user : userPatterns) {
      userWebsiteMap.computeIfAbsent(user.username, c -> new ArrayList<>()).add(user.website);
    }
    // possiblities of 3-sequence websites visited by user
    Map<WebsiteSequence, Integer> sequenceCountMap = new HashMap<>();
    int maxCount = 0;
    WebsiteSequence resSeq = null;
    for (Map.Entry<String, List<String>> entry : userWebsiteMap.entrySet()) {
      List<String> userWebsites = entry.getValue();
      // sequence need at least 3 websites
      if (userWebsites.size() < 3) continue;
      Set<WebsiteSequence> userWebsiteSequences = getUserWebsiteSequences(userWebsites);
      for (WebsiteSequence userSeq : userWebsiteSequences) {
        sequenceCountMap.put(userSeq, sequenceCountMap.getOrDefault(userSeq, 0) + 1);
        if (sequenceCountMap.get(userSeq) > maxCount) {
          maxCount = sequenceCountMap.get(userSeq);
          resSeq = userSeq;
        } else if (sequenceCountMap.get(userSeq) == maxCount && userSeq.compareTo(resSeq) < 0) {
          resSeq = userSeq;
        }
      }
    }
    return Arrays.asList(resSeq.website1, resSeq.website2, resSeq.website3);
  }

  private Set<WebsiteSequence> getUserWebsiteSequences(List<String> userWebsits) {
    int size = userWebsits.size();
    Set<WebsiteSequence> res = new HashSet<>();
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        for (int k = j + 1; k < size; k++) {
          res.add(new WebsiteSequence(userWebsits.get(i), userWebsits.get(j), userWebsits.get(k)));
        }
      }
    }
    return res;
  }
}
class WebsiteSequence implements Comparable<WebsiteSequence> {
  String website1;
  String website2;
  String website3;

  public WebsiteSequence(String website1, String website2, String website3) {
    this.website1 = website1;
    this.website2 = website2;
    this.website3 = website3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebsiteSequence that = (WebsiteSequence) o;
    return website1.equals(that.website1) &&
        website2.equals(that.website2) &&
        website3.equals(that.website3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(website1, website2, website3);
  }

  @Override
  public int compareTo(WebsiteSequence o) {
    if (!this.website1.equals(o.website1)) {
      return this.website1.compareTo(o.website1);
    } else if (!this.website2.equals(o.website2)) {
      return this.website2.compareTo(o.website2);
    } else {
      return this.website3.compareTo(o.website3);
    }
  }
}

class UserPattern {
  String username;
  int timestamp;
  String website;

  public UserPattern(String username, int timestamp, String website) {
    this.username = username;
    this.timestamp = timestamp;
    this.website = website;
  }
}
