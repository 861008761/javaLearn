package com.mylovin.algo.leetcode0005;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            newStr.append(s.charAt(i) + "_");
        }
        newStr.deleteCharAt(newStr.length() - 1);

        for (int i = 0; i < newStr.length(); i++) {
            String hw = huiwei(newStr.toString(), i).replaceAll("_", "");
            if (hw.length() >= res.length()) {
                res = hw;
            }
        }
        return res;

        /*String res = "";
        int left = 0, right = 0, len = 0;
        char lastCHar = s.charAt(0);
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (lastCHar == s.charAt(i)) {
                right++;
            } else {
                len = Math.max(len, right - left);
                if (right - left >= len) {
                    map.put(len, s.substring(left, right));
                }

                left = right;
                lastCHar = s.charAt(i);
                right++;
            }

            String hw = huiwei(s, i);
            if (hw.length() >= res.length()) {
                res = hw;
            }
        }
        len = Math.max(len, right - left);
        if (right - left >= len) {
            map.put(len, s.substring(left, right));
        }
        if (map.get(len).length() > res.length()) {
            return map.get(len);
        }
        return res;*/
    }

    private String huiwei(String s, int index) {
        int left = index, right = index;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }

    /**
     * babad   1 1  0 2 -1 3
     * bab
     * <p>
     * cbbd
     * bb
     * <p>
     * a
     * a
     * <p>
     * ac
     * a
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        String result = s.longestPalindrome("arra");
        System.out.println(result);
    }
}
