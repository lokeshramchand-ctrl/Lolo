/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++)
        {
                                int sum = target - nums[i];
                          if(map.containsKey(sum))
            {
                return new int[]{map.get(sum),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }
}
// @lc code=end

