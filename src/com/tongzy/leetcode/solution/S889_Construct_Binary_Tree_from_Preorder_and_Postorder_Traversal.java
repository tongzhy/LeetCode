package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 * <p>
 * Return any binary tree that matches the given preorder and postorder traversals.
 * <p>
 * Values in the traversals pre and post are distinct positive integers.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= pre.length == post.length <= 30
 * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class S889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>(post.length);
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return buildTree(pre, 0, pre.length, post, 0, post.length, map);
    }

    public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd, Map<Integer, Integer> map) {
        if (preStart >= preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart + 1 == preEnd) {
            return root;
        } else {
            int offset = map.get(pre[preStart + 1]) - postStart;
            root.left = buildTree(pre, preStart + 1, preStart + 2 + offset, post, postStart, postStart + offset + 1, map);
            root.right = buildTree(pre, preStart + 2 + offset, preEnd, post, postStart + offset + 1, postEnd - 1, map);
        }
        return root;
    }
/*
    Runtime: 2 ms, faster than 22.09% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
    Memory Usage: 38.7 MB, less than 27.27% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
*/

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        S889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal treeBuilder = new S889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal();
        TreeNode treeNode = treeBuilder.constructFromPrePost(preorder, postorder);
        /*else if (preStart + 2 == preEnd) {
            root.left = new TreeNode(pre[preStart + 1]);
        } else if (preStart + 3 == preEnd) {
            if (pre[preStart + 1] == post[postStart]) {
                root.left = new TreeNode(pre[preStart + 1]);
                root.right = new TreeNode(pre[preStart + 2]);
            } else {
                root.left = new TreeNode(pre[preStart + 1]);
                root.left.left = new TreeNode(pre[preStart + 2]);
            }
        }*/
    }
}
