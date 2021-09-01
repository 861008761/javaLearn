package com.mylovin.algo.leetcode0006;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String convert(String s, int numRows) {
        String res = "";
        if (s.length() <= 1 || numRows == 1) {
            return s;
        }
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int ans = i / (numRows - 1);
            int remains = i % (numRows - 1);
            if (ans % 2 == 0) { // 正向部分
                map.put(remains, map.getOrDefault(remains, "") + s.charAt(i));
            }
            if (ans % 2 != 0) { // 反向部分
                map.put(numRows - 1 - remains, map.getOrDefault(numRows - 1 - remains, "") + s.charAt(i));
            }
        }
        for (int i = 0; i < numRows; i++) {
            res += map.getOrDefault(i, "");
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String result = s.convert("ABCDEFG", 3);
        System.out.println(result);
    }
}
