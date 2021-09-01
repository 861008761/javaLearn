package com.mylovin.algo.leetcode0001;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length && i != j; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }*/
        result = dfs(nums, target, 0);
        return result;
    }

    List<Integer> visited = new LinkedList<>();
    int[] result = new int[2];

    public int[] dfs(int[] nums, int target, int current) {
        visited.add(current);
        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(i)) {
                continue;
            }
            if (nums[current] + nums[i] == target) {
                result[0] = current;
                result[1] = i;
                return result;
            }
        }
        return dfs(nums, target, ++current);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        //int[] result = s.twoSum(nums, target);
        int[] result = s.dfs(nums, target, 0);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
