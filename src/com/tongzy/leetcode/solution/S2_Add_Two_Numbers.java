package com.tongzy.leetcode.solution;


import com.tongzy.leetcode.definition.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * <p>
 * 2. Add Two Numbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class S2_Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        int num = 0;
        do {
            if (l1 != null) {
                num += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num += l2.val;
                l2 = l2.next;
            }
            tail.next = new ListNode(num % 10);
            tail = tail.next;
            num /= 10;
        } while (l1 != null || l2 != null || num > 0);
        return head.next;
    }
}

/*
    Runtime: 1 ms, faster than 99.99% of Java online submissions for Add Two Numbers.
    Memory Usage: 43.3 MB, less than 88.40% of Java online submissions for Add Two Numbers.
*/
