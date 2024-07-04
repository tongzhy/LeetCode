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
}
