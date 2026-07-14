public class MaximumConsecutiveOnesIII {

    public static void main(String[] args) {

        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};

        int k = 2;

        // int maxLength = 0;

        // // ---------------------------------------------------------
        // // Try every possible starting index.
        // // ---------------------------------------------------------

        // for (int left = 0; left < nums.length; left++) {

        //     int zeroCount = 0;

        //     // -----------------------------------------------------
        //     // Extend window one element at a time.
        //     // -----------------------------------------------------

        //     for (int right = left; right < nums.length; right++) {

        //         // ---------------------------------------------
        //         // Current Window
        //         //
        //         // Example
        //         //
        //         // [1,1,1,0,0,0,1,1,1,1,0]
        //         //  L
        //         //      R
        //         //
        //         // Window
        //         //
        //         // [1,1,1]
        //         // ---------------------------------------------

        //         if (nums[right] == 0) {

        //             zeroCount++;

        //             // Example
        //             //
        //             // Window
        //             //
        //             // [1,1,1,0]
        //             //
        //             // zeroCount = 1
        //         }

        //         // Too many zeros.
        //         // Cannot flip more than k zeros.

        //         if (zeroCount > k) {

        //             // Example
        //             //
        //             // Window
        //             //
        //             // [1,1,1,0,0,0]
        //             //
        //             // zeroCount = 3
        //             //
        //             // k = 2
        //             //
        //             // Stop checking this starting point.

        //             break;
        //         }

        //         // Valid window.

        //         int currentLength = right - left + 1;

        //         maxLength = Math.max(maxLength, currentLength);

        //         // Example
        //         //
        //         // left = 0
        //         // right = 4
        //         //
        //         // length = 5
        //     }
        // }

        // System.out.println(maxLength);


        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        // ---------------------------------------------------------
        // RIGHT keeps expanding the window.
        // ---------------------------------------------------------

        for(int right = 0; right < nums.length; right++)
        {

            // -----------------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // [1,1,1,0,0,0,1,1,1,1,0]
            //  L
            //  R
            //
            // Current Window
            //
            // [1]
            // -----------------------------------------------------

            if(nums[right] == 0)
            {
                zeroCount++;

                // Example
                //
                // Window
                //
                // [1,1,1,0]
                //
                // zeroCount = 1
            }

            // -----------------------------------------------------
            // Window became invalid.
            //
            // Too many zeros.
            //
            // Shrink from LEFT.
            // -----------------------------------------------------

            while(zeroCount > k)
            {

                // Example
                //
                // Window
                //
                // [1,1,1,0,0,0]
                //
                // zeroCount = 3
                //
                // k = 2
                //
                // Remove left side until
                // zeroCount becomes <=2

                if(nums[left] == 0)
                {
                    zeroCount--;

                    // Removed one zero.
                }

                left++;

                // LEFT MOVES
                //
                // [1,1,1,0,0,0]
                //  L
                //
                // becomes
                //
                // [1,1,0,0,0]
                //    L
            }

            // -----------------------------------------------------
            // Window is valid.
            //
            // zeroCount <= k
            //
            // Update answer.
            // -----------------------------------------------------

            int currentLength = right-left+1;

            maxLength = Math.max(maxLength,currentLength);

            // Example
            //
            // left = 4
            //
            // right = 9
            //
            // length = 6
        }

        System.out.println(maxLength);

    }

}
