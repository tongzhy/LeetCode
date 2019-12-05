package com.tongzy.leetcode.solution;

import java.lang.reflect.Method;
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

    public int findKthLargest5(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int index = quickSelect(nums, left, right);
        while (index != k - 1) {
            if (index < k - 1) {
                index = quickSelect(nums, index + 1, right);
            } else {
                index = quickSelect(nums, left, index - 1);
            }
        }
        return nums[index];
    }
/*
    Runtime: 27 ms, faster than 29.25% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 37.5 MB, less than 90.16% of Java online submissions for Kth Largest Element in an Array.
*/

    private int quickSelect(int[] nums, int left, int right) {
        if (left >= right)
            return left;
        int pivot = nums[left];
        int tmp;
        int head = left;
        while (left < right) {
            if (nums[right] <= pivot) {
                right--;
            } else if (nums[left] >= pivot) {
                left++;
            } else {
                tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        tmp = nums[head];
        nums[head] = nums[left];
        nums[left] = tmp;
        return left;
    }

    /**
     * @param nums an unsorted array
     * @param k    is always valid, 1 ≤ k ≤ array's length.
     * @return kth largest element
     */
    public int findKthLargest6(int[] nums, int k) {
        findKthLeast(nums, 0, nums.length - 1, nums.length - k);
        return nums[nums.length - k];
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Largest Element in an Array.
    Memory Usage: 36.3 MB, less than 90.67% of Java online submissions for Kth Largest Element in an Array.
*/

    private void findKthLeast(int[] nums, int start, int end, int Kth) {
        int left = start;
        int right = end;
        int pivot = nums[left] + (nums[right] - nums[left]) / 2;
        int swap;
        while (left <= right) {
            if (nums[left] < pivot) {
                left++;
            } else if (nums[right] > pivot) {
                right--;
            } else {
                if (left < right) {
                    swap = nums[left];
                    nums[left] = nums[right];
                    nums[right] = swap;
                }
                left++;
                right--;
            }
        }
        if (Kth >= start && Kth <= right) {
            findKthLeast(nums, start, right, Kth);
        } else if (Kth >= left && Kth <= end) {
            findKthLeast(nums, left, end, Kth);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        long t1 = System.currentTimeMillis();
        verifyAll(nums, Solution_215.class.getMethod("findKthLargest6", int[].class, int.class));
        long t2 = System.currentTimeMillis();
        System.out.println("time: " + (t2 - t1) + " ms");
    }

    private static void verifyAll(int[] nums, Method method) throws Exception {
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(copy);
        Solution_215 solution = new Solution_215();
        for (int i = 1; i <= nums.length; i++) {
            int kthLargest = (int) method.invoke(solution, Arrays.copyOfRange(nums, 0, nums.length), i);
            System.out.println("第" + (i) + "大数是" + kthLargest + "  "
                    + (kthLargest == copy[nums.length - i]));
        }
    }
}
