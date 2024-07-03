package com.tongzy.leetcode.solution;

public class S80_Remove_Duplicates_from_Sorted_Array_II {
    public int removeDuplicates(int[] nums) {
        final int N = 2;
        if (nums.length <= N) return nums.length;
        int slow = N;
        for (int fast = N; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - N]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
