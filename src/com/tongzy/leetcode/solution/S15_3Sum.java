package com.tongzy.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (List<Integer> list : twoSum(nums, i + 1, 0 - nums[i])) {
                    list.add(0, nums[i]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int low = start, high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                result.add(new ArrayList<>(Arrays.asList(nums[low++], nums[high--])));
                while (low < high && nums[low] == nums[low - 1]) ++low;
                while (low < high && nums[high] == nums[high + 1]) --high;
            }
        }
        return result;
    }
}
