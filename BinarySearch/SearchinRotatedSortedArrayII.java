
public class SearchinRotatedSortedArrayII {

    /*
     * ============================================================
     * LeetCode 81 - Search in Rotated Sorted Array II
     * ============================================================
     *
     * Binary Search on Rotated Array
     *
     * ------------------------------------------------------------
     * Difference from LeetCode 33
     * ------------------------------------------------------------
     *
     * LC 33
     *
     * No duplicate values.
     *
     * Therefore,
     * one half is ALWAYS strictly sorted.
     *
     * ------------------------------------------------------------
     *
     * LC 81
     *
     * Duplicate values are allowed.
     *
     * Because of duplicates,
     * sometimes we CANNOT determine
     * which half is sorted.
     *
     * Example
     *
     * nums
     *
     * [1,0,1,1,1]
     *
     * low = 0
     * mid = 2
     * high = 4
     *
     * nums[low]  = 1
     * nums[mid]  = 1
     * nums[high] = 1
     *
     * Both halves appear identical.
     *
     * We cannot determine
     * whether the pivot lies
     * on the left
     * or
     * on the right.
     *
     * Therefore,
     * we remove one duplicate
     * from each side.
     *
     * This is the ONLY new idea
     * compared to LC 33.
     *
     * ============================================================
     */

    public boolean search(int[] nums, int target) {

        /*
         * Search over the entire array.
         */

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            /*
             * Middle element.
             */

            int mid = low + (high - low) / 2;

            /*
             * Target found.
             */

            if (nums[mid] == target) {
                return true;
            }

            /*
             * =====================================================
             * NEW CONDITION (Only in LC 81)
             * =====================================================
             *
             * Suppose
             *
             * nums
             *
             * [1,1,1,1,1]
             *
             * low
             * mid
             * high
             *
             * nums[low]
             *
             * ==
             *
             * nums[mid]
             *
             * ==
             *
             * nums[high]
             *
             * We have no idea
             * which side is sorted.
             *
             * So,
             * remove duplicates
             * from both ends.
             *
             * This shrinks the search space.
             */

            if (nums[low] == nums[mid] &&
                nums[mid] == nums[high]) {

                low++;
                high--;

                continue;
            }

            /*
             * =====================================================
             * Left Half Sorted
             *
             * Array looks like
             *
             * low ------------ mid
             *
             * 4 5 6 7
             *
             * Sorted.
             * =====================================================
             */

            if (nums[low] <= nums[mid]) {

                /*
                 * Is target inside
                 * the sorted left half?
                 */

                if (target >= nums[low] &&
                    target < nums[mid]) {

                    /*
                     * Search Left.
                     */

                    high = mid - 1;

                } else {

                    /*
                     * Search Right.
                     */

                    low = mid + 1;
                }
            }

            /*
             * =====================================================
             * Right Half Sorted
             *
             * mid ------------ high
             *
             * 1 2 3
             *
             * =====================================================
             */

            else {

                /*
                 * Is target inside
                 * the sorted right half?
                 */

                if (target > nums[mid] &&
                    target <= nums[high]) {

                    /*
                     * Search Right.
                     */

                    low = mid + 1;

                } else {

                    /*
                     * Search Left.
                     */

                    high = mid - 1;
                }
            }
        }

        /*
         * Entire search space exhausted.
         */

        return false;
    }
}

/*
==================================================================
COMPLETE DRY RUN
==================================================================

Example

nums

[2,5,6,0,0,1,2]

target = 0

--------------------------------------------------

low = 0

high = 6

mid = 3

nums[mid]

=

0

Target Found

Return TRUE

==================================================================
Another Example
==================================================================

nums

[2,5,6,0,0,1,2]

target = 3

--------------------------------------------------

Iteration 1

low = 0

high = 6

mid = 3

nums[mid]=0

Target !=0

nums[low]=2

nums[mid]=0

Left NOT sorted.

Right Half

0 1 2

Sorted.

Is

3

inside

0...2 ?

No.

Search Left.

high = 2

--------------------------------------------------

Iteration 2

low=0

high=2

mid=1

nums[mid]=5

Left Half

2 5

Sorted.

Is

3

inside

2...5 ?

Yes

Search Left.

high=0

--------------------------------------------------

Iteration 3

low=0

high=0

mid=0

nums[mid]=2

Target not found.

Duplicate condition?

No.

Left sorted.

Target not inside.

low=1

Loop ends.

Return FALSE.

==================================================================
IMPORTANT DUPLICATE CASE
==================================================================

nums

[1,0,1,1,1]

target = 0

--------------------------------------------------

Iteration 1

low=0

mid=2

high=4

nums

1 0 1 1 1

^

    ^

        ^

nums[low]

=

1

nums[mid]

=

1

nums[high]

=

1

All equal.

Can we tell
which half is sorted?

NO.

So

low++

high--

Now

low=1

high=3

Array considered

0 1 1

Now

low=1

mid=2

high=3

nums[low]=0

nums[mid]=1

Left Half

0 1

Sorted.

Target

0

belongs there.

Search Left.

Eventually

target found.

==================================================================
Why Do We Shrink Both Ends?
==================================================================

Suppose

[1,1,1,1,1]

Every comparison

looks identical.

Binary Search
cannot determine
which half is sorted.

Removing

low++

high--

eliminates duplicate values
without losing the target,
because

nums[low]

=

nums[mid]

=

nums[high]

represent the same value.

==================================================================
TIME COMPLEXITY
==================================================================

Average Case

O(log n)

Worst Case

O(n)

Why?

Example

[1,1,1,1,1,1,1]

Every iteration

low++

high--

Only two elements removed
each iteration.

This degrades Binary Search
to linear time.

==================================================================
SPACE COMPLEXITY
==================================================================

O(1)

==================================================================
LC 33 vs LC 81
==================================================================

LC 33

No duplicates

↓

Always know
which half is sorted

↓

Pure Binary Search

--------------------------------------------------

LC 81

Duplicates

↓

Sometimes impossible
to determine
the sorted half

↓

First remove duplicates

↓

Then apply
exactly the same logic
as LC 33

==================================================================
KEY TAKEAWAY
==================================================================

The Binary Search loop does NOT change.

The logic for checking the sorted half does NOT change.

The ONLY additional step is:

if (nums[low] == nums[mid] &&
    nums[mid] == nums[high]) {

    low++;
    high--;
    continue;
}

Everything else is identical to Search in Rotated Sorted Array (LC 33).
*/
