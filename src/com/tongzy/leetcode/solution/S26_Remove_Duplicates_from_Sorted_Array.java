package com.tongzy.leetcode.solution;

public class S26_Remove_Duplicates_from_Sorted_Array {

    public int removeDuplicates(int[] nums) {

        int i = 0;
        int j = 0;

        while (j < nums.length) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }
}
