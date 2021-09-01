package com.mylovin.algo.leetcode0003;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
/*        int left = 0, right = 0;
        int start = 0, len = 0;
        // abcav
        StringBuilder sb = new StringBuilder();
        while (right < s.length()) {
            String cur = s.charAt(right) + "";
            int index = sb.indexOf(cur);
            if (index != -1) {
                if (right - left > len) {
                    len = right - left;
                }
                left++;
                right = left;
                sb = new StringBuilder();
            } else {
                sb.append(cur);
                right++;
            }
        }
        return len > right - left ? len : right - left;*/

        int right = 0;
        int len = 0;
        StringBuilder sb = new StringBuilder();
        while (right < s.length()) {
            String cur = s.charAt(right) + "";
            if (sb.indexOf(cur) >= 0) {
                sb.delete(0, sb.indexOf(cur) + 1);
            }
            sb.append(cur);
            len = Math.max(len, sb.length());
            right++;
        }
        return len;
    }


    public String window(String source, String target) {
        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            map.put(source.charAt(i) + "", 0);
        }
        int contains = 0;

        while (right < source.length()) {
            String cur = source.charAt(right) + "";
            right++;
            if (target.indexOf(cur) != -1) {
                if (map.get(cur) == 0) {
                    contains++;
                }
                map.put(cur, map.get(cur) + 1);
            }
            while (contains == target.length()) {
                if (len > right - left) {
                    start = left;
                    len = right - left;
                }
                String now = source.charAt(left) + "";
                left++;
                if (target.indexOf(now) != -1) {
                    map.put(now, map.get(now) - 1);
                    if (map.get(now) == 0) {
                        contains--;
                    }
                }
            }
        }
        return source.substring(start, start + len);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int result = s.lengthOfLongestSubstring("aaa");
        System.out.println(result);

        //System.out.println(s.window("ABRTNCADFSDVBATTCBDA", "ABC"));
    }
}
