package com.tongzy.leetcode.solution;

import java.util.Arrays;

public class S16_3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (Math.abs(sum - target) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) ++low;
                else --high;
            }
        }
        return target - diff;
    }
}
