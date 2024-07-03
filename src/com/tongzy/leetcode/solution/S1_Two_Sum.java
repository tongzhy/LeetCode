package com.tongzy.leetcode.solution;

import java.util.Map;
import java.util.HashMap;

public class S1_Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer complement = map.get(target - nums[i]);
            if (complement != null) {
                return new int[]{i, complement};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
