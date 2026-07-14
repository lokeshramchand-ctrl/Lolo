public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {

        String s = "AABABBA";
        int k = 1;

        // ============================================================
        // Problem:
        //
        // Replace at most k characters.
        //
        // Return the length of the longest substring
        // that can become all the same character.
        //
        // Example
        //
        // A A B A
        //
        // Replace B -> A
        //
        // A A A A
        //
        // Answer = 4
        // ============================================================

        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // Intuition:
        //
        // Start from every possible character.
        //
        // Extend the substring one character at a time.
        //
        // Maintain frequencies.
        //
        // Find the highest occurring character.
        //
        // Required Replacements
        //
        // =
        //
        // Window Length - Maximum Frequency
        //
        // If replacements become greater than k,
        // stop this starting point.
        //
        // TC : O(n²)
        // SC : O(1)
        // ============================================================

        /*
         * int answer = 0;
         * 
         * for (int left = 0; left < s.length(); left++) {
         * 
         * int[] frequency = new int[26];
         * 
         * int maxFrequency = 0;
         * 
         * for (int right = left; right < s.length(); right++) {
         * 
         * //-----------------------------------------------
         * // RIGHT enters window.
         * //
         * // Example
         * //
         * // A A B A
         * // L
         * // R
         * //
         * // Window
         * //
         * // A A B
         * //-----------------------------------------------
         * 
         * char current = s.charAt(right);
         * 
         * frequency[current - 'A']++;
         * 
         * maxFrequency = Math.max(
         * maxFrequency,
         * frequency[current - 'A']);
         * 
         * int windowLength = right - left + 1;
         * 
         * int replacementsNeeded =
         * windowLength - maxFrequency;
         * 
         * //-----------------------------------------------
         * // Too many replacements required.
         * //-----------------------------------------------
         * 
         * if (replacementsNeeded > k) {
         * 
         * break;
         * }
         * 
         * answer = Math.max(answer, windowLength);
         * }
         * }
         * 
         * System.out.println("Brute Force : " + answer);
         */

        // ============================================================
        // APPROACH 2 : OPTIMAL (SLIDING WINDOW)
        //
        // Intuition:
        //
        // Maintain ONE window.
        //
        // Expand using RIGHT.
        //
        // Track frequency of every character.
        //
        // Track highest frequency inside window.
        //
        // Window is INVALID when
        //
        // Window Length - Max Frequency > k
        //
        // Shrink until valid.
        //
        // TC : O(n)
        // SC : O(1)
        // ============================================================

        int[] frequency = new int[26];

        int left = 0;

        int maxFrequency = 0;

        int answer = 0;

        // RIGHT expands window.
        for (int right = 0; right < s.length(); right++) {

            // -------------------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // A A B A
            // L
            // R
            //
            // Current Window
            //
            // A A B
            // -------------------------------------------------------

            char current = s.charAt(right);

            // Increase frequency of current character.
            //
            // Example
            //
            // Current = 'A'
            //
            // frequency['A'-'A']
            //
            // frequency[0]++
            //
            // A : 3

            frequency[current - 'A']++;

            // Update highest occurring character.
            //
            // Example
            //
            // A = 3
            // B = 1
            //
            // maxFrequency = 3

            maxFrequency = Math.max(
                    maxFrequency,
                    frequency[current - 'A']);

            // -------------------------------------------------------
            // Window became invalid.
            //
            // Formula
            //
            // Window Length - Max Frequency > k
            // -------------------------------------------------------

            while ((right - left + 1) - maxFrequency > k) {

                // -----------------------------------------------
                // Remove LEFT character.
                // -----------------------------------------------

                char leftCharacter = s.charAt(left);

                frequency[leftCharacter - 'A']--;

                // LEFT MOVES
                //
                // Before
                //
                // A A B A
                // L
                //
                // After
                //
                // A B A
                // L

                left++;
            }

            // -------------------------------------------------------
            // Window is valid.
            // -------------------------------------------------------

            int currentLength = right - left + 1;

            answer = Math.max(answer, currentLength);

            // -------------------------------------------------------
            // Example
            //
            // Window
            //
            // A A B A
            //
            // Length = 4
            //
            // Max Frequency = 3
            //
            // Need
            //
            // 4-3 = 1 replacement
            //
            // Allowed.
            // -------------------------------------------------------
        }

        System.out.println("Optimal : " + answer);
    }
}