/* 
QUESTION:
given a set of array, you need to find out how many UNIQUE pair of numbers can be added to become 10
cannot add the number itself 
*/

/*
IMPLEMETATION
1. need to have key as each element of array, so the first number wont duplciates 
2. need to have values taht are latest indices of those elements so we can make sure we sum the behind element not the same or before element (which already summed)
3. to make sure there is a doneMap so it wont duplicate for the first or second number
 */

import java.util.HashMap;

public class App {
    static int[] numbers = {1, 9, 9, 1, 4, 3, 6, 2, 9, 0, 5, 3, 4, 5, 7};
    static int target = 10;
    static int numbLen = numbers.length;
    static int pairs = 0;
    // static ArrayList<Integer> doneList1 = new ArrayList<>();
    // static ArrayList<Integer> doneList2 = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        HashMap<Integer,Integer> numsMap = new HashMap<>();
        HashMap<Integer,Integer> doneMap = new HashMap<>();
        for (int i=0; i<numbLen; i++){
            numsMap.put(numbers[i], i);
        }

        for (int i=0; i<numbLen; i++){
            int sec_numb = target - numbers[i];
            if(numsMap.containsKey(sec_numb) && numsMap.get(sec_numb) > i && !doneMap.containsKey(numbers[i]) && !doneMap.containsValue(numbers[i])){
                doneMap.put(numbers[i], sec_numb);
                pairs++;
                System.out.println(numbers[i] + " and " + sec_numb);
            }
        }
        System.out.println(pairs);
    }
}
