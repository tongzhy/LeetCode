package com.tongzy.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Add to Array-Form of Integer
 * <p>
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
 * <p>
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,2,0,0], K = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 * <p>
 * Note£º
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * If A.length > 1, then A[0] != 0
 */
public class Solution_989 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        for (int i = A.length - 1; i > 0; --i) {
            A[i] += K % 10;
            K /= 10;
            if (A[i] > 9) {
                A[i - 1] += 1;
                A[i] -= 10;
            } else if (K == 0) {
                break;
            }
        }

        if (K == 0) {
            if (A[0] > 9) {
                A[0] -= 10;
                result.add(1);
            }
            result.add(A[0]);
        } else {
            K += A[0];
            int divisor = 10000;
            boolean flag = false;
            while (divisor > 0) {
                int sum = K / divisor;
                K %= divisor;
                divisor /= 10;
                if (flag || sum > 0) {
                    flag = true;
                    result.add(sum);
                }
            }
        }
        for (int i = 1; i < A.length; ++i) {
            result.add(A[i]);
        }
        return result;
    }
}
/*

Runtime: 3 ms, faster than 99.54% of Java online submissions for Add to Array-Form of Integer.
        Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Add to Array-Form of Integer.
*/
