public class MaximumConsecutiveOnesIII {

    public static void main(String[] args) {

        int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        int k = 2;

        // ============================================================
        // Problem:
        //
        // You may flip AT MOST k zeros into ones.
        //
        // Return the maximum number of consecutive ones.
        //
        // Example:
        //
        // nums = [1,1,1,0,0,0,1,1,1,1,0]
        //
        // k = 2
        //
        // Answer = 6
        // ============================================================

        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // Intuition:
        //
        // Start from every possible index.
        //
        // Keep extending to the right.
        //
        // Count how many zeros appear.
        //
        // If zeros become greater than k,
        // stop this starting point.
        //
        // TC : O(n²)
        // SC : O(1)
        // ============================================================

        /*
         * int maxLength = 0;
         * 
         * // Try every possible starting index.
         * for (int left = 0; left < nums.length; left++) {
         * 
         * int zeroCount = 0;
         * 
         * // Extend window.
         * for (int right = left; right < nums.length; right++) {
         * 
         * // ---------------------------------------------
         * // RIGHT enters window.
         * //
         * // Example
         * //
         * // [1,1,1,0,0,0,1]
         * // L
         * // R
         * //
         * // Window
         * //
         * // [1,1,1,0]
         * // ---------------------------------------------
         * 
         * if (nums[right] == 0) {
         * 
         * zeroCount++;
         * 
         * // Example
         * //
         * // Window
         * //
         * // [1,1,1,0]
         * //
         * // zeroCount = 1
         * }
         * 
         * // Too many zeros.
         * if (zeroCount > k) {
         * 
         * // Example
         * //
         * // Window
         * //
         * // [1,1,1,0,0,0]
         * //
         * // zeroCount = 3
         * //
         * // Cannot flip
         * // more than k zeros.
         * break;
         * }
         * 
         * int currentLength = right - left + 1;
         * 
         * maxLength = Math.max(maxLength, currentLength);
         * }
         * }
         * 
         * System.out.println("Brute Force Answer : " + maxLength);
         */

        // ============================================================
        // APPROACH 2 : OPTIMAL (SLIDING WINDOW)
        //
        // Intuition:
        //
        // Instead of restarting from every left,
        // maintain ONE window.
        //
        // RIGHT expands.
        //
        // If zeroCount becomes greater than k,
        // move LEFT until window becomes valid.
        //
        // TC : O(n)
        // SC : O(1)
        // ============================================================

        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        // RIGHT expands the window.
        for (int right = 0; right < nums.length; right++) {

            // -----------------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // [1,1,1,0,0,0,1,1,1,1,0]
            // L
            // R
            //
            // Current Window
            //
            // [1,1,1,0]
            // -----------------------------------------------------

            if (nums[right] == 0) {

                zeroCount++;

                // Window
                //
                // [1,1,1,0]
                //
                // zeroCount = 1
            }

            // -----------------------------------------------------
            // Window became invalid.
            //
            // zeroCount > k
            //
            // Shrink from LEFT.
            // -----------------------------------------------------

            while (zeroCount > k) {

                // Example
                //
                // Window
                //
                // [1,1,1,0,0,0]
                //
                // zeroCount = 3
                //
                // Need to remove
                // elements from LEFT.

                if (nums[left] == 0) {

                    zeroCount--;

                    // One zero left the window.
                }

                // LEFT MOVES
                //
                // Before
                //
                // [1,1,1,0,0,0]
                // L
                //
                // After
                //
                // [1,1,0,0,0]
                // L

                left++;
            }

            // -----------------------------------------------------
            // Window is valid.
            //
            // zeroCount <= k
            //
            // Update answer.
            // -----------------------------------------------------

            int currentLength = right - left + 1;

            maxLength = Math.max(maxLength, currentLength);

            // Example
            //
            // left = 4
            // right = 9
            //
            // Window
            //
            // [0,0,1,1,1,1]
            //
            // Flip both zeros.
            //
            // [1,1,1,1,1,1]
            //
            // length = 6
        }

        System.out.println("Optimal Answer : " + maxLength);
    }
}