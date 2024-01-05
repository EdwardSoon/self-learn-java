import java.util.Arrays;

public class BubbleSort {

    // This is better
    /* 
     * 1. compare across the adjacent elements across the array
     * 2. if found one left one is bigger than right one than swap
     * 3. if not continue to do 1 until the end of the array
     * 4. next round of looping you do 1-3 but with 1 lesser element to compare as the last element now is sorted
     */
    public void bubbleSortMyCode(int[] arr){
        int temp, i, j;
        int n = arr.length;
        for(i=n-1; i>=0; i--){ // compare (size-1) rounds so that all larget elements get sink (and smaller elements bubble)
            j=0; // set to 0 when it is a new loop, then restart the comparison between adjacent elements
            while(j<i){ // <i (where i starts from last index) instead of <n-1 so that you avoid comparing the sorted part
                // System.out.println("j:" + j + ";arr" + Arrays.toString(arr));    // for debugging
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                j++;
            }
        } 
    }

    // provided by https://www.geeksforgeeks.org/bubble-sort/ 
    // more confusing in my opinion
    static void bubbleSort(int arr[]){
        int i, j, temp;
        int n = arr.length;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        int[] arr = {1,3,2,4};
        BubbleSort ob = new BubbleSort();
        ob.bubbleSortMyCode(arr);
        // bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
