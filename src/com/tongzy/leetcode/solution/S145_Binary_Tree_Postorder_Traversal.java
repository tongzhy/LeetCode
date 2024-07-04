package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S145_Binary_Tree_Postorder_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, prev;
        do {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            prev = null;
            while (!stack.isEmpty()) {
                cur = stack.pop();
                if (cur.right == prev) {
                    result.add(cur.val);
                    prev = cur;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                    break;
                }
            }
        } while (!stack.isEmpty());
        return result;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        traverseTree(root, result);
        return result;
    }

    void traverseTree(TreeNode root, List<Integer> result) {
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }
        if (root.left != null) {
            traverseTree(root.left, result);
        }
        if (root.right != null) {
            traverseTree(root.right, result);
        }
        result.add(root.val);
    }
}
