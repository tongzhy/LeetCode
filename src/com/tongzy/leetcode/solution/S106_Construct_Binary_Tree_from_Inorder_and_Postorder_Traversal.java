package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * <p>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class S106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length, map);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> map) {
        if (postStart >= postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd - 1]);
        int offset = map.get(postorder[postEnd - 1]) - inStart;
        root.left = buildTree(inorder, inStart, inStart + offset, postorder, postStart, postStart + offset, map);
        root.right = buildTree(inorder, inStart + offset + 1, inEnd, postorder, postStart + offset, postEnd - 1, map);
        return root;
    }
/*
    Runtime: 2 ms, faster than 94.47% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
    Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
*/
}
