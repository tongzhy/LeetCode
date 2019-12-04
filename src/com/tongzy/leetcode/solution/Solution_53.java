package com.tongzy.leetcode.solution;

/**
 * Maximum Subarray
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class Solution_53 {
    public int maxSubArray(int[] nums) {
        int j = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int maxElem = Integer.MIN_VALUE;
        while (j < nums.length) {
            if (nums[j] > maxElem) {
                maxElem = nums[j];
            }
            sum += nums[j++];
            if (sum < 0) {
                sum = 0;
            }
            if (sum > max) {
                max = sum;
            }
        }
        if (max <= 0) {
            return maxElem;
        }
        return max;
    }

/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
    Memory Usage: 37 MB, less than 99.53% of Java online submissions for Maximum Subarray.
*/

    /**
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/hua-jie-suan-fa-53-zui-da-zi-xu-he-by-guanpengchn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

/*
    Runtime: 1 ms, faster than 82.49% of Java online submissions for Maximum Subarray.
    Memory Usage: 37.5 MB, less than 99.53% of Java online submissions for Maximum Subarray.
*/


    /**
     * LeetCode sample
     */
    public int maxSubArray3(int[] nums) {
        int max = nums[0], tempM = nums[0];

        for (int i = 1; i < nums.length; i++) {
            tempM = Math.max(nums[i], tempM + nums[i]);
            if (tempM > max) {
                max = tempM;
            }
        }
        return max;
    }
}
