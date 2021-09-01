package com.mylovin.algo.leetcode0004;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = (nums1.length + nums2.length);
        int[] nums = new int[total];
        int index = 0;
        int pos_a = 0, pos_b = 0;
        for (pos_a = 0, pos_b = 0; pos_a < nums1.length && pos_b < nums2.length; ) {
            if (nums1[pos_a] < nums2[pos_b]) {
                nums[index++] = nums1[pos_a++];
            } else {
                nums[index++] = nums2[pos_b++];
            }
        }
        while (pos_a < nums1.length) {
            nums[index++] = nums1[pos_a++];
        }
        while (pos_b < nums2.length) {
            nums[index++] = nums2[pos_b++];
        }
        if (total % 2 == 0) {
            return nums[total / 2 - 1] * 1.0 / 2.0 + nums[total / 2] * 1.0 / 2.0;
        } else {
            return nums[total / 2] * 1.0;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        double result = s.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 7});
        System.out.println(result);
    }
}
