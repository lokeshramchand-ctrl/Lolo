public class MinimumSizeSubarraySum {

    public static void main(String[] args) {

        int target = 7;

        int[] nums = {2,3,1,2,4,3};

        // ============================================================
        // Problem:
        //
        // Find the smallest subarray
        // whose sum is >= target.
        //
        // Example
        //
        // target = 7
        //
        // [2,3,1,2,4,3]
        //
        // Answer
        //
        // [4,3]
        //
        // Length = 2
        // ============================================================



        // ============================================================
        // APPROACH 1 : BRUTE FORCE
        //
        // TC : O(n²)
        // SC : O(1)
        // ============================================================

        /*
        int answer = Integer.MAX_VALUE;

        for(int left=0; left<nums.length; left++)
        {
            int sum = 0;

            for(int right=left; right<nums.length; right++)
            {
                //------------------------------------------
                // RIGHT enters current window.
                //------------------------------------------

                sum += nums[right];

                //------------------------------------------
                // Window satisfies target.
                //------------------------------------------

                if(sum>=target)
                {
                    int length = right-left+1;

                    answer=Math.min(answer,length);

                    break;
                }
            }
        }

        System.out.println(
                answer==Integer.MAX_VALUE?0:answer);
        */



        // ============================================================
        // APPROACH 2 : OPTIMAL (SLIDING WINDOW)
        //
        // TC : O(n)
        // SC : O(1)
        // ============================================================

        int left = 0;

        int sum = 0;

        int answer = Integer.MAX_VALUE;

        // RIGHT expands window.
        for(int right=0; right<nums.length; right++)
        {

            //----------------------------------------------
            // RIGHT MOVES
            //
            // Example
            //
            // [2,3,1,2]
            //----------------------------------------------

            sum += nums[right];

            //----------------------------------------------
            // Window already satisfies target.
            //
            // Try making it smaller.
            //----------------------------------------------

            while(sum>=target)
            {

                //------------------------------------------
                // Current window is valid.
                //------------------------------------------

                int length = right-left+1;

                answer=Math.min(answer,length);

                //------------------------------------------
                // Remove LEFT element.
                //
                // Example
                //
                // [2,3,1,2]
                //  L
                //
                // becomes
                //
                // [3,1,2]
                //------------------------------------------

                sum-=nums[left];

                left++;
            }

        }

        System.out.println(
                answer==Integer.MAX_VALUE?0:answer);
    }
}