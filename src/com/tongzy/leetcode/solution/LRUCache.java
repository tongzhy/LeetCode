package com.tongzy.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhiyong.tzy@alibaba-inc.com
 * @version : LRUCache, v0.1
 * @date : 2024-05-22 15:49
 **/
class LRUCache {

    final private int capacity;
    final private Map<Integer, Node> map;
    final private DoubleLinkedList doubleLinkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        doubleLinkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
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
                Node last = doubleLinkedList.peekLast();
                doubleLinkedList.removeNode(last);
                map.remove(last.key);
            }
            map.put(key, node);
            doubleLinkedList.addFirst(node);
        }
    }

    private void update(Node node) {
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addFirst(node);
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
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
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * https://leetcode.com/problems/lru-cache/submissions/1264712255/
 */

/**
 * https://leetcode.com/problems/lru-cache/submissions/1264712255/
 */