package com.mylovin.algo.leetcode0007;

public class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        StringBuilder res = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            res.append(sb.charAt(i));
        }
        if (res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }
        if (x < 0) {
            res.insert(0, "-");
            res.deleteCharAt(res.length() - 1);
        }
        if (res.length() == 10 && (Integer.MAX_VALUE + "").compareTo(res.toString()) < 0) {
            return 0;
        }

        if (res.length() == 11 && (Integer.MIN_VALUE + "").compareTo(res.toString()) < 0) {
            return 0;
        }
        return Integer.parseInt(res.toString());
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println((Integer.MIN_VALUE + "").compareTo("-0033333333".toString()));
        Object result = s.reverse(123);
        System.out.println(result);
    }
}
