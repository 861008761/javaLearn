
package com.mylovin.algo.leetcode0014;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            shortest = Math.min(shortest, strs[i].length());
        }
        int idx = 0;
        for (idx = 0; idx < shortest; idx++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(idx) != strs[0].charAt(idx)) {
                    return strs[0].substring(0, idx);
                }
            }
        }
        return strs[0].substring(0, idx);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String[] strs = new String[]{"abab", "ab", "a"};
        Object result = s.longestCommonPrefix(strs);
        System.out.println(result);
    }
}
