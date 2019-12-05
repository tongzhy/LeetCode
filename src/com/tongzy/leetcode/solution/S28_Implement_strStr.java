package com.tongzy.leetcode.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 28. Implement strStr()
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class S28_Implement_strStr {
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

            int ret = new S28_Implement_strStr().strStr(haystack, needle);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
