public class FindMinimumInRotatedSortedArray {

    /*
     * ===============================================================
     * LeetCode 153 - Find Minimum in Rotated Sorted Array
     * ===============================================================
     *
     * Problem:
     * --------
     * Given a sorted array that has been rotated at some unknown pivot,
     * return the minimum element.
     *
     * Example:
     *
     * Original Array
     * --------------
     * [0,1,2,4,5,6,7]
     *
     * Rotated
     * -------
     * [4,5,6,7,0,1,2]
     *
     * Answer = 0
     *
     * ---------------------------------------------------------------
     * Observation
     * ---------------------------------------------------------------
     *
     * A rotated sorted array always contains TWO sorted parts.
     *
     * Example
     *
     * [4,5,6,7 | 0,1,2]
     *
     * Left Part
     * ----------
     * 4 5 6 7
     *
     * Right Part
     * ----------
     * 0 1 2
     *
     * The minimum element is always the FIRST element
     * of the second sorted part.
     *
     * We do NOT need to find the pivot explicitly.
     *
     * We only need to keep narrowing the search
     * until low == high.
     *
     * ===============================================================
     */

    public int findMin(int[] nums) {

        /*
         * Start searching across the whole array.
         */

        int low = 0;
        int high = nums.length - 1;

        /*
         * Continue until both pointers meet.
         *
         * When low == high,
         * that index is guaranteed to be
         * the minimum element.
         */

        while (low < high) {

            /*
             * Calculate the middle safely.
             */

            int mid = low + (high - low) / 2;

            /*
             * -------------------------------------------------------
             * Compare nums[mid] with nums[high]
             * -------------------------------------------------------
             *
             * Why compare with HIGH?
             *
             * Because nums[high] belongs to the right boundary
             * of our current search space.
             *
             * This comparison tells us
             * which side contains the minimum.
             */

            if (nums[mid] > nums[high]) {

                /*
                 * Example
                 *
                 * [4,5,6,7,0,1,2]
                 *
                 * low = 0
                 * high = 6
                 * mid = 3
                 *
                 * nums[mid] = 7
                 * nums[high] = 2
                 *
                 * Since
                 *
                 * 7 > 2
                 *
                 * mid lies in the LEFT sorted part.
                 *
                 * The minimum must be somewhere
                 * AFTER mid.
                 *
                 * Therefore discard everything
                 * including mid.
                 */

                low = mid + 1;

            } else {

                /*
                 * Example
                 *
                 * [4,5,6,7,0,1,2]
                 *
                 * low = 4
                 * high = 6
                 * mid = 5
                 *
                 * nums[mid] = 1
                 * nums[high] = 2
                 *
                 * Since
                 *
                 * 1 <= 2
                 *
                 * mid lies in the RIGHT sorted part.
                 *
                 * The minimum could be:
                 *
                 * nums[mid]
                 * OR
                 * somewhere before it.
                 *
                 * Therefore
                 * we CANNOT remove mid.
                 *
                 * Keep it.
                 */

                high = mid;
            }
        }

        /*
         * Eventually
         *
         * low == high
         *
         * That index is the minimum.
         */

        return nums[low];
    }
}

/*
=====================================================================
COMPLETE DRY RUN
=====================================================================

Example

nums = [4,5,6,7,0,1,2]

-----------------------------------------------------

Initial

low = 0

high = 6

Array

Index

0 1 2 3 4 5 6

Value

4 5 6 7 0 1 2

-----------------------------------------------------
Iteration 1
-----------------------------------------------------

mid = (0+6)/2 = 3

nums[mid] = 7

nums[high] = 2

Compare

7 > 2

YES

Meaning

mid lies in LEFT sorted part.

Minimum must be AFTER mid.

Move

low = mid + 1

low = 4

Search Space

[0,1,2]

-----------------------------------------------------
Iteration 2
-----------------------------------------------------

low = 4

high = 6

mid = (4+6)/2 = 5

nums[mid] = 1

nums[high] = 2

Compare

1 > 2 ?

NO

Meaning

mid belongs to RIGHT sorted part.

The minimum could be:

0

or

1

We cannot remove mid.

Move

high = mid

high = 5

Search Space

[0,1]

-----------------------------------------------------
Iteration 3
-----------------------------------------------------

low = 4

high = 5

mid = (4+5)/2 = 4

nums[mid] = 0

nums[high] = 1

Compare

0 > 1 ?

NO

Again,

mid belongs to the RIGHT sorted part.

Minimum may be exactly at mid.

Keep it.

high = mid

high = 4

-----------------------------------------------------

Now

low = 4

high = 4

Loop ends.

Return

nums[4]

=

0

Correct Answer.

=====================================================================
SECOND EXAMPLE
=====================================================================

nums = [3,4,5,1,2]

Iteration 1

low = 0

high = 4

mid = 2

nums[mid] = 5

nums[high] = 2

5 > 2

Move right

low = 3

-----------------------------------------------------

low = 3

high = 4

mid = 3

nums[mid] = 1

nums[high] = 2

1 > 2 ?

NO

Keep mid.

high = 3

-----------------------------------------------------

low = 3

high = 3

Answer

1

=====================================================================
INTUITION
=====================================================================

Question:

Why compare with nums[high]?

Because nums[high] helps us identify whether mid is in:

LEFT sorted portion

or

RIGHT sorted portion.

If

nums[mid] > nums[high]

↓

mid is definitely in LEFT sorted part.

Minimum must be to the right.

-----------------------------------------------------

If

nums[mid] <= nums[high]

↓

mid is in RIGHT sorted part.

Minimum could be mid itself.

So we keep mid.

=====================================================================
TIME COMPLEXITY
=====================================================================

Every iteration removes half the search space.

Time

O(log n)

=====================================================================
SPACE COMPLEXITY
=====================================================================

O(1)

=====================================================================
KEY IDEA TO REMEMBER
=====================================================================

This problem is NOT searching for a target.

Instead, every iteration asks one question:

"Is mid in the left sorted half or the right sorted half?"

If mid is in the left sorted half,
discard the left half.

If mid is in the right sorted half,
keep mid because it could be the minimum.

Eventually,

low == high

and that index is the minimum.
*/
