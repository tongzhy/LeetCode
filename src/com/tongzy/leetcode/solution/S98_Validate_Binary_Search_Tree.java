package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;
import com.tongzy.leetcode.util.TreeUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 98. Validate Binary Search Tree
 * <p>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 * <p>
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * <p>
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class S98_Validate_Binary_Search_Tree {

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (pre >= p.val) return false;
            pre = p.val;
            p = p.right;
        }
        return true;
    }
/*
    Runtime: 1 ms, faster than 33.01% of Java online submissions for Validate Binary Search Tree.
    Memory Usage: 39.3 MB, less than 80.47% of Java online submissions for Validate Binary Search Tree.
*/

    public boolean isValidBST(TreeNode root, long down, long up) {
        if (root == null) {
            return true;
        }
        if (root.val >= up || root.val <= down) {
            return false;
        }
        boolean isValidBST = true;
        if (root.left != null) {
            isValidBST = isValidBST && (root.left.val < root.val) && isValidBST(root.left, down, root.val);
        }
        if (root.right != null) {
            isValidBST = isValidBST && (root.right.val > root.val) && isValidBST(root.right, root.val, up);
        }
        return isValidBST;
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
    Memory Usage: 39.1 MB, less than 81.40% of Java online submissions for Validate Binary Search Tree.
*/

    public boolean helper(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtil.stringToTreeNode("[-2147483648,null,2147483647,-2147483648]");
        S98_Validate_Binary_Search_Tree solution = new S98_Validate_Binary_Search_Tree();
        boolean validBST = solution.isValidBST(root);
        boolean validBST2 = solution.isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.println(validBST);
        System.out.println(validBST2);
    }
}
