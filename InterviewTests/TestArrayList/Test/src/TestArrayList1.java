/* 
QUESTION:
given a set of array, you need to find out how many UNIQUE pair of numbers can be added to become 10
cannot add the number itself 
*/

/* Implementation steps
 * 1. sum the two numbers across the arrays, once found sum then add to the pairs
 * 2. once found sum add the second number to a new array, so that when found the first number == the numbers stored in the new array, it will not proceed
 */


/* 
 * for i in numbers 
 *   binarySearch (complement, numbers, index: i+1 , numbers.length-1) 0+5-1 = 4 
 * 
 * 
 * {1,1,2,3,4,5}  == {}
 * 
 * 
 * for i=0; i<numb.length; i++
 *      if(numb[i-1] == numb[i] || )
 *          skip
    * for j=j.numb.length-1 ; j>=0; j--
*      if(numb[j+1] == numb[j])
*          skip
        *  if (numb[i] + numb[j] < 10 )
        *          skip
        *  else if num[i] + numb[j] ==10 

            else if num[i] + numb[j] >10 
                continue

 *      
 *  
 */




 /*

 int[] arr_1;
 int[] arr_2;
 int arr_1_len;
 int arr_2_len;

 put(key, value)
    arr_1[arr_1_len] = key
    arr_2[arr_2_len] = value
    index = arr_1.charAt()

 get (key)
    find arr1 key's index
        arry_2[arr1_index];



  */

import java.util.ArrayList;
import java.util.Arrays;

public class TestArrayList1{
    
    static int[] numbers = {1, 4, 6, 2, 9, 0, 5, 3, 4, 5, 7, 10}; 
    static int target = 10;
    static int pairs = 0;
    static ArrayList<Integer> al = new ArrayList<>();
    public static void main(String[] args){
        Arrays.sort(numbers);


        for (int x=0; x<numbers.length-1; x++){
            if(!al.contains(numbers[x])){
                for (int y=x+1; y<numbers.length; y++){
                    if(numbers[x] + numbers[y] == target){
                        System.out.println(numbers[x] + " and " + numbers[y]);
                        // System.out.println("x: " + x + " and " + "y: " + y);
                        pairs++;
                        al.add(numbers[y]);
                        break; // once got ten then break because for sure it will be the duplicated pairs if continue
                    }
                }                
            }
        }
        System.out.println(pairs);
        System.out.println(numbers);
        System.out.println(Arrays.toString(numbers));
        System.out.println(al);
        System.out.println(Arrays.asList(numbers)); // testing if convert array into list what will happen
    }

}