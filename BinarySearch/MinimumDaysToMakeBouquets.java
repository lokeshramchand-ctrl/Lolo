
public class MinimumDaysToMakeBouquets {

    /*
     * ============================================================
     * LeetCode 1482 - Minimum Number of Days to Make m Bouquets
     * ============================================================
     *
     * Binary Search on Answer
     *
     * ------------------------------------------------------------
     * Problem
     * ------------------------------------------------------------
     *
     * bloomDay[i]
     *
     * tells us the day on which the ith flower blooms.
     *
     * Example
     *
     * bloomDay = [1,10,3,10,2]
     *
     * Flower 0 blooms on day 1.
     * Flower 1 blooms on day 10.
     * Flower 2 blooms on day 3.
     * ...
     *
     * We need
     *
     * m bouquets.
     *
     * Every bouquet requires
     *
     * k ADJACENT flowers.
     *
     * ------------------------------------------------------------
     *
     * We need the MINIMUM DAY
     * on which we can make
     * at least m bouquets.
     *
     * ============================================================
     */

    public int minDays(int[] bloomDay, int m, int k) {

        /*
         * If total flowers required
         * exceed available flowers,
         * answer is impossible.
         */

        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        /*
         * Search Space
         *
         * Minimum day
         * = earliest bloom day
         *
         * Maximum day
         * = latest bloom day
         */

        int low = min(bloomDay);
        int high = max(bloomDay);

        /*
         * Binary Search on Answer
         */

        while (low <= high) {

            /*
             * Candidate day
             */

            int mid = low + (high - low) / 2;

            /*
             * Can we make
             * m bouquets
             * by day = mid?
             */

            if (isPossible(bloomDay, m, k, mid)) {

                /*
                 * Day works.
                 *
                 * Try an earlier day.
                 */

                high = mid - 1;

            } else {

                /*
                 * Cannot make enough bouquets.
                 *
                 * Need more blooming time.
                 */

                low = mid + 1;
            }
        }

        /*
         * Smallest valid day.
         */

        return low;
    }

    /*
     * ------------------------------------------------------------
     * Finds minimum bloom day.
     * ------------------------------------------------------------
     */

    private int min(int[] bloomDay) {

        int min = bloomDay[0];

        for (int i = 1; i < bloomDay.length; i++) {

            if (bloomDay[i] < min) {
                min = bloomDay[i];
            }
        }

        return min;
    }

    /*
     * ------------------------------------------------------------
     * Finds maximum bloom day.
     * ------------------------------------------------------------
     */

    private int max(int[] bloomDay) {

        int max = bloomDay[0];

        for (int i = 1; i < bloomDay.length; i++) {

            if (bloomDay[i] > max) {
                max = bloomDay[i];
            }
        }

        return max;
    }

    /*
     * ============================================================
     * isPossible()
     * ============================================================
     *
     * Suppose today is "day".
     *
     * Which flowers have bloomed?
     *
     * Every flower whose
     *
     * bloomDay[i] <= day
     *
     * is available.
     *
     * We walk through the array.
     *
     * Count consecutive bloomed flowers.
     *
     * Every time we get k adjacent flowers,
     * we make one bouquet.
     *
     * ============================================================
     */

    private boolean isPossible(int[] bloomDay,
                               int m,
                               int k,
                               int day) {

        /*
         * Consecutive bloomed flowers.
         */

        int flowers = 0;

        /*
         * Bouquets created.
         */

        int bouquets = 0;

        for (int bloom : bloomDay) {

            /*
             * Has this flower bloomed?
             */

            if (bloom <= day) {

                /*
                 * Extend consecutive sequence.
                 */

                flowers++;

                /*
                 * Enough adjacent flowers?
                 */

                if (flowers == k) {

                    bouquets++;

                    /*
                     * These flowers are used.
                     *
                     * Start counting again.
                     */

                    flowers = 0;
                }

            } else {

                /*
                 * Flower not bloomed.
                 *
                 * Adjacency breaks.
                 */

                flowers = 0;
            }
        }

        return bouquets >= m;
    }
}

/*
==================================================================
COMPLETE DRY RUN
==================================================================

Example

bloomDay

[1,10,3,10,2]

m = 3

k = 1

--------------------------------------------------

Search Space

low = 1

high = 10

--------------------------------------------------

Iteration 1

mid

=

(1+10)/2

=

5

Question

Can we make

3 bouquets

by Day 5?

--------------------------------------------------

Flower 1

Bloom Day = 1

1 <= 5

Bloomed

flowers =1

flowers==k

Bouquet++

bouquets=1

flowers=0

-------------------------

Flower 10

10<=5 ?

No

flowers=0

-------------------------

Flower 3

3<=5

flowers=1

Bouquet++

bouquets=2

flowers=0

-------------------------

Flower 10

Not bloomed

flowers=0

-------------------------

Flower 2

2<=5

flowers=1

Bouquet++

bouquets=3

flowers=0

--------------------------------------------------

Bouquets=3

Need=3

Return TRUE

Binary Search

Try earlier day.

high=4

==================================================

Iteration 2

low=1

high=4

mid=2

--------------------------------------------------

Available flowers

1

2

Only

2 bouquets

Need

3

FALSE

Need later day.

low=3

==================================================

Iteration 3

low=3

high=4

mid=3

--------------------------------------------------

Bloomed

1

3

2

Three bouquets

TRUE

high=2

==================================================

Loop Ends

low=3

high=2

Return

3

==================================================================
ANOTHER EXAMPLE (Adjacency Matters)
==================================================================

bloomDay

[1,2,4,9,3]

k=2

day=4

--------------------------------------------------

Bloomed?

YES YES YES NO YES

Consecutive Count

Flower 1

count=1

----------------

Flower 2

count=2

Bouquet=1

count=0

----------------

Flower 4

count=1

----------------

Flower 9

NOT BLOOMED

Adjacency breaks.

count=0

----------------

Flower 3

count=1

End

Only

1 bouquet

==================================================================
TIME COMPLEXITY
==================================================================

Finding Minimum

O(n)

Finding Maximum

O(n)

Binary Search

O(log(maxDay-minDay))

Each isPossible()

O(n)

Overall

O(n × log(maxBloomDay))

==================================================================
SPACE COMPLEXITY
==================================================================

O(1)

==================================================================
PATTERN
==================================================================

Search Space

[minBloomDay ... maxBloomDay]

↓

Guess a Day

↓

Simulate blooming

↓

Count adjacent flowers

↓

Make bouquets greedily

↓

Enough bouquets?

↓

YES

Search LEFT

↓

NO

Search RIGHT

==================================================================
COMPARISON WITH PREVIOUS PROBLEMS
==================================================================

Koko Eating Bananas

mid = Eating Speed

↓

Compute total hours

--------------------------------------------------

Ship Packages

mid = Ship Capacity

↓

Simulate loading packages

--------------------------------------------------

Smallest Divisor

mid = Divisor

↓

Compute total after division

--------------------------------------------------

Minimum Days to Make Bouquets

mid = Day

↓

Simulate blooming

↓

Count consecutive flowers

↓

Make bouquets

Notice the Binary Search loop is exactly the same.

Only the implementation of isPossible() changes.
*/
