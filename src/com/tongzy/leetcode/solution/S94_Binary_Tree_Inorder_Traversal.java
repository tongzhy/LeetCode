package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.*;

/**
 * 94. Binary Tree Inorder Traversal
 * <p>
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [1,3,2]
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class S94_Binary_Tree_Inorder_Traversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
    Memory Usage: 35.3 MB, less than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
*/
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
        }
        return result;
    }
}
