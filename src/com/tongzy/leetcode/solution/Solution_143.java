package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.ListNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reorder List
 * <p>
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
 * reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 */
public class Solution_143 {
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode p = head, q = head.next;
        int count = 1;
        while (q != null) {
            ++count;
            if (count % 2 == 1) {
                p = p.next;
            }
            q = q.next;
        }
        ListNode head2 = null;
        q = p.next;
        while (q != null) {
            p.next = q.next;
            q.next = head2;
            head2 = q;
            q = p.next;
        }
        p = head;
        q = head2;
        while (q != null) {
            head2 = q.next;
            q.next = p.next;
            p.next = q;
            p = q.next;
            q = head2;
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            new Solution_143().reorderList(head);
            String out = listNodeToString(head);

            System.out.print(out);
        }
    }
}

/*
Runtime: 1 ms, faster than 99.95% of Java online submissions for Reorder List.
        Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Reorder List.
*/
