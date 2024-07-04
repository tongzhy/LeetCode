package com.tongzy.leetcode.solution;

public class S42_Trapping_Rain_Water {
    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i-1], height[i-1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], height[i+1]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int high = Math.min(left[i], right[i]);
            if (high > height[i]) {
                sum += high - height[i];
            }
        }
        return sum;
    }
}
