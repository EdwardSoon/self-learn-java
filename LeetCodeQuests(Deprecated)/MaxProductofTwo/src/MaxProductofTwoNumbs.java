/*
Question:
Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 */

import java.util.HashMap;

public class MaxProductofTwoNumbs {
    public static void main(String[] args) throws Exception {
        int[] nums = {3,9,9,5,2};
        
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : nums){
            if(num > max1){
                max2 = max1;
                max1 = num;
            }
            else if (num > max2){
                max2 = num;
            }
        }
        int result = (max1-1) * (max2-1);
        System.out.println(result);
        System.out.println("max1: " + max1 + "; max2: " + max2);



    }
}
