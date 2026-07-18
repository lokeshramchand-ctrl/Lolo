package BinarySearch;
/*
===========================================================
Approach 1 : Two Separate Helper Functions
===========================================================

Idea:
1. Run Binary Search once to find the FIRST occurrence.
2. Run Binary Search again to find the LAST occurrence.
3. Return both indices.

Time Complexity  : O(log n) + O(log n) = O(log n)
Space Complexity : O(1)

-----------------------------------------------------------
Dry Run

nums = [5,7,7,8,8,10]
target = 8

FIRST OCCURRENCE

low=0 high=5
mid=2 -> nums[mid]=7

7 < 8
Move Right
low=3

--------------------

low=3 high=5
mid=4 -> nums[mid]=8

Found target.
Save answer = 4

Don't stop!!
Maybe another 8 exists on LEFT.

high = mid-1 = 3

--------------------

low=3 high=3
mid=3 -> nums[mid]=8

Found target.
Save answer = 3

Again search LEFT

high=2

Loop Ends

First Occurrence = 3

===========================================================

LAST OCCURRENCE

low=0 high=5
mid=2 -> nums[mid]=7

7 < 8

Move Right

low=3

--------------------

low=3 high=5
mid=4 -> nums[mid]=8

Found target.

Save answer = 4

Search RIGHT

low=5

--------------------

low=5 high=5
mid=5 -> nums[mid]=10

10 > 8

Search LEFT

high=4

Loop Ends

Last Occurrence = 4

Answer = [3,4]

===========================================================
*/

// class Solution {

//     public int[] searchRange(int[] nums, int target) {

//         int first = findFirst(nums, target);
//         int last = findLast(nums, target);

//         return new int[]{first, last};
//     }

//     // Finds first occurrence
//     private int findFirst(int[] nums, int target) {

//         int low = 0;
//         int high = nums.length - 1;

//         int ans = -1;

//         while (low <= high) {

//             int mid = low + (high - low) / 2;

//             if (nums[mid] == target) {

//                 // Save current answer
//                 ans = mid;

//                 // Continue searching LEFT
//                 // because an earlier occurrence may exist.
//                 high = mid - 1;
//             }
//             else if (nums[mid] < target) {

//                 // Target is larger.
//                 // Ignore left half.
//                 low = mid + 1;
//             }
//             else {

//                 // Target is smaller.
//                 // Ignore right half.
//                 high = mid - 1;
//             }
//         }

//         return ans;
//     }

//     // Finds last occurrence
//     private int findLast(int[] nums, int target) {

//         int low = 0;
//         int high = nums.length - 1;

//         int ans = -1;

//         while (low <= high) {

//             int mid = low + (high - low) / 2;

//             if (nums[mid] == target) {

//                 // Save current answer
//                 ans = mid;

//                 // Continue searching RIGHT
//                 // because another occurrence may exist.
//                 low = mid + 1;
//             }
//             else if (nums[mid] < target) {

//                 low = mid + 1;
//             }
//             else {

//                 high = mid - 1;
//             }
//         }

//         return ans;
//     }
// }

/*
===========================================================
Approach 2 : One Helper Function (Recommended by many)

Idea

There is ONLY ONE DIFFERENCE between

1. First Occurrence
2. Last Occurrence

When target is found

First Occurrence
---------------
high = mid - 1

Last Occurrence
--------------
low = mid + 1

Everything else is IDENTICAL.

So instead of writing two functions,
we pass a boolean.

true  -> Find First
false -> Find Last

Time Complexity : O(log n)
Space Complexity: O(1)

===========================================================

Dry Run

nums = [5,7,7,8,8,10]

Call

findBoundary(nums,8,true)

====================

low=0 high=5

mid=2

7<8

Move Right

low=3

====================

mid=4

Found

answer=4

Need FIRST

Search LEFT

high=3

====================

mid=3

Found

answer=3

Search LEFT

high=2

Loop Ends

Returns 3

===========================================================

Now call

findBoundary(nums,8,false)

====================

low=0 high=5

mid=2

Move Right

low=3

====================

mid=4

Found

answer=4

Need LAST

Search RIGHT

low=5

====================

mid=5

10>8

Move Left

high=4

Loop Ends

Returns 4

Answer

[3,4]

===========================================================
*/

public class FindFirstandLastElement {

    public int[] searchRange(int[] nums, int target) {

        int first = findBoundary(nums, target, true);

        int last = findBoundary(nums, target, false);

        return new int[]{first, last};
    }

    /*
        first = true
        ----------------
        Find First Occurrence

        first = false
        ----------------
        Find Last Occurrence
    */

    private int findBoundary(int[] nums, int target, boolean first) {

        int low = 0;
        int high = nums.length - 1;

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {

                // Store current occurrence
                ans = mid;

                if (first) {

                    // We need FIRST occurrence

                    // Continue searching LEFT
                    high = mid - 1;

                } else {

                    // We need LAST occurrence

                    // Continue searching RIGHT
                    low = mid + 1;
                }

            }
            else if (nums[mid] < target) {

                // Search Right
                low = mid + 1;

            }
            else {

                // Search Left
                high = mid - 1;
            }
        }

        return ans;
    }
}