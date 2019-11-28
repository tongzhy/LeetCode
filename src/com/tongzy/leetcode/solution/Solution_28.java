package com.tongzy.leetcode.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_28 {
    public int strStr(String haystack, String needle) {
        char[] text = haystack.toCharArray();
        char[] key = needle.toCharArray();
        int[] next = new int[key.length];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < key.length - 1) {
            if (k == -1 || key[k] == key[j]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        int i = 0;
        j = 0;
        while (i < text.length && j < key.length) {
            if (j == -1 || text[i] == key[j]) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == key.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String haystack = line;
            line = in.readLine();
            String needle = line;

            int ret = new Solution_28().strStr(haystack, needle);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
