package com.tongzy.leetcode.solution;

import java.util.HashMap;

/**
 * @author : zhiyong.tzy@alibaba-inc.com
 * @version : LFUCache, v0.1
 * @date : 2024-05-22 17:55
 **/
class LFUCache {

    final private int capacity;
    final private HashMap<Integer, Node> map;
    final private HashMap<Integer, DoubleLinkedList> freqMap;
    private int min; // 记录最小的频率


    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        freqMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            update(node);
        } else {
            Node node = new Node(key, value);
            if (map.size() == capacity) {
                DoubleLinkedList last = freqMap.get(min);
                Node remove = last.pollLast();
                map.remove(remove.key);
            }
            min = 1;
            DoubleLinkedList list = freqMap.getOrDefault(min, new DoubleLinkedList());
            list.addFirst(node);
            freqMap.put(min, list);
            map.put(key, node);
        }
    }

    private void update(Node node) {
        DoubleLinkedList oldList = freqMap.get(node.count);
        oldList.removeNode(node);
        if (node.count == min && oldList.size == 0) min ++;
        node.count += 1;
        DoubleLinkedList newList = freqMap.getOrDefault(node.count, new DoubleLinkedList());
        newList.addFirst(node);
        freqMap.put(node.count, newList);
    }

    static class Node {
        public int key;
        public int value;

        public int count;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }

    static class DoubleLinkedList {
        Node head, tail;
        int size;

        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node pollLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            removeNode(last);
            return last;
        }

        public Node peekFirst() {
            if (head.next == tail) {
                return null;
            }
            return head.next;
        }

        public Node peekLast() {
            if (tail.prev == head) {
                return null;
            }
            return tail.prev;
        }
    }

    public static void main(String[] args){
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * @see https://leetcode.com/problems/lfu-cache/submissions/1264859365/
 */