package leetcode.hashtable.LC460;

import java.util.HashMap;
import java.util.Map;

public class LC460LFUCache2 {
  class Node {
    int key, val, freq;
    Node prev, next;

    Node(int key, int val) {
      this.key = key;
      this.val = val;
      freq = 1;
    }
  }

  class DoubleLinkedList {
    Node head, tail;
    int size;

    DoubleLinkedList() {
      head = new Node(0, 0);
      tail = new Node(0, 0);
      head.next = tail;
      tail.prev = head;
    }

    void add(Node node) {
      head.next.prev = node;
      node.next = head.next;
      node.prev = head;
      head.next = node;
      size++;
    }

    void remove(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      size--;
    }

    Node removeLast() {
      if (size > 0) {
        Node node = tail.prev;
        remove(node);
        return node;
      } else return null;
    }
  }

  // cache capacity
  private int capacity;
  // min frequent
  private int minFreq;
  Map<Integer, Node> nodeMap;
  Map<Integer, DoubleLinkedList> freqMap;

  public LC460LFUCache2(int capacity) {
    this.minFreq = 0;
    this.capacity = capacity;
    nodeMap = new HashMap<>();
    freqMap = new HashMap<>();
  }

  public int get(int key) {
    Node node = nodeMap.get(key);
    if (node == null) return -1;
    update(node);
    return node.val;
  }

  public void put(int key, int value) {
    if (capacity == 0) return;
    Node node;
    if (nodeMap.containsKey(key)) {
      node = nodeMap.get(key);
      node.val = value;
      update(node);
    } else {
      node = new Node(key, value);
      nodeMap.put(key, node);
      if (nodeMap.size() == capacity) {
        DoubleLinkedList lastList = freqMap.get(minFreq);
        nodeMap.remove(lastList.removeLast().key);
      }
      minFreq = 1;
      DoubleLinkedList newList = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
      newList.add(node);
      freqMap.put(node.freq, newList);
    }
  }

  private void update(Node node) {
    DoubleLinkedList oldList = freqMap.get(node.freq);
    oldList.remove(node);
    if (node.freq == minFreq && oldList.size == 0) minFreq++;
    node.freq++;
    DoubleLinkedList newList = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
    newList.add(node);
    freqMap.put(node.freq, newList);
  }
}
