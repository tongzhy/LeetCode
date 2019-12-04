package com.tongzy.leetcode.solution;

import java.util.Arrays;

/**
 * 240. Search a 2D Matrix II
 */
public class Solution_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; //m行
        if (m == 0)
            return false;
        int n = matrix[0].length; //n列
        return binarySearch(matrix, target, 0, m - 1, 0, n - 1);
    }

    public boolean binarySearch(int[][] matrix, int target, int mLeft, int mRight, int nLeft, int nRight) {
        if (mLeft > mRight || nLeft > nRight)
            return false;
        if (mLeft == mRight) {
            return Arrays.binarySearch(matrix[mLeft], nLeft, nRight + 1, target) >= nLeft;
        }
        int mMid = mLeft + (mRight - mLeft) / 2;
        int nMid = nLeft + (nRight - nLeft) / 2;
        int compare = matrix[mMid][nMid];
        if (target == compare) {
            return true;
        } else if (target < compare) {
            return binarySearch(matrix, target, mLeft, mMid, nLeft, nMid);
        } else {
            //target > compare
            return binarySearch(matrix, target, mMid + 1, mRight, nLeft, nMid)
                    || binarySearch(matrix, target, mMid + 1, mRight, nMid + 1, nRight)
                    || binarySearch(matrix, target, mLeft, mMid, nMid + 1, nRight);
        }
    }
}
