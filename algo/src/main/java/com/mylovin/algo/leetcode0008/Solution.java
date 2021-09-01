package com.mylovin.algo.leetcode0008;

public class Solution {
    public int myAtoi(String s) {
        char opr = '0';// 符号部分 默认为空
        s = s.trim();
        if (s.length() == 0) { //对应用例：""，没有操作符
            return 0;
        }
        opr = (s.charAt(0) == '+' ? '+' : opr); // 如果是+
        opr = (s.charAt(0) == '-' ? '-' : opr); // 如果是-
        if (s.charAt(0) == '+' || s.charAt(0) == '-') { // 去掉符号，补0，保证如果用例是：“+”也是可以的
            s = "0" + s.substring(1);
        }
        // 截取数字部分 033ds35 -> 033
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            }
            sb.append(s.charAt(i));
        }
        int res = 0;
        try {
            res = Integer.valueOf(sb.insert(0, opr).toString()); // 把符号带上之后解析，如果报错，说明不在整数范围内
        } catch (Exception e) {
            res = (opr == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Object result = s.myAtoi("k");// 003443 -0023456 +345
        System.out.println(result);
    }
}
