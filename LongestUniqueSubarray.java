import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubarray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 2, 1, 4, 2, 4};

        Set<Integer> window = new HashSet<>();

        int left = 0;
        int maxLength = 0;
        for(int i = 0 ; i < arr.length ; i++) {
            while(window.contains(arr[i]))
            {
                window.remove(arr[left]);
                left++;
            }
            window.add(arr[i]);
            int currlength = i - left + 1;
            maxLength = Math.max(maxLength , currlength);

        }
        System.out.println("Longest Length = " + maxLength);
    }
}