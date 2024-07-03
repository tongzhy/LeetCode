package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class S82_Remove_Duplicates_from_Sorted_List_II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        if (slow.val == fast.val) {
            while(fast != null && slow.val == fast.val) {
                fast = fast.next;
            }
            return deleteDuplicates(fast);
        } else {
            slow.next = deleteDuplicates(slow.next);
            return slow;
        }
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            boolean duplicated = false;
            while (cur.next != null && cur.val == cur.next.val) {
                duplicated = true;
                cur = cur.next;
            }
            if (duplicated) {
                cur = cur.next;
                continue;
            }
            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        prev.next = cur;
        return dummy.next;
    }
}


/***
 * 递归问题的关键：
 * 1、边界问题
 * 2、如何分割问题，怎么续上？
 */