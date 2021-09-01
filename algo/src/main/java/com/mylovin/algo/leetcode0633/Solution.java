package com.mylovin.algo.leetcode0633;

public class Solution {
    public boolean judgeSquareSum(int c) {
        int bxb = 0;
        for (int a = 0; a * a <= c / 2; a++) {
            bxb = c - a * a;
            int b = (int) Math.sqrt(bxb);
            if (b * b == bxb) {
                return true;
            }
            /*
            double b = Math.sqrt(bxb);
            if (b - Math.floor(b) == 0) {
                return true;
            }*/
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Math.sqrt(4));

        System.out.println(s.judgeSquareSum(1));
        System.out.println(s.judgeSquareSum(2));
        System.out.println(s.judgeSquareSum(3));
        System.out.println(s.judgeSquareSum(4));
        System.out.println(s.judgeSquareSum(2147483646));
    }
}
