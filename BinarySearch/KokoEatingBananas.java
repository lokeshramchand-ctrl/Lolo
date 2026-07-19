public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {

        /*
         * Binary Search on Answer
         *
         * We are NOT searching inside the piles array.
         *
         * Instead, we search for the minimum possible eating speed.
         *
         * Search Space:
         * --------------------------
         * Minimum speed = 1 banana/hour
         * Maximum speed = max(piles)
         */

        int low = 1;
        int high = findMax(piles);

        // Stores the minimum valid speed found so far
        int ans = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            /*
             * Assume Koko eats 'mid' bananas every hour.
             *
             * Check whether she can finish all bananas
             * within h hours.
             */
            if (isPossible(piles, h, mid)) {

                /*
                 * This speed works.
                 *
                 * Save it as a possible answer.
                 */
                ans = mid;

                /*
                 * But maybe there is an even smaller
                 * valid speed.
                 *
                 * Search Left.
                 */
                high = mid - 1;

            } else {

                /*
                 * This speed is too slow.
                 *
                 * Need a larger speed.
                 *
                 * Search Right.
                 */
                low = mid + 1;
            }
        }

        return ans;
    }

    /*
     * Returns true if Koko can finish
     * all bananas within h hours
     * while eating at 'speed' bananas/hour.
     */
    private boolean isPossible(int[] piles, int h, int speed) {

        int totalHours = 0;

        for (int pile : piles) {

            /*
             * Hours required for one pile
             *
             * ceil(pile / speed)
             *
             * Integer trick:
             *
             * (pile + speed - 1) / speed
             *
             * Examples:
             *
             * pile = 11
             * speed = 4
             *
             * (11 + 4 - 1) / 4
             * = 14 / 4
             * = 3
             */

            totalHours += (pile + speed - 1) / speed;
        }

        /*
         * If total hours are within limit,
         * this speed is valid.
         */
        return totalHours <= h;
    }

    /*
     * Finds the largest pile.
     *
     * This becomes the maximum possible speed.
     */
    private int findMax(int[] piles) {

        int max = piles[0];

        for (int pile : piles) {

            if (pile > max) {
                max = pile;
            }
        }

        return max;
    }
}

/*
==========================================================
DRY RUN
==========================================================

Input

piles = [3,6,7,11]
h = 8

------------------------------------------
Step 1

low = 1
high = 11

Search Space

1 2 3 4 5 6 7 8 9 10 11

------------------------------------------
Iteration 1

mid = (1 + 11) / 2
    = 6

Assume speed = 6

Hours:

Pile 3
ceil(3/6) = 1

Pile 6
ceil(6/6) = 1

Pile 7
ceil(7/6) = 2

Pile 11
ceil(11/6) = 2

Total Hours

1 + 1 + 2 + 2 = 6

6 <= 8

Possible

ans = 6

Try smaller speed

high = 5

------------------------------------------
Iteration 2

low = 1
high = 5

mid = 3

Speed = 3

Hours

3 -> 1

6 -> 2

7 -> 3

11 -> 4

Total

1 + 2 + 3 + 4 = 10

10 > 8

Not Possible

Need faster speed

low = 4

------------------------------------------
Iteration 3

low = 4
high = 5

mid = 4

Speed = 4

Hours

3 -> 1

6 -> 2

7 -> 2

11 -> 3

Total

1 + 2 + 2 + 3 = 8

Possible

ans = 4

Try smaller speed

high = 3

------------------------------------------
Loop Ends

low = 4
high = 3

Answer

4

==========================================================
Time Complexity

Finding Maximum
O(n)

Each isPossible()
O(n)

Binary Search
O(log(maxPile))

Overall

O(n × log(maxPile))

==========================================================
Space Complexity

O(1)

==========================================================
Pattern

Binary Search on Answer

Search Space:
1 .... maxPile

Question:

"Can Koko finish all bananas
if her eating speed is mid?"

YES -> Try Smaller Speed

NO -> Try Larger Speed

==========================================================
*/