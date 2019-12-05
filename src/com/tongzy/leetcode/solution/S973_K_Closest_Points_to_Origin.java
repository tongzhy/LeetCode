package com.tongzy.leetcode.solution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 */
public class S973_K_Closest_Points_to_Origin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> queue = new PriorityQueue<>(K);
        for (int i = 0; i < points.length; i++) {
            Point point = new Point(i, points[i][0], points[i][1]);
            if (queue.size() < K) {
                queue.offer(point);
            } else if (point.compareTo(queue.peek()) > 0) {
                queue.poll();
                queue.offer(point);
            }
        }
        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) {
            int index = queue.poll().index;
            result[i][0] = points[index][0];
            result[i][1] = points[index][1];
        }
        return result;
    }
/*
    Runtime: 22 ms, faster than 66.64% of Java online submissions for K Closest Points to Origin.
    Memory Usage: 57.7 MB, less than 85.09% of Java online submissions for K Closest Points to Origin.
*/

    class Point implements Comparable<Point> {
        public int index;
        public int distance;

        public Point(int index, int a, int b) {
            this.index = index;
            distance = a * a + b * b;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.distance, this.distance);
        }
    }

    /**
     * @param points -10000 < points[i][0] < 10000 && -10000 < points[i][1] < 10000
     * @param K      1 <= K <= points.length <= 10000
     * @return K Closest Points
     */
    public int[][] kClosest2(int[][] points, int K) {
        int length = points.length;
        int[] distance = new int[length]; // 空间换时间，减少距离计算
        for (int i = 0; i < length; i++) {
            distance[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }
        findKthLeast(points, distance, 0, length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
/*
    Runtime: 4 ms, faster than 99.74% of Java online submissions for K Closest Points to Origin.
    Memory Usage: 57.5 MB, less than 85.71% of Java online submissions for K Closest Points to Origin.
*/

    private void findKthLeast(int[][] points, int[] distance, int start, int end, int Kth) {
        if (start >= end) return;
        int left = start;
        int right = end;
        int pivot = distance[left] + (distance[right] - distance[left]) / 2;
        int swap;
        int[] swapPoint;
        while (left <= right) {
            if (distance[left] < pivot) {
                left++;
            } else if (distance[right] > pivot) {
                right--;
            } else {
                if (left < right) {
                    swap = distance[left];
                    distance[left] = distance[right];
                    distance[right] = swap;
                    swapPoint = points[left];
                    points[left] = points[right];
                    points[right] = swapPoint;
                }
                left++;
                right--;
            }
        }
        if (Kth >= start && Kth <= right) {
            findKthLeast(points, distance, start, right, Kth);
        } else if (Kth >= left && Kth <= end) {
            findKthLeast(points, distance, left, end, Kth);
        }
    }
}
