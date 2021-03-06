package com.tongzy.leetcode.solution;

import java.util.Arrays;

/**
 * 240. Search a 2D Matrix II
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 */
public class Solution_240_Search_a_2D_Matrix {
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
            return binarySearch(matrix, target, mLeft, mMid - 1, nLeft, nMid - 1)
                    || binarySearch(matrix, target, mMid, mRight, nLeft, nMid - 1)
                    || binarySearch(matrix, target, mLeft, mMid - 1, nMid, nRight);
        } else {
            //target > compare
            return binarySearch(matrix, target, mMid + 1, mRight, nLeft, nMid)
                    || binarySearch(matrix, target, mMid + 1, mRight, nMid + 1, nRight)
                    || binarySearch(matrix, target, mLeft, mMid, nMid + 1, nRight);
        }
    }
/*
    Runtime: 19 ms, faster than 5.01% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 42.7 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
*/

    public boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;
        for (int m = 0; m < rows; m++) {
            if (Arrays.binarySearch(matrix[m], target) >= 0)
                return true;
        }
        return false;
    }
/*
    Runtime: 6 ms, faster than 31.56% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 43.6 MB, less than 98.11% of Java online submissions for Search a 2D Matrix II.
*/

    public boolean searchMatrix3(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;
        if (columns == 0) return false;
        for (int m = 0; m < rows; m++) {
            if (target < matrix[m][0] || target > matrix[m][columns - 1]) {
                continue;
            }
            if (Arrays.binarySearch(matrix[m], target) >= 0)
                return true;
        }
        return false;
    }
/*
    Runtime: 6 ms, faster than 31.56% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 42.5 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
*/

    public boolean searchMatrix4(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;
        if (columns == 0) return false;
        int top = 0, bottom = rows - 1;
        while (top < bottom) {
            int mid = (top + bottom) / 2;
            if (target < matrix[mid][0]) {
                bottom = mid - 1;
            } else if (target > matrix[mid][columns - 1]) {
                top = mid + 1;
            } else {
                break;
            }
        }
        // not effective
        for (int m = top; m <= bottom; m++) {
            if (Arrays.binarySearch(matrix[m], target) >= 0)
                return true;
        }
        return false;
    }
/*
    Runtime: 6 ms, faster than 31.56% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 41.9 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
*/

    public boolean searchMatrix5(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;
        if (columns == 0) return false;
        int left = 0, right = rows - 1;
        int bottom = rows - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (target < matrix[mid][0]) {
                right = mid - 1;
                bottom = right;
            } else {
                left = mid;
            }
        }
        int top = 0;
        left = 0;
        right = rows - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target > matrix[mid][columns - 1]) {
                left = mid + 1;
                top = left;
            } else {
                right = mid;
            }
        }
        for (int m = top; m <= bottom; m++) {
            if (Arrays.binarySearch(matrix[m], target) >= 0)
                return true;
        }
        return false;
    }
/*
    Runtime: 6 ms, faster than 31.56% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 42.7 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
*/

    public boolean searchMatrix6(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int i = matrix.length - 1;
        int j = 0;

        while (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                // target < matrix[i][j]
                i--;
            }
        }
        return false;
    }
/*
    Runtime: 5 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
    Memory Usage: 42.8 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
*/

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int num = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = num++;
            }
        }
        Solution_240_Search_a_2D_Matrix solution = new Solution_240_Search_a_2D_Matrix();
        for (int i = 0; i < 30; i++) {
            System.out.println(i + "  " + solution.searchMatrix6(matrix, i));
        }
    }
}
