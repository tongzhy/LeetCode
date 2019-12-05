package com.tongzy.leetcode.solution;

import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 */
public class Solution_973 {
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
}
