package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class S105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int index = search(inorder, preorder[0]);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + index), Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + index, preorder.length), Arrays.copyOfRange(inorder, 1 + index, inorder.length));
        return root;
    }
/*
    Runtime: 31 ms, faster than 5.09% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    Memory Usage: 72.9 MB, less than 5.61% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
*/

    public int search(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int offset = search(inorder, inStart, inEnd, preorder[preStart]) - inStart;
        root.left = buildTree(preorder, preStart + 1, preStart + 1 + offset, inorder, inStart, inStart + offset);
        root.right = buildTree(preorder, preStart + 1 + offset, preEnd, inorder, inStart + offset + 1, inEnd);
        return root;
    }
/*
    Runtime: 8 ms, faster than 43.36% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
*/

    public int search(int[] a, int from, int to, int key) {
        for (int i = from; i < to; i++) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart >= preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int offset = map.get(preorder[preStart]) - inStart;
        root.left = buildTree(preorder, preStart + 1, preStart + 1 + offset, inorder, inStart, inStart + offset, map);
        root.right = buildTree(preorder, preStart + 1 + offset, preEnd, inorder, inStart + offset + 1, inEnd, map);
        return root;
    }
/*
    Runtime: 2 ms, faster than 97.05% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    Memory Usage: 40.2 MB, less than 13.08% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
*/

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        S105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal tree = new S105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal();
        TreeNode treeNode = tree.buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode treeNode2 = tree.buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }
}
