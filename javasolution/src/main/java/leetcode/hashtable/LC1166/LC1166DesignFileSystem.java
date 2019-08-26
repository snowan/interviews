package leetcode.hashtable.LC1166;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1166DesignFileSystem {
  Map<String, Integer> fileMap;

  public LC1166DesignFileSystem() {
    fileMap = new HashMap<>();
    fileMap.put("", -1);
  }

  public boolean create1(String path, int value) {
    int lastIdx = path.lastIndexOf("/");
    String parentPath = path.substring(0, lastIdx);
    if (!fileMap.containsKey(parentPath)) return false;
    return fileMap.putIfAbsent(path, value) == null;
  }

  public boolean create2(String path, int value) {
    String[] paths = path.split("/");
    int len = paths.length;
    if (len == 2) {
      fileMap.put(path, value);
      return true;
    }
    StringBuilder sb = new StringBuilder();
    sb.append("/");
    for (int i = 1; i < len; i++) {
      sb.append(paths[i]);
      if (i == len - 1) {
        fileMap.put(sb.toString(), value);
        return true;
      }
      if (!fileMap.containsKey(sb.toString())) {
        return false;
      }
      sb.append("/");
    }
    return true;
  }

  public int get(String path) {
    return fileMap.getOrDefault(path, -1);
  }

  public static void main(String[] args) {
    LC1166DesignFileSystem fileSystem = new LC1166DesignFileSystem();
    System.out.println(fileSystem.create1("/a", 1));
    System.out.println(fileSystem.get("/a"));

    System.out.println(fileSystem.create1("/leet", 1));
    System.out.println(fileSystem.create1("/leet/code/", 2));
    System.out.println(fileSystem.get("/leet/code"));
    System.out.println(fileSystem.create1("/c/d", 1));
    System.out.println(fileSystem.get("/c"));

    System.out.println("----------------------------------------");

    LC1166DesignFileSystem fileSystem2 = new LC1166DesignFileSystem();
    System.out.println(fileSystem2.create2("/a", 1));
    System.out.println(fileSystem2.get("/a"));

    System.out.println(fileSystem2.create2("/leet", 1));
    System.out.println(fileSystem2.create2("/leet/code/", 2));
    System.out.println(fileSystem2.get("/leet/code"));
    System.out.println(fileSystem2.create2("/c/d", 1));
    System.out.println(fileSystem2.get("/c"));
  }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.create(path,value);
 * int param_2 = obj.get(path);
 */
