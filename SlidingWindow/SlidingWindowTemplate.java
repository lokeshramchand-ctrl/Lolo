// ======================================================================
//                ULTIMATE SLIDING WINDOW REVISION SHEET
//
// This is NOT a solution.
// This is the thought process before writing ANY sliding window code.
//
// ======================================================================

/*

STEP 0
------

Ask yourself:

Is the window FIXED or VARIABLE?

Example

Fixed
-----

Maximum Sum Subarray of Size K
Permutation in String
Find All Anagrams

Variable
--------

Longest Substring Without Repeating Characters
Fruits Into Baskets
Character Replacement
Maximum Consecutive Ones III
Minimum Size Subarray Sum



======================================================================

VARIABLE WINDOW TEMPLATE

======================================================================

left = 0;

for(right = 0; right < n; right++)
{

    //-------------------------------------------------------------
    // STEP 1
    //
    // Expand the window.
    //
    // RIGHT enters.
    //
    // Examples
    //
    // sum += nums[right]
    //
    // frequency++
    //
    // zeroCount++
    //
    // HashMap++
    //-------------------------------------------------------------




    //-------------------------------------------------------------
    // STEP 2
    //
    // Is the window INVALID ?
    //
    // If yes,
    // remove from LEFT.
    //
    // Continue until valid.
    //-------------------------------------------------------------

    while(window is invalid)
    {

        //---------------------------------------------------------
        // Remove LEFT.
        //---------------------------------------------------------

        remove(nums[left]);

        left++;
    }




    //-------------------------------------------------------------
    // STEP 3
    //
    // Window is valid.
    //
    // Update answer.
    //-------------------------------------------------------------

}



======================================================================

Question 1

What information should I maintain?

======================================================================

Running Sum ?

↓

sum

-------------------------------------

Distinct Elements ?

↓

HashSet / HashMap

-------------------------------------

Frequency of characters ?

↓

frequency[26]

-------------------------------------

Frequency of numbers ?

↓

HashMap<Integer,Integer>

-------------------------------------

Maximum Frequency ?

↓

maxFrequency

-------------------------------------

Number of zeros ?

↓

zeroCount



======================================================================

Question 2

When is the window INVALID?

======================================================================

Longest Unique Substring

↓

Duplicate exists

-------------------------------------

Fruits Into Baskets

↓

Distinct Fruits > 2

-------------------------------------

Maximum Consecutive Ones III

↓

zeroCount > k

-------------------------------------

Character Replacement

↓

WindowLength-maxFrequency > k

-------------------------------------

Maximum Sum Distinct Subarray

↓

Window Size > k

-------------------------------------

Minimum Size Subarray Sum

↓

SPECIAL CASE

Shrink while VALID.

(sum >= target)



======================================================================

Question 3

What leaves the window?

======================================================================

Whenever LEFT moves

Undo whatever RIGHT did.

RIGHT added frequency

↓

LEFT removes frequency

-------------------------------------

RIGHT added sum

↓

LEFT subtracts sum

-------------------------------------

RIGHT added zero

↓

LEFT removes zero

-------------------------------------

RIGHT added character

↓

LEFT removes character



======================================================================

Question 4

When do I update answer?

======================================================================

Maximum Window

↓

Every valid window

-------------------------------------

Minimum Window

↓

Before shrinking

-------------------------------------

Fixed Window

↓

Only when
window size == k



======================================================================

FIXED WINDOW TEMPLATE

======================================================================

Build first window.

↓

Check answer.

↓

Slide.

↓

Remove left element.

↓

Add right element.

↓

Check answer.

Repeat.



======================================================================

MOST IMPORTANT IDEA

======================================================================

Whenever RIGHT adds something

LEFT must undo exactly that thing.

Think like this:

RIGHT

↓

Added frequency

↓

LEFT

↓

Subtract frequency

------------------------------------

RIGHT

↓

Added sum

↓

LEFT

↓

Subtract sum

------------------------------------

RIGHT

↓

Added zero

↓

LEFT

↓

Remove zero

------------------------------------

RIGHT

↓

Added map entry

↓

LEFT

↓

Remove map entry when frequency becomes 0



======================================================================

FINAL INTERVIEW CHECKLIST

======================================================================

□ Fixed or Variable?

□ What does RIGHT add?

□ What does LEFT remove?

□ What makes the window invalid?

□ When should I update my answer?

*/