package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.ListNode;

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
public class S83_Remove_Duplicates_from_Sorted_List {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = deleteDuplicates(head.next);
        if (head.val == newHead.val) {
            return newHead;
        } else {
            head.next = newHead;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        for (ListNode slow = head, fast = head.next; fast != null; fast = slow.next) {
            if (slow.val == fast.val) {
                slow.next = fast.next;
            } else {
                slow = fast;
            }
        }
        return head;
    }
}


