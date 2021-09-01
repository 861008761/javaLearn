package com.mylovin.algo.leetcode0012;

public class Solution {
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int remainder = 0; // 余数
        int radix = 10;
        int recursionTimes = 0;
        if (1 <= num && num <= 3999) {
            while (true) {
                recursionTimes++;
                remainder = num % radix;
                makeStr(recursionTimes, remainder, res);
                num = num / radix;
                if (num == 0) {
                    break;
                }
            }
            return res.toString();
        }
        return "";
    }

    private void makeStr(int recursionTimes, int curNum, StringBuilder res) {
        if (curNum == 4 || curNum == 9) {
            switch (recursionTimes) {
                case 1:
                    res.insert(0, curNum == 4 ? "IV" : "IX");
                    break;
                case 2:
                    res.insert(0, curNum == 4 ? "XL" : "XC");
                    break;
                case 3:
                    res.insert(0, curNum == 4 ? "CD" : "CM");
                    break;
            }
            return;
        }
        for (int i = 0; i < curNum; i++) {
            switch (recursionTimes) {
                case 1:
                    res.insert(0, "I");
                    break;
                case 2:
                    res.insert(0, "X");
                    break;
                case 3:
                    res.insert(0, "C");
                    break;
                case 4:
                    res.insert(0, "M");
                    break;
            }
        }
        res = res.indexOf("IIIII") != -1 ? res.replace(res.indexOf("IIIII"), res.indexOf("IIIII") + 5, "V") : res;
        res = res.indexOf("XXXXX") != -1 ? res.replace(res.indexOf("XXXXX"), res.indexOf("XXXXX") + 5, "L") : res;
        res = res.indexOf("CCCCC") != -1 ? res.replace(res.indexOf("CCCCC"), res.indexOf("CCCCC") + 5, "D") : res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Object result = s.intToRoman(58);
        System.out.println(result);
    }
}
