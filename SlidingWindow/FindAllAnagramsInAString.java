import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        // ============================================================
        // Problem:
        //
        // Find all starting indices of p's anagrams in s.
        //
        // Example
        //
        // s = "cbaebabacd"
        //
        // p = "abc"
        //
        // Anagrams of "abc"
        //
        // abc
        // acb
        // bac
        // bca
        // cab
        // cba
        //
        // Output
        //
        // [0,6]
        // ============================================================

        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // Intuition
        //
        // Every possible window of size p.length()
        // can be a possible anagram.
        //
        // Build the frequency array for every window.
        //
        // Compare with p's frequency.
        //
        // If frequencies match,
        // store the starting index.
        //
        // TC : O((n-m+1) * m)
        // SC : O(1)
        //
        // n = s.length()
        // m = p.length()
        // ============================================================

        /*
         * List<Integer> answer = new ArrayList<>();
         * 
         * int[] pattern = new int[26];
         * 
         * // ---------------------------------------------
         * // Build frequency of pattern.
         * // ---------------------------------------------
         * 
         * for(int i=0;i<p.length();i++)
         * {
         * pattern[p.charAt(i)-'a']++;
         * }
         * 
         * // ---------------------------------------------
         * // Try every possible window.
         * // ---------------------------------------------
         * 
         * for(int left=0;
         * left<=s.length()-p.length();
         * left++)
         * {
         * 
         * int[] window=new int[26];
         * 
         * // -----------------------------------------
         * // Build current window.
         * //
         * // Example
         * //
         * // cba
         * // -----------------------------------------
         * 
         * for(int right=left;
         * right<left+p.length();
         * right++)
         * {
         * 
         * char current=s.charAt(right);
         * 
         * window[current-'a']++;
         * }
         * 
         * // -----------------------------------------
         * // Compare frequencies.
         * // -----------------------------------------
         * 
         * if(matches(pattern,window))
         * {
         * 
         * answer.add(left);
         * 
         * // Example
         * //
         * // cba
         * //
         * // Store
         * //
         * // 0
         * }
         * 
         * }
         * 
         * System.out.println("Brute Force : "+answer);
         */

        // ============================================================
        // APPROACH 2 : OPTIMAL (FIXED SIZE SLIDING WINDOW)
        //
        // Intuition
        //
        // Instead of rebuilding every window,
        //
        // Build ONLY the first window.
        //
        // Then slide the window.
        //
        // Every slide
        //
        // Remove left character
        //
        // Add right character
        //
        // Compare frequencies.
        //
        // TC : O(n)
        // SC : O(1)
        // ============================================================

        List<Integer> answer = new ArrayList<>();

        if (p.length() > s.length()) {

            System.out.println(answer);
            return;
        }

        int[] pattern = new int[26];
        int[] window = new int[26];

        // --------------------------------------------------------
        // Build frequency of pattern.
        //
        // Example
        //
        // Pattern
        //
        // abc
        //
        // a = 1
        // b = 1
        // c = 1
        // --------------------------------------------------------

        for (int i = 0; i < p.length(); i++) {

            pattern[p.charAt(i) - 'a']++;
        }

        // --------------------------------------------------------
        // Build FIRST window.
        //
        // Window
        //
        // cba
        // --------------------------------------------------------

        for (int i = 0; i < p.length(); i++) {

            window[s.charAt(i) - 'a']++;
        }

        // --------------------------------------------------------
        // Compare first window.
        // --------------------------------------------------------

        if (matches(pattern, window)) {

            answer.add(0);
        }

        // --------------------------------------------------------
        // Slide the window.
        // --------------------------------------------------------

        for (int right = p.length(); right < s.length(); right++) {

            // ----------------------------------------------------
            // Remove LEFT character.
            //
            // Example
            //
            // cba
            //
            // Remove
            //
            // c
            // ----------------------------------------------------

            char remove = s.charAt(right - p.length());

            window[remove - 'a']--;

            // ----------------------------------------------------
            // Add RIGHT character.
            //
            // Example
            //
            // cba
            //
            // becomes
            //
            // bae
            //
            // Add
            //
            // e
            // ----------------------------------------------------

            char add = s.charAt(right);

            window[add - 'a']++;

            // ----------------------------------------------------
            // Compare frequencies.
            //
            // If equal,
            // current window is an anagram.
            // ----------------------------------------------------

            if (matches(pattern, window)) {

                answer.add(right - p.length() + 1);

                // Example
                //
                // Window
                //
                // bac
                //
                // right = 8
                //
                // Start Index
                //
                // 8-3+1 = 6
            }
        }

        System.out.println("Optimal : " + answer);
    }

    // ============================================================
    // Compare two frequency arrays.
    //
    // Returns true
    //
    // if every character frequency matches.
    // ============================================================

    static boolean matches(int[] a, int[] b) {

        for (int i = 0; i < 26; i++) {

            if (a[i] != b[i]) {

                return false;
            }
        }

        return true;
    }
}