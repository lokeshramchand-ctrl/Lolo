/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            while(window.contains(s.charAt(i)))
            {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(s.charAt(i));
            int currlength = i - left + 1;
            maxLength = Math.max(maxLength , currlength);

        }
        return maxLength;
    }
}
// @lc code=end
