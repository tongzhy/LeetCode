package com.tongzy.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

public class S128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int length = 1;
                while (set.contains(cur + 1)) {
                    cur = cur + 1;
                    length = length + 1;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}
