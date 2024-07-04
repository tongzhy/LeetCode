package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.definition.Node;

import java.util.ArrayList;
import java.util.List;

public class S590_N_ary_Tree_Postorder_Traversal {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        traverseTree(root, result);
        return result;
    }

    void traverseTree(Node root, List<Integer> result) {
        if (root.children.isEmpty()){
            result.add(root.val);
            return;
        }
        for (Node child : root.children) {
            traverseTree(child, result);
        }
        result.add(root.val);
    }
}
