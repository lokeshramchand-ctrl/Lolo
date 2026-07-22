
public class SmallestDivisor {

    /*
     * ============================================================
     * LeetCode 1283 - Find the Smallest Divisor Given a Threshold
     * ============================================================
     *
     * Binary Search on Answer
     *
     * ------------------------------------------------------------
     * Problem
     * ------------------------------------------------------------
     *
     * We are given an array of positive integers.
     *
     * nums = [1,2,5,9]
     *
     * We choose one divisor.
     *
     * Example:
     *
     * divisor = 5
     *
     * Every number is divided by the divisor.
     *
     * IMPORTANT:
     *
     * We always round UP.
     *
     * ceil(nums[i] / divisor)
     *
     * Example
     *
     * ceil(9 / 5)
     *
     * = 2
     *
     * Then we add every rounded value.
     *
     * If the total sum is less than or equal to threshold,
     * then the divisor works.
     *
     * We need the SMALLEST divisor that works.
     *
     * ============================================================
     */

    public int smallestDivisor(int[] nums, int threshold) {

        /*
         * --------------------------------------------------------
         * Search Space
         * --------------------------------------------------------
         *
         * Lowest divisor
         *
         * = 1
         *
         * Highest divisor
         *
         * = Maximum number in the array
         *
         * Why?
         *
         * If divisor becomes larger than the maximum element,
         * every division becomes 1.
         */

        int low = 1;
        int high = max(nums);

        /*
         * Binary Search on Answer
         */

        while (low <= high) {

            /*
             * Candidate divisor
             */

            int mid = low + (high - low) / 2;

            /*
             * Does this divisor satisfy the threshold?
             */

            if (isPossible(nums, threshold, mid)) {

                /*
                 * This divisor works.
                 *
                 * Try finding an even smaller divisor.
                 */

                high = mid - 1;

            } else {

                /*
                 * Divisor is too small.
                 *
                 * Small divisor
                 *
                 * =>
                 * Bigger quotients
                 *
                 * =>
                 * Bigger total sum
                 *
                 * Need a larger divisor.
                 */

                low = mid + 1;
            }
        }

        /*
         * Binary Search finishes.
         *
         * low points to the smallest valid divisor.
         */

        return low;
    }

    /*
     * ------------------------------------------------------------
     * Finds maximum element.
     * ------------------------------------------------------------
     */

    private int max(int[] nums) {

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

    /*
     * ============================================================
     * isPossible()
     * ============================================================
     *
     * Question
     *
     * Suppose divisor = mid.
     *
     * Divide every number.
     *
     * Round UP.
     *
     * Add everything.
     *
     * If total <= threshold
     *
     * return true
     *
     * else
     *
     * return false
     *
     * ============================================================
     */

    private boolean isPossible(int[] nums,
                               int threshold,
                               int divisor) {

        /*
         * Total after division.
         */

        int total = 0;

        /*
         * Divide every element.
         */

        for (int num : nums) {

            /*
             * ceil(num / divisor)
             *
             * Integer trick
             *
             * (num + divisor - 1) / divisor
             *
             * Example
             *
             * num = 9
             * divisor = 5
             *
             * (9 + 5 - 1) / 5
             *
             * = 13 / 5
             *
             * = 2
             */

            total += (num + divisor - 1) / divisor;

            /*
             * Optional Optimization
             *
             * Once total exceeds threshold,
             * no need to continue.
             */

            if (total > threshold) {
                return false;
            }
        }

        return true;
    }

}

/*
==================================================================
COMPLETE DRY RUN
==================================================================

Example

nums = [1,2,5,9]

threshold = 6

--------------------------------------------------

Search Space

low = 1

high = 9

--------------------------------------------------

Iteration 1

mid

= (1+9)/2

=5

Question

Does divisor = 5 work?

Call

isPossible(nums,6,5)

--------------------------------------------------

total = 0

Number = 1

ceil(1/5)

=1

total=1

----------------

Number = 2

ceil(2/5)

=1

total=2

----------------

Number = 5

ceil(5/5)

=1

total=3

----------------

Number = 9

ceil(9/5)

=2

total=5

----------------

Finished

5 <= 6

Return TRUE

Binary Search

Since divisor 5 works,

try smaller.

high = 4

==================================================

Iteration 2

low =1

high=4

mid=2

Divisor=2

--------------------------------------------------

1 -> 1

2 ->1

5 ->3

9 ->5

Total

1+1+3+5

=10

10>6

Return FALSE

Need bigger divisor.

low=3

==================================================

Iteration 3

low=3

high=4

mid=3

--------------------------------------------------

1 ->1

2 ->1

5 ->2

9 ->3

Total

1+1+2+3

=7

7>6

FALSE

Need larger divisor.

low=4

==================================================

Iteration 4

low=4

high=4

mid=4

--------------------------------------------------

1 ->1

2 ->1

5 ->2

9 ->3

Total

1+1+2+3

=7

FALSE

Need larger divisor.

low=5

==================================================

Loop Ends

low=5

high=4

Return

5

==================================================================
TIME COMPLEXITY
==================================================================

Finding Maximum

O(n)

Binary Search

O(log(max(nums)))

Each isPossible()

O(n)

Overall

O(n × log(max(nums)))

==================================================================
SPACE COMPLEXITY
==================================================================

O(1)

==================================================================
PATTERN
==================================================================

Search Space

1 ... max(nums)

↓

Guess a divisor

↓

Divide every number

↓

Round UP

↓

Compute total

↓

total <= threshold ?

↓

YES

Search LEFT

↓

NO

Search RIGHT

Notice how this is extremely similar to Koko Eating Bananas.

Koko

Question:

"How many hours does this speed require?"

↓

Compute total hours.

--------------------------------------------------

Smallest Divisor

Question:

"What total does this divisor produce?"

↓

Compute total after division.

The Binary Search loop is identical.

Only the implementation of isPossible() changes.
*/
