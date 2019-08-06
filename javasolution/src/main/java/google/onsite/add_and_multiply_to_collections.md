# Add And Multiply to Collection

### Problem

Design an integer collection that supports addToAll opearation that adds a given number to all the elements in the 
collection at this point.
``` 
class AddToAllCollection {
  public void append(int val) {
  }

  public int get(int index) {
  }

  public void addToAll(int val) {
  }
}
```
All methods should work in O(1) time.
   
Example: 
```
   AddToAllCollection col = new AddToAllCollection(); // []  initialy collection is empty
   col.append(1); // [1]
   col.append(2); // [1, 2]
   col.append(3); // [1, 2, 3]
   col.get(2); // return 3
   col.addToAll(1); // [2, 3, 4]
   col.get(2); // return 4
   col.addToAll(5); // [7, 8, 9]
   col.get(2); // return 9
   col.append(4); // [7, 8, 9, 4]
   col.get(3); // return 4, because the newly appended number was not involved with the previous addToAll calls.
```

### 题意分析

### 解答（Solution）

    


#### Reference
1. [Leetcode interview discussion solution](https://leetcode.com/discuss/interview-question/334203)