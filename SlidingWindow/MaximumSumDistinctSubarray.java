import java.util.HashMap;

public class MaximumSumDistinctSubarray {

    public static void main(String[] args) {

        int[] nums = { 1, 5, 4, 2, 9, 9, 9 };
        int k = 3;

        // Stores:
        // Number -> Frequency inside current window
        HashMap<Integer, Integer> frequency = new HashMap<>();

        int left = 0;
        long currentSum = 0;
        long maxSum = 0;

        for (int right = 0; right < nums.length; right++) {

            // -------------------------------------------------------
            // RIGHT MOVES
            //
            // Example:
            //
            // [1,5,4,2,9,9,9]
            // L
            // R
            //
            // Add current number into window.
            // -------------------------------------------------------

            currentSum += nums[right];

            frequency.put(
                    nums[right],
                    frequency.getOrDefault(nums[right], 0) + 1);

            // -------------------------------------------------------
            // If window size becomes greater than k,
            // remove one element from the LEFT.
            // -------------------------------------------------------

            if (right - left + 1 > k) {

                currentSum -= nums[left];

                frequency.put(
                        nums[left],
                        frequency.get(nums[left]) - 1);

                // Remove key if frequency becomes 0
                if (frequency.get(nums[left]) == 0) {
                    frequency.remove(nums[left]);
                }

                left++;
            }

            // -------------------------------------------------------
            // Window size is exactly k
            //
            // Check if every element is distinct.
            //
            // If frequency map size == k
            // every element occurs exactly once.
            // -------------------------------------------------------

            if (right - left + 1 == k && frequency.size() == k) {

                maxSum = Math.max(maxSum, currentSum);
            }
        }

        System.out.println(maxSum);
    }
}