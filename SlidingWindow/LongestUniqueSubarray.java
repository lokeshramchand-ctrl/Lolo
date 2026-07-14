
import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Set;

public class LongestUniqueSubarray {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 2, 1, 4, 2, 4 };

        // ============================================================
        // Approach 1: Sliding Window + HashSet
        // TC: O(n) -> Each element is added and removed at most once.
        // SC: O(n) -> HashSet may store all unique elements.
        // ============================================================

        /*
         * Set<Integer> window = new HashSet<>();
         * 
         * int left = 0;
         * int maxLength = 0;
         * 
         * for (int i = 0; i < arr.length; i++) {
         * 
         * while (window.contains(arr[i])) {
         * window.remove(arr[left]);
         * left++;
         * }
         * 
         * window.add(arr[i]);
         * 
         * int currentLength = i - left + 1;
         * maxLength = Math.max(maxLength, currentLength);
         * }
         * 
         * System.out.println("Longest Length = " + maxLength);
         */

        // ============================================================
        // Approach 2: Sliding Window + Last Seen Index (HashMap)
        // TC: O(n) -> Each element is processed exactly once.
        // SC: O(n) -> HashMap stores the last index of unique elements.
        // ============================================================

        HashMap<Integer, Integer> lastSeen = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {

            // Duplicate found inside current window
            if (lastSeen.containsKey(arr[i]) && lastSeen.get(arr[i]) >= left) {
                left = lastSeen.get(arr[i]) + 1;
            }

            // Update latest index
            lastSeen.put(arr[i], i);

            int currentLength = i - left + 1;
            maxLength = Math.max(maxLength, currentLength);
        }

        System.out.println("Longest Length = " + maxLength);
    }
}