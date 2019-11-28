package com.tongzy.leetcode.solution;

/**
 * Majority Element
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ? n/2 ? times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class Solution_169 {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                major = nums[++i];
                count = 1;
            }
        }
        return major;
    }
}
