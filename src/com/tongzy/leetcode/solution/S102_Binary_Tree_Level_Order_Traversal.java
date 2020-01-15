package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * <p>
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class S102_Binary_Tree_Level_Order_Traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.offer(root.left);
                if (root.right != null)
                    queue.offer(root.right);
            }
            result.add(level);
        }
        return result;
    }
/*
    Runtime: 1 ms, faster than 76.49% of Java online submissions for Binary Tree Level Order Traversal.
    Memory Usage: 37.2 MB, less than 96.67% of Java online submissions for Binary Tree Level Order Traversal.
*/

    public void helper(TreeNode root, int level, List<List<Integer>> levels) {
        if (root == null)
            return;
        if (level == levels.size()) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        helper(root.left, level + 1, levels);
        helper(root.right, level + 1, levels);
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Level Order Traversal.
    Memory Usage: 37.2 MB, less than 99.33% of Java online submissions for Binary Tree Level Order Traversal.
*/

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        helper(root, 0, levels);
        return levels;
    }
}
