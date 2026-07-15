public class PermutationInString {

    public static void main(String[] args) {

        String s1 = "ab";
        String s2 = "eidbaooo";

        // ==========================================================
        // Problem:
        //
        // Return true if s2 contains any permutation of s1.
        //
        // Example
        //
        // s1 = "ab"
        //
        // Permutations
        //
        // ab
        // ba
        //
        // s2
        //
        // eidbaooo
        //
        // Window
        //
        // ba
        //
        // Answer = true
        // ==========================================================

        if (s1.length() > s2.length()) {
            System.out.println(false);
            return;
        }

        int[] s1Frequency = new int[26];
        int[] windowFrequency = new int[26];

        // ----------------------------------------------------------
        // Build frequency of s1.
        // ----------------------------------------------------------

        for (int i = 0; i < s1.length(); i++) {

            s1Frequency[s1.charAt(i) - 'a']++;
        }

        int windowSize = s1.length();

        // ----------------------------------------------------------
        // Build first window.
        //
        // Example
        //
        // ei
        // ----------------------------------------------------------

        for (int i = 0; i < windowSize; i++) {

            windowFrequency[s2.charAt(i) - 'a']++;
        }

        if (matches(s1Frequency, windowFrequency)) {

            System.out.println(true);
            return;
        }

        // ----------------------------------------------------------
        // Slide window.
        // ----------------------------------------------------------

        for (int right = windowSize; right < s2.length(); right++) {

            //---------------------------------------------
            // Remove left character.
            //---------------------------------------------

            char remove = s2.charAt(right - windowSize);

            windowFrequency[remove - 'a']--;

            //---------------------------------------------
            // Add new character.
            //---------------------------------------------

            char add = s2.charAt(right);

            windowFrequency[add - 'a']++;

            //---------------------------------------------
            // Compare frequencies.
            //---------------------------------------------

            if (matches(s1Frequency, windowFrequency)) {

                System.out.println(true);
                return;
            }
        }

        System.out.println(false);
    }

    static boolean matches(int[] a, int[] b) {

        for (int i = 0; i < 26; i++) {

            if (a[i] != b[i]) {

                return false;
            }
        }

        return true;
    }
}