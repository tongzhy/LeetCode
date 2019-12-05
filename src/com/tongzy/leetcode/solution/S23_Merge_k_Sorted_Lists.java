package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.ListNode;

/**
 * 23. Merge k Sorted Lists
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class S23_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        merge(lists, 0, lists.length - 1);
        return lists[0];
    }

    private void merge(ListNode[] lists, int m, int n) {
        if (m == n) {
            return;
        }
        int k = m + (n - m) / 2;
        merge(lists, m, k);
        merge(lists, k + 1, n);
        lists[m] = merge(lists[m], lists[k + 1]);
        lists[k + 1] = null;
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                tail = a;
                a = a.next;
            } else {
                tail.next = b;
                tail = b;
                b = b.next;
            }
        }
        if (a != null) {
            tail.next = a;
        }
        if (b != null) {
            tail.next = b;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[10];
        for (int i = 0; i < 10; i++) {
            lists[9 - i] = new ListNode(i);
        }
        ListNode listNode = new S23_Merge_k_Sorted_Lists().mergeKLists(lists);
        System.out.println(listNode);
    }
}

/*
    Runtime: 2 ms, faster than 99.41% of Java online submissions for Merge k Sorted Lists.
    Memory Usage: 41.7 MB, less than 34.98% of Java online submissions for Merge k Sorted Lists.
*/
