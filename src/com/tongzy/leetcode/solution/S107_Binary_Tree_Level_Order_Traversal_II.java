package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 107. Binary Tree Level Order Traversal II
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class S107_Binary_Tree_Level_Order_Traversal_II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        helper(root, 0, levels);
        Collections.reverse(levels);
        return levels;
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Level Order Traversal II.
    Memory Usage: 37 MB, less than 100.00% of Java online submissions for Binary Tree Level Order Traversal II.
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
}
