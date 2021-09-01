package com.mylovin.algo.leetcode0011;

public class Solution {
    // 这种复杂度较高，超时。官方示例使用双指针
    public int maxArea(int[] height) {
        /*
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }*/
        int res = 0;
        int left = 0, right = height.length - 1;
        int h = 0, b = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                h = height[left];
                b = right - left;
                left++;
            } else {
                h = height[right];
                b = right - left;
                right--;
            }
            res = Math.max(res, h * b);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Object result = s.maxArea(new int[]{1, 3, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(result);
    }
}
