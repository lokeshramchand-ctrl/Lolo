package SlidingWindow;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubarray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 2, 1, 4, 2, 4};
        //Sliding Window Approach



        // Set<Integer> window = new HashSet<>();

        // int left = 0;
        // int maxLength = 0;
        // for(int i = 0 ; i < arr.length ; i++) {
        //     while(window.contains(arr[i]))
        //     {
        //         window.remove(arr[left]);
        //         left++;
        //     }
        //     window.add(arr[i]);
        //     int currlength = i - left + 1;
        //     maxLength = Math.max(maxLength , currlength);

        // }

        
        HashMap<Integer,Integer> lol = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for(int i= 0; i < arr.length ; i++)
        {
            if(lol.containsKey(arr[i])&& lol.get(arr[i]) >= left)
            {
                left = lol.get(arr[i]) + 1;
            }


            lol.put(arr[i] , i);
            int curr = i - left + 1;
            maxLength = Math.max(maxLength , curr);
        }
        System.out.println("Longest Length = " + maxLength);
    }
}