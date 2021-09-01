
package com.mylovin.algo.leetcode0015;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        dfs(map, new LinkedList<>(), res);
        return res;
    }

    private void dfs(Map<Integer, Integer> map, List<Integer> visited, List<List<Integer>> res) {
        if (visited.size() == 3) {
            if (visited.stream().map(integer -> map.get(integer)).reduce(0, Integer::sum) == 0) {
                List<String> cur = visited.stream().map(i -> map.get(i) + "").collect(Collectors.toList());
                cur.sort(Comparator.comparingInt(o -> Integer.parseInt(o)));
                System.out.println(cur);
            }
            return;
        }
        for (int i = 0; i < map.size(); i++) {
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            dfs(map, visited, res);
            visited.remove(visited.size() - 1);
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] strs = new int[]{0, 3, 0, 1, 1, -1, -5, -5, 3, -3, -3, 0};
        Object result = s.threeSum(strs);
        System.out.println(result);
    }
}
