package com.tongzy.leetcode.solution;

import com.tongzy.leetcode.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * 4. Median of Two Sorted Arrays
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class S4_Median_of_Two_Sorted_Arrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return method(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);
    }

/*
    Runtime: 3 ms, faster than 36.57% of Java online submissions for Median of Two Sorted Arrays.
    Memory Usage: 47.3 MB, less than 84.03% of Java online submissions for Median of Two Sorted Arrays.
*/

    private double method(int[] nums1, int head1, int tail1, int[] nums2, int head2, int tail2) {
        if (nums1 == null || head1 > tail1) {
            return median(nums2, head2, tail2);
        } else if (nums2 == null || head2 > tail2) {
            return median(nums1, head1, tail1);
        }
        if (tail1 - head1 < 2 || tail2 - head2 < 2) {
            int[] array = new int[tail1 - head1 + 1 + tail2 - head2 + 1];
            for (int j = 0; j < array.length; j++) {
                if (head1 > tail1) {
                    array[j] = nums2[head2++];
                    continue;
                }
                if (head2 > tail2) {
                    array[j] = nums1[head1++];
                    continue;
                }
                if (nums1[head1] < nums2[head2]) {
                    array[j] = nums1[head1++];
                } else {
                    array[j] = nums2[head2++];
                }
            }
            return median(array, 0, array.length - 1);
        }
        double median1 = median(nums1, head1, tail1);
        double median2 = median(nums2, head2, tail2);
        int reduce = Math.min(tail1 - head1, tail2 - head2) / 2;
        if (median1 == median2) {
            return (median1 + median2) / 2;
        } else if (median1 < median2) {
            return method(nums1, head1 + reduce, tail1, nums2, head2, tail2 - reduce);
        } else {
            return method(nums1, head1, tail1 - reduce, nums2, head2 + reduce, tail2);
        }
    }

    private double median(int[] nums, int head, int tail) {
        if ((tail - head) % 2 == 0) {
            return nums[head + (tail - head) / 2];
        } else {
            return (nums[head + (tail - head) / 2] + nums[head + (tail - head + 1) / 2]) / 2.0;
        }
    }

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = StringUtil.stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = StringUtil.stringToIntegerArray(line);

            double ret = new S4_Median_of_Two_Sorted_Arrays().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int head1 = 0, tail1 = nums1.length - 1;
        int head2 = 0, tail2 = nums2.length - 1;
        double median1, median2;
        while (tail1 - head1 > 1 && tail2 - head2 > 1) {
            median1 = median(nums1, head1, tail1);
            median2 = median(nums2, head2, tail2);
            int reduce = Math.min(tail1 - head1, tail2 - head2) / 2;
            if (median1 < median2) {
                head1 += reduce;
                tail2 -= reduce;
            } else if (median1 > median2) {
                tail1 -= reduce;
                head2 += reduce;
            } else if (median1 == median2) {
                return (median1 + median2) / 2;
            }
        }
        int[] array = new int[tail1 - head1 + 1 + tail2 - head2 + 1];
        for (int i = 0; i < array.length; i++) {
            if (head1 > tail1) {
                array[i] = nums2[head2++];
                continue;
            }
            if (head2 > tail2) {
                array[i] = nums1[head1++];
                continue;
            }
            if (nums1[head1] < nums2[head2]) {
                array[i] = nums1[head1++];
            } else {
                array[i] = nums2[head2++];
            }
        }
        return median(array, 0, array.length - 1);
    }

    /**
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/shuang-zhi-zhen-by-powcai/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public double findMedian(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedian(nums2, nums1);
        int k = (n1 + n2 + 1) / 2;
        int left = 0;
        int right = n1;
        while (left < right) {
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1])
                left = m1 + 1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;

    }


    class Solution {
        /**
         * 二分查找，第K小 O(log(min(m,n)))
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                int[] tmp = nums2;
                nums2 = nums1;
                nums1 = tmp;
            }
            int k = (nums1.length + nums2.length + 1) / 2;
            int left = 0;
            int right = nums1.length - 0;
            while (left < right) {
                int m1 = left + (right - left) / 2;
                int m2 = k - m1;
                if (nums1[m1] < nums2[m2 - 1]) {
                    left = m1 + 1;
                } else {
                    right = m1;
                }
            }
            int m1 = left;
            int m2 = k - left;
            int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                    m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
            if ((nums1.length + nums2.length) % 2 == 1)
                return c1;
            int c2 = Math.min(m1 >= nums1.length ? Integer.MAX_VALUE : nums1[m1],
                    m2 >= nums2.length ? Integer.MAX_VALUE : nums2[m2]);
            return (c1 + c2) * 0.5;
        }
    }


}
