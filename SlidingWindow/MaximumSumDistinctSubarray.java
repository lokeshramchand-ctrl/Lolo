import java.util.HashMap;

public class MaximumSumDistinctSubarray {

    public static void main(String[] args) {

        int[] nums = { 1, 5, 4, 2, 9, 9, 9 };
        int k = 3;

        // ============================================================
        // Problem:
        //
        // Find the maximum sum of any subarray of EXACTLY size k
        // such that every element inside the window is DISTINCT.
        //
        // Example:
        //
        // nums = [1,5,4,2,9,9,9]
        // k = 3
        //
        // Valid Windows
        //
        // [1,5,4] -> Sum = 10
        // [5,4,2] -> Sum = 11
        // [4,2,9] -> Sum = 15 <-- Answer
        //
        // Invalid Windows
        //
        // [2,9,9]
        // [9,9,9]
        // ============================================================

        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // Intuition:
        //
        // Check every possible window of size k.
        //
        // For every window,
        // verify that all elements are distinct.
        //
        // If distinct,
        // calculate its sum.
        //
        // Update the maximum answer.
        //
        // TC : O(n * k)
        // SC : O(k)
        // ============================================================

        /*
         * long maxSum = 0;
         * 
         * // Try every possible window of size k.
         * for (int left = 0; left <= nums.length - k; left++) {
         * 
         * HashMap<Integer, Integer> frequency = new HashMap<>();
         * 
         * long currentSum = 0;
         * 
         * // Build current window.
         * for (int right = left; right < left + k; right++) {
         * 
         * // --------------------------------------------
         * // RIGHT enters current window.
         * //
         * // Example
         * //
         * // [1,5,4]
         * // --------------------------------------------
         * 
         * currentSum += nums[right];
         * 
         * frequency.put(
         * nums[right],
         * frequency.getOrDefault(nums[right], 0) + 1);
         * }
         * 
         * // If every number appeared exactly once,
         * // map size will be equal to k.
         * 
         * if (frequency.size() == k) {
         * 
         * maxSum = Math.max(maxSum, currentSum);
         * }
         * }
         * 
         * System.out.println("Brute Force Answer : " + maxSum);
         */

        // ============================================================
        // APPROACH 2 : OPTIMAL (FIXED SIZE SLIDING WINDOW)
        //
        // Intuition:
        //
        // Instead of rebuilding every window,
        //
        // Maintain ONE window.
        //
        // RIGHT adds one element.
        //
        // If window becomes larger than k,
        // remove LEFT element.
        //
        // Whenever window size becomes exactly k,
        // check whether every element is distinct.
        //
        // TC : O(n)
        // SC : O(k)
        // ============================================================

        HashMap<Integer, Integer> frequency = new HashMap<>();

        int left = 0;

        long currentSum = 0;
        long maxSum = 0;

        // RIGHT expands the window.
        for (int right = 0; right < nums.length; right++) {

            // -------------------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // [1,5,4,2,9,9,9]
            // L
            // R
            //
            // Current Window
            //
            // [1,5,4]
            // -------------------------------------------------------

            currentSum += nums[right];

            frequency.put(
                    nums[right],
                    frequency.getOrDefault(nums[right], 0) + 1);

            // -------------------------------------------------------
            // Window became larger than k.
            //
            // Remove one element from LEFT.
            // -------------------------------------------------------

            if (right - left + 1 > k) {

                currentSum -= nums[left];

                frequency.put(
                        nums[left],
                        frequency.get(nums[left]) - 1);

                // Frequency became zero.
                // Remove that number completely.

                if (frequency.get(nums[left]) == 0) {

                    frequency.remove(nums[left]);
                }

                // LEFT MOVES
                //
                // Before
                //
                // [1,5,4,2]
                // L
                //
                // After
                //
                // [5,4,2]
                // L

                left++;
            }

            // -------------------------------------------------------
            // Window size is exactly k.
            //
            // Check if every element is unique.
            // -------------------------------------------------------

            if (right - left + 1 == k &&
                    frequency.size() == k) {

                maxSum = Math.max(maxSum, currentSum);

                // Example
                //
                // Window
                //
                // [4,2,9]
                //
                // Sum = 15
            }
        }

        System.out.println("Optimal Answer : " + maxSum);
    }
}