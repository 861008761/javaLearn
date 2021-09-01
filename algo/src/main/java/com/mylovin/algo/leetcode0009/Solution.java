package com.mylovin.algo.leetcode0009;

public class Solution {
    public boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        if (String.valueOf(x).equals(sb.reverse().toString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Object result = s.isPalindrome(121);// 003443 -0023456 +345
        System.out.println(result);
    }
}
