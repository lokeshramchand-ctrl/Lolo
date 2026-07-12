/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> lol = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for(int i= 0; i < s.length() ; i++)
        {
            if(lol.containsKey(s.charAt(i))&& lol.get(s.charAt(i)) >= left)
            {
                left = lol.get(s.charAt(i)) + 1;
            }


            lol.put(s.charAt(i), i);
            int curr = i - left + 1;
            maxLength = Math.max(maxLength , curr);
        }
        return maxLength;
    }
}
// @lc code=end
