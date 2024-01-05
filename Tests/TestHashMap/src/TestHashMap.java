/*  
 QUESTION: (USE HashMap)
given a set of array, you need to find out how many UNIQUE pair of numbers can be added to become 10
cannot add the number itself 
*/

/* 
Conditions to get the UNIQUE sum of a pair of numbers to get to the target
 1. to build a hashmap 
  a) with key == all numbers from array -- so the 1st number wont duplicate (as key will not duplicate) (with duplicated key, the values will always take the last one)
  b) value == the respective indices -- so that we can avoid summing the number itself or the number that is less than its indices (which have already summed before)
 2. we need to ensure if the 2nd number to sum is in the array thus, target - 1st number == 2nd number
 3. we also need to make sure 2nd number is not smaller indices than the 1st number, if not it will summing reperatedly 
 4. if it had been summed we need to make sure it will not be summed again -- meaning the used key != complement because it will be duplicated pairs 
 */

import java.util.HashMap;

public class TestHashMap {
    static int[] numbers = {5, 1, 4,3, 6, 2, 9, 0, 5, 3, 4, 5, 7, 10};
    static int target = 10;
    static int pairs;
    public static void main(String[] args) throws Exception {
        HashMap<Integer,Integer> numbMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> doneMap = new HashMap<Integer,Integer>();
        
        int n = numbers.length;
        int complement;
        // build hashmap with key == first number; value == index of the array
        for (int i=0; i<n; i++){
            numbMap.put(numbers[i],i);
            System.out.println(numbMap);
        }

        for (int i=0; i<n-1; i++){
            complement = target - numbers[i];
            if (numbMap.containsKey(complement) && numbMap.get(complement) > i && !doneMap.containsKey(complement) && !doneMap.containsValue(complement)){ // == i means it is the number itself, which we cannot add the number itself
                pairs ++;
                System.out.println(numbers[i] + " and " + complement);
                doneMap.put(numbers[i],complement);

                
            }
        }
        System.out.println("Number of unique pairs to reach target: " + pairs);
    }
}
