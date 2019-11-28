package com.tongzy.leetcode.solution;

public class Solution_5 {

    public String longestPalindrome(String s) {
        // ����
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // �õ�Ԥ�����ַ���
        String str = addBoundaries(s, '#');
        // ���ַ����ĳ���
        int sLen = 2 * len + 1;

        // ���� p ��¼��ɨ����Ļ����Ӵ�����Ϣ
        int[] p = new int[sLen];

        // ˫ָ�룬������һһ��Ӧ�ģ���ͬʱ����
        int maxRight = 0;
        int center = 0;

        // ��ǰ���������������ɢ��������ֵ����ԭʼ�ַ�����������Ӵ��ĳ���
        int maxLen = 1;
        // ԭʼ�ַ�����������Ӵ�����ʼλ�ã��� maxLen ����ͬʱ����
        int start = 0;

        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // ��һ�д����� Manacher �㷨�Ĺؼ����ڣ�Ҫ���ͼ�������
                p[i] = Math.min(maxRight - i, p[mirror]);
            }

            // ��һ�γ�����ɢ��������㣬����ɢ�Ĳ���ֱ�Ӽӵ� p[i] ��
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            // left >= 0 && right < sLen ��֤��Խ��
            // str.charAt(left) == str.charAt(right) ��ʾ������ɢ 1 ��
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;

            }
            // ���� maxRight �Ķ��壬���Ǳ������� i �� i + p[i] �������
            // ��� maxRight ��ֵԽ�󣬽������� i < maxRight ���жϵĿ����Ծ�Խ�������Ϳ����ظ�����֮ǰ�жϹ��Ļ�����Ϣ��
            if (i + p[i] > maxRight) {
                // maxRight �� center ��Ҫͬʱ����
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // ��¼������Ӵ��ĳ��Ⱥ���Ӧ����ԭʼ�ַ����е����
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * ����Ԥ�����ַ���
     *
     * @param s      ԭʼ�ַ���
     * @param divide �ָ��ַ�
     * @return ʹ�÷ָ��ַ������Ժ�õ����ַ���
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("�������������ݵķָ��ַ����������ַ����д��ڣ�");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }
}