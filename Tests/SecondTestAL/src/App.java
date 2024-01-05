/* 
QUESTION:
given a set of array, you need to find out how many UNIQUE pair of numbers can be added to become 10
cannot add the number itself 
*/

/* Implementation
 1. sum the number forward not itself or before
 2. if it is done summing, then add to doneList to make sure it wont repeat or duplicate
 */

import java.util.ArrayList;

public class App {

    static int[] numbers = {1, 4, 3, 7, 6, 2, 9, 0, 5, 3, 4, 5, 7, 10};
    static int numbLen = numbers.length;
    static int target = 10;
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> doneList = new ArrayList<>();
        
        for (int i=0; i<numbLen-1; i++){
            int sec_num = target - numbers[i];
            if(!doneList.contains(sec_num) && !doneList.contains(numbers[i])){
                doneList.add(sec_num);
                doneList.add(numbers[i])
            }
            }
        }
    }
}
