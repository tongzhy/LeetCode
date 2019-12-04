package com.tongzy.leetcode.solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class Solution_215 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
/*
    Runtime: 3 ms, faster than 78.11% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 38.3 MB, less than 47.67% of Java online submissions for Kth Largest Element in an Array.
*/

    public int findKthLargest2(int[] nums, int k) {
        int tmp;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums[nums.length - k];
    }
/*
    Runtime: 59 ms, faster than 5.41% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 38 MB, less than 51.81% of Java online submissions for Kth Largest Element in an Array.
*/

    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (queue.peek() < num) {
                queue.poll();
                queue.offer(num);
            }
        }
        return queue.peek();
    }
/*
    Runtime: 4 ms, faster than 68.77% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 36.5 MB, less than 90.67% of Java online submissions for Kth Largest Element in an Array.
*/

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int kthLargest3 = new Solution_215().findKthLargest4(nums, 2);
        System.out.println(kthLargest3);
    }

    public int findKthLargest4(int[] nums, int k) {
        if (k < nums.length / 2) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(k);
            for (int num : nums) {
                if (queue.size() < k) {
                    queue.offer(num);
                } else if (queue.peek() < num) {
                    queue.poll();
                    queue.offer(num);
                }
            }
            return queue.peek();
        } else {
            PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length - k + 1, new Comparator<Integer>() {
                @Override
                public int compare(Integer x, Integer y) {
                    return (x > y) ? -1 : ((x == y) ? 0 : 1);
                }
            });
            for (int num : nums) {
                if (queue.size() < nums.length - k + 1) {
                    queue.offer(num);
                } else if (queue.peek() > num) {
                    queue.poll();
                    queue.offer(num);
                }
            }
            return queue.peek();
        }
    }
/*
    Runtime: 5 ms, faster than 66.19% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 36.8 MB, less than 90.67% of Java online submissions for Kth Largest Element in an Array.
*/

}
