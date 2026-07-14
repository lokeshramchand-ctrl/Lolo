import java.util.HashMap;

public class FruitsIntoBaskets {

    public static void main(String[] args) {

        int[] fruits = { 1, 2, 3, 2, 2 };

        // ============================================================
        // Problem:
        //
        // We have only TWO baskets.
        // Each basket can contain ONLY ONE TYPE of fruit.
        //
        // Find the longest CONTINUOUS subarray containing
        // AT MOST TWO DISTINCT fruit types.
        //
        // Example:
        //
        // [1,2,3,2,2]
        //
        // Answer:
        //
        // [2,3,2,2]
        //
        // Length = 4
        // ============================================================

        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // Intuition:
        //
        // Start from every possible tree.
        //
        // Keep collecting fruits until a third fruit type appears.
        //
        // Then stop and try the next starting tree.
        //
        // TC : O(n²)
        // SC : O(1)
        // (HashMap stores at most 3 fruit types before breaking.)
        // ============================================================

        /*
         * int maxLength = 0;
         * 
         * // Try every possible starting index.
         * for (int left = 0; left < fruits.length; left++) {
         * 
         * HashMap<Integer, Integer> basket = new HashMap<>();
         * 
         * // Extend the window.
         * for (int right = left; right < fruits.length; right++) {
         * 
         * // ---------------------------------------------
         * // RIGHT picks current fruit.
         * //
         * // Example
         * //
         * // [1,2,3,2,2]
         * // L
         * // R
         * //
         * // Window
         * //
         * // [1,2,3]
         * // ---------------------------------------------
         * 
         * basket.put(
         * fruits[right],
         * basket.getOrDefault(fruits[right], 0) + 1);
         * 
         * // Basket contains more than two fruit types.
         * if (basket.size() > 2) {
         * 
         * // Example
         * //
         * // Basket
         * //
         * // {1,2,3}
         * //
         * // Three fruit types.
         * //
         * // Cannot continue.
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
        // maintain ONE sliding window.
        //
        // Expand using RIGHT.
        //
        // Whenever basket becomes invalid
        // (more than two fruit types),
        //
        // shrink from LEFT until valid again.
        //
        // TC : O(n)
        // SC : O(1)
        // (HashMap stores at most 3 keys.)
        // ============================================================

        HashMap<Integer, Integer> basket = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        // RIGHT expands the window.
        for (int right = 0; right < fruits.length; right++) {

            // -----------------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // [1,2,3,2,2]
            // L
            // R
            //
            // Current Window
            //
            // [1,2,3]
            // -----------------------------------------------------

            basket.put(
                    fruits[right],
                    basket.getOrDefault(fruits[right], 0) + 1);

            // -----------------------------------------------------
            // Window became invalid.
            //
            // More than two fruit types.
            //
            // Shrink from LEFT.
            // -----------------------------------------------------

            while (basket.size() > 2) {

                // Example
                //
                // Basket
                //
                // 1 -> 1
                // 2 -> 1
                // 3 -> 1
                //
                // Remove left fruit.

                basket.put(
                        fruits[left],
                        basket.get(fruits[left]) - 1);

                // If frequency becomes zero,
                // that fruit completely disappeared
                // from the current window.

                if (basket.get(fruits[left]) == 0) {

                    basket.remove(fruits[left]);
                }

                // LEFT MOVES
                //
                // Before
                //
                // [1,2,3]
                // L
                //
                // After
                //
                // [2,3]
                // L

                left++;
            }

            // -----------------------------------------------------
            // Window is valid.
            //
            // Basket contains
            // at most two fruit types.
            // -----------------------------------------------------

            int currentLength = right - left + 1;

            maxLength = Math.max(maxLength, currentLength);

            // Example
            //
            // left = 1
            // right = 4
            //
            // Window
            //
            // [2,3,2,2]
            //
            // length = 4
        }

        System.out.println("Optimal Answer : " + maxLength);
    }
}