package com.mylovin.algo.leetcode0013;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int romanToInt(String s) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        for (String key : map.keySet()) {
            if (s.contains(key)) {
                res += map.get(key);
                s = s.replaceAll(key, "");
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                res += 1;
            } else if (s.charAt(i) == 'V') {
                res += 5;
            } else if (s.charAt(i) == 'X') {
                res += 10;
            } else if (s.charAt(i) == 'L') {
                res += 50;
            } else if (s.charAt(i) == 'C') {
                res += 100;
            } else if (s.charAt(i) == 'D') {
                res += 500;
            } else if (s.charAt(i) == 'M') {
                res += 1000;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        Object result = s.romanToInt("IV");
        System.out.println(result);
    }
}
