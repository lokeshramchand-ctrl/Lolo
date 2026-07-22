public class CapacityToShipPackages {

    /*
     * ============================================================
     * LeetCode 1011 - Capacity To Ship Packages Within D Days
     * ============================================================
     *
     * Binary Search on Answer
     *
     * We are NOT searching inside the array.
     *
     * We are searching for the minimum ship capacity that can ship
     * every package within the given number of days.
     *
     * ------------------------------------------------------------
     * Search Space
     * ------------------------------------------------------------
     *
     * Minimum Capacity
     *
     * = Maximum weight in the array
     *
     * Why?
     *
     * Because the ship must at least carry the heaviest package.
     *
     * ------------------------------------------------------------
     *
     * Maximum Capacity
     *
     * = Sum of all package weights
     *
     * Why?
     *
     * Because in the worst case we can carry every package in one day.
     *
     * ------------------------------------------------------------
     *
     * Binary Search
     *
     * Guess a ship capacity.
     *
     * Check whether that capacity can ship everything within
     * the given number of days.
     *
     * If YES
     *      try a smaller capacity.
     *
     * If NO
     *      increase the capacity.
     *
     * ============================================================
     */

    public int shipWithinDays(int[] weights, int days) {

        /*
         * Lowest possible answer.
         */
        int low = max(weights);

        /*
         * Highest possible answer.
         */
        int high = sum(weights);

        /*
         * Binary Search on Answer
         */
        while (low <= high) {

            /*
             * Candidate ship capacity.
             */
            int mid = low + (high - low) / 2;

            /*
             * Can this capacity finish shipping
             * within the allowed number of days?
             */
            if (isPossible(weights, days, mid)) {

                /*
                 * Capacity works.
                 *
                 * Try finding an even smaller capacity.
                 */
                high = mid - 1;

            } else {

                /*
                 * Capacity is too small.
                 *
                 * Increase the ship capacity.
                 */
                low = mid + 1;
            }
        }

        /*
         * When Binary Search finishes,
         * low points to the smallest valid capacity.
         */
        return low;
    }

    /*
     * ------------------------------------------------------------
     * Calculates the sum of all package weights.
     *
     * Used as the upper limit of Binary Search.
     * ------------------------------------------------------------
     */
    private int sum(int[] weights) {

        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
        }

        return sum;
    }

    /*
     * ------------------------------------------------------------
     * Finds the heaviest package.
     *
     * Used as the lower limit of Binary Search.
     * ------------------------------------------------------------
     */
    private int max(int[] weights) {

        int max = weights[0];

        for (int i = 1; i < weights.length; i++) {

            if (weights[i] > max) {
                max = weights[i];
            }
        }

        return max;
    }

    /*
     * ============================================================
     * isPossible()
     * ============================================================
     *
     * Question:
     *
     * If the ship capacity is 'capacity',
     * can we ship every package
     * within the given number of days?
     *
     * Return
     *
     * true  -> Capacity works.
     *
     * false -> Capacity is too small.
     *
     * ------------------------------------------------------------
     *
     * Unlike Koko Eating Bananas,
     * there is NO mathematical formula here.
     *
     * Instead,
     * we simulate loading packages exactly as described
     * in the problem statement.
     *
     * ============================================================
     */
    private boolean isPossible(int[] weights, int days, int capacity) {

        /*
         * Shipping starts on Day 1.
         */
        int days_used = 1;

        /*
         * Current weight loaded
         * on today's ship.
         */
        int curr_weight = 0;

        /*
         * Process every package
         * in the given order.
         *
         * Packages cannot be reordered.
         */
        for (int i = 0; i < weights.length; i++) {

            /*
             * Can this package fit into
             * today's ship?
             */
            if (curr_weight + weights[i] <= capacity) {

                /*
                 * Yes.
                 *
                 * Load it.
                 */
                curr_weight += weights[i];

            } else {

                /*
                 * No.
                 *
                 * Today's ship is full.
                 *
                 * Start a new day.
                 */
                days_used++;

                /*
                 * This package becomes
                 * the first package
                 * of the next day.
                 */
                curr_weight = weights[i];
            }
        }

        /*
         * If we used fewer or equal days,
         * this capacity is valid.
         */
        return days_used <= days;
    }

}

/*
==================================================================
COMPLETE DRY RUN
==================================================================

Input

weights

[1,2,3,4,5,6,7,8,9,10]

days = 5

--------------------------------------------------

Search Space

Maximum Weight = 10

Sum of Weights = 55

Binary Search

low = 10

high = 55

--------------------------------------------------

Suppose

mid = 15

Question

Can ship capacity = 15
finish within 5 days?

Call

isPossible(weights,5,15)

--------------------------------------------------

Initially

days_used = 1

curr_weight = 0

--------------------------------------------------

Package = 1

0 + 1 <= 15

Load

curr_weight = 1

--------------------------------------------------

Package = 2

1 + 2 <= 15

curr_weight = 3

--------------------------------------------------

Package = 3

curr_weight = 6

--------------------------------------------------

Package = 4

curr_weight = 10

--------------------------------------------------

Package = 5

curr_weight = 15

Ship exactly full.

--------------------------------------------------

Package = 6

15 + 6 > 15

Cannot load.

Start Day 2.

days_used = 2

curr_weight = 6

--------------------------------------------------

Package = 7

6 + 7 = 13

Load

--------------------------------------------------

Package = 8

13 + 8 > 15

Start Day 3

days_used = 3

curr_weight = 8

--------------------------------------------------

Package = 9

8 + 9 > 15

Start Day 4

days_used = 4

curr_weight = 9

--------------------------------------------------

Package = 10

9 + 10 > 15

Start Day 5

days_used = 5

curr_weight = 10

--------------------------------------------------

Finished.

days_used = 5

Allowed Days = 5

5 <= 5

Return TRUE

--------------------------------------------------

Binary Search receives TRUE.

This means

Capacity = 15

works.

Try a smaller capacity.

high = mid - 1

--------------------------------------------------

Eventually Binary Search converges.

Answer

15

==================================================================
TIME COMPLEXITY
==================================================================

Finding Maximum

O(n)

Finding Sum

O(n)

Each isPossible()

O(n)

Binary Search

O(log(sum - max))

Overall

O(n × log(sum(weights)))

==================================================================
SPACE COMPLEXITY
==================================================================

O(1)

==================================================================
PATTERN
==================================================================

This is Binary Search on Answer.

Search Space

[maxWeight ... sumWeights]

↓

Guess a capacity

↓

Simulate loading packages

↓

If possible

Search LEFT

↓

Else

Search RIGHT

Only the implementation of isPossible() changes from one Binary Search on
Answer problem to another. The binary search loop itself remains almost
identical.
*/