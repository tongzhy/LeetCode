package com.tongzy.leetcode.solution;

/**
 * 70. Climbing Stairs
 * <p>
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Note: Given n will be a positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class S70_Climbing_Stairs {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);  // slow very much!
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(new S70_Climbing_Stairs().climbStairs3(2));
        System.out.println(System.currentTimeMillis() - t1);
    }

    /**
     * T = O(n), S = O(n)
     */
    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] count = new int[n + 1];
        count[1] = 1;
        count[2] = 2;
        for (int i = 3; i <= n; i++) {
            count[i] = count[i - 1] + count[i - 2];
        }
        return count[n];
    }

    /**
     * T = O(n), S = O(1)
     */
    public int climbStairs3(int n) {
        int[] count = new int[3];
        count[1] = 1;
        count[2] = 2;
        for (int i = 3; i <= n; i++) {
            count[i % 3] = count[(i - 1) % 3] + count[(i - 2) % 3];
        }
        return count[n % 3];
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
    Memory Usage: 32.8 MB, less than 5.26% of Java online submissions for Climbing Stairs.
*/
}
