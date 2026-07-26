
public class FindPeakElement {

    /*
     * =====================================================================
     * LeetCode 162 - Find Peak Element
     * LeetCode 852 - Peak Index in a Mountain Array
     * =====================================================================
     *
     * Surprisingly, BOTH problems use the EXACT SAME Binary Search.
     *
     * ---------------------------------------------------------------------
     * WHY?
     * ---------------------------------------------------------------------
     *
     * LeetCode 162
     * -------------
     * Find ANY peak.
     *
     * Example
     *
     * nums = [1,2,1,3,5,6,4]
     *
     * Peaks are:
     *
     *      2
     *      6
     *
     * Either answer is accepted.
     *
     * ---------------------------------------------------------------------
     *
     * LeetCode 852
     * -------------
     * Mountain Array
     *
     * Example
     *
     * nums = [0,2,5,8,10,7,3,1]
     *
     * There is ONLY ONE peak.
     *
     * Therefore,
     * the exact same algorithm
     * automatically converges
     * to that unique peak.
     *
     * =====================================================================
     * INTUITION
     * =====================================================================
     *
     * Instead of asking
     *
     * "Where is the peak?"
     *
     * we ask
     *
     * "Am I currently moving uphill
     *  or downhill?"
     *
     * ---------------------------------------------------------------------
     *
     * If
     *
     * nums[mid] < nums[mid+1]
     *
     * Example
     *
     * 3 -> 5
     *
     * We are climbing.
     *
     * Therefore,
     * a peak MUST exist
     * somewhere to the RIGHT.
     *
     * Move right.
     *
     * ---------------------------------------------------------------------
     *
     * If
     *
     * nums[mid] > nums[mid+1]
     *
     * Example
     *
     * 7 -> 4
     *
     * We are descending.
     *
     * A peak is either
     *
     * nums[mid]
     *
     * OR
     *
     * somewhere to the LEFT.
     *
     * Therefore,
     * keep mid.
     *
     * =====================================================================
     */

    public int findPeakElement(int[] nums) {

        /*
         * Initially,
         * search the whole array.
         */

        int low = 0;
        int high = nums.length - 1;

        /*
         * We continue until only
         * ONE candidate remains.
         *
         * Unlike normal Binary Search,
         * we are NOT searching
         * for an exact value.
         */

        while (low < high) {

            /*
             * Calculate middle safely.
             */

            int mid = low + (high - low) / 2;

            /*
             * ----------------------------------------------------------
             * Compare mid with its RIGHT neighbour.
             * ----------------------------------------------------------
             */

            if (nums[mid] < nums[mid + 1]) {

                /*
                 * Example
                 *
                 * 4 -> 7
                 *
                 * Increasing.
                 *
                 * We are climbing.
                 *
                 * Peak cannot be
                 * on the left.
                 *
                 * Therefore
                 *
                 * discard left half
                 * INCLUDING mid.
                 */

                low = mid + 1;

            } else {

                /*
                 * Example
                 *
                 * 8 -> 5
                 *
                 * Descending.
                 *
                 * Peak could be
                 * exactly at mid.
                 *
                 * Therefore
                 * DO NOT discard mid.
                 */

                high = mid;
            }
        }

        /*
         * Eventually
         *
         * low == high
         *
         * Only one candidate remains.
         *
         * That candidate
         * is guaranteed
         * to be a peak.
         */

        return low;
    }
}

/*
====================================================================
DRY RUN 1 (LeetCode 162)
====================================================================

nums

[1,2,1,3,5,6,4]

Index

0 1 2 3 4 5 6

----------------------------------------------------

Initial

low = 0

high = 6

----------------------------------------------------
Iteration 1
----------------------------------------------------

mid = (0+6)/2

mid = 3

nums[mid] = 3

nums[mid+1] = 5

Compare

3 < 5

Increasing.

We are climbing.

Move Right.

low = 4

----------------------------------------------------

Search Space

[5,6,4]

----------------------------------------------------
Iteration 2
----------------------------------------------------

low = 4

high = 6

mid = 5

nums[mid] = 6

nums[mid+1] = 4

Compare

6 > 4

Descending.

Peak could be

6

Keep mid.

high = 5

----------------------------------------------------

Search Space

[5,6]

----------------------------------------------------
Iteration 3
----------------------------------------------------

low = 4

high = 5

mid = 4

nums[mid] = 5

nums[mid+1] = 6

Compare

5 < 6

Still climbing.

Move Right.

low = 5

----------------------------------------------------

low = high = 5

Loop Ends.

Return

5

Peak Value

6

====================================================================
DRY RUN 2 (Mountain Array - LC 852)
====================================================================

nums

[0,2,5,8,10,7,3,1]

----------------------------------------------------

Initial

low = 0

high = 7

----------------------------------------------------
Iteration 1
----------------------------------------------------

mid = 3

nums[mid] = 8

nums[mid+1] = 10

8 < 10

Increasing.

Move Right.

low = 4

----------------------------------------------------

Search Space

[10,7,3,1]

----------------------------------------------------
Iteration 2
----------------------------------------------------

low = 4

high = 7

mid = 5

nums[mid] = 7

nums[mid+1] = 3

7 > 3

Descending.

Keep mid.

high = 5

----------------------------------------------------

Search Space

[10,7]

----------------------------------------------------
Iteration 3
----------------------------------------------------

low = 4

high = 5

mid = 4

nums[mid] = 10

nums[mid+1] = 7

10 > 7

Descending.

Keep mid.

high = 4

----------------------------------------------------

low = high = 4

Return Index

4

Peak Value

10

====================================================================
WHY DO WE KEEP MID?
====================================================================

Suppose

1 3 5 7 4 2

          ^
         mid

Compare

7

and

4

We are descending.

Could 7 itself be the peak?

YES.

Therefore,

we MUST keep it.

So,

high = mid

NOT

high = mid - 1

====================================================================
WHY DO WE REMOVE MID WHEN GOING RIGHT?
====================================================================

Suppose

1 3 5 8

        ^
       mid

Compare

5

and

8

Since

5 < 8

5 can NEVER be a peak.

Its right neighbour
is already larger.

Therefore,

discard mid.

low = mid + 1

====================================================================
TIME COMPLEXITY
====================================================================

Every iteration removes half
of the search space.

Time

O(log n)

====================================================================
SPACE COMPLEXITY
====================================================================

O(1)

====================================================================
GENERIC TEMPLATE
====================================================================

while (low < high) {

    int mid = low + (high - low) / 2;

    if (nums[mid] < nums[mid + 1]) {

        low = mid + 1;

    } else {

        high = mid;
    }
}

return low;

====================================================================
INTERVIEW MEMORY TRICK
====================================================================

Never think

"Where is the peak?"

Instead ask

"What is the slope?"

Increasing slope

↓

Move Right

----------------------------------

Decreasing slope

↓

Move Left

(Keep mid because it could already be the peak.)

Eventually,

low == high

That position is guaranteed
to be a peak.
*/
