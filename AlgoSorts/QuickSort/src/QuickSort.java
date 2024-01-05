// https://www.interviewcake.com/concept/java/quicksort
import java.util.Arrays;

public class QuickSort {

    /* 
     * 1. PARTITIONING AROUND A PIVOT (NEED TO HAVE STARTINDEX AND ENDINDEX PARAMETERS FOR SUBARRAY PARTITION AND SORT PURPOSES )
     * 1.1 choose the pivot (usually the last item in the list as it is the simplest way of doing quickSort)
     * 1.2 two parts: one for left and one for right
     * 1.3 we will move the left pointer first until found something bigger than the pivot then it belongs to right
     * 1.4 when found, then we need right pointer to find somehting smaller than the pivot so that it belongs to left 
     * 1.5 swap them
     * 1.6 repeat this process until the left and right pointer cross each other
     * 1.7 then swap the left pointer with the pivot (so that the larger number will stay right)
     * 1.8 partition into two lists
     */
    private static int quickSortPartitionMyCode(int[] arr, int startI, int endI){   // need startI and endI, so that the other array length can use for partitions in any length
        int pivot = arr[endI];   // 1.1
        int temp;
        int l = startI, r = endI - 1;    // 1.2 : -1 because the last element is pivot
        // int l = 0, r = n-2;     // 1.2
        while (r >= l) {        // 1.6
            if (arr[l]>pivot){  // 1.3
                if (arr[r]<pivot){  // 1.4
                    temp = arr[l];  //1.5
                    arr[l] = arr[r];
                    arr[r] = temp;      
                    l++; // can skip the current element as it had swapped to correct already
                    r--; // can skip the current element as it had swapped to correct already
                }
                else{
                    r--;     // move the right pointer towards left
                }
            }
            else {
                l++;        // move the left pointer towards right
            }

        }
        arr[endI] = arr[l];  // 1.7
        arr[l] = pivot;    
        return l; // to indicate the pivot index at where so that we know how to partition
        
    }

    /* 
     * 2. RECURSIVELY SORTING THE HALVES
     * 2.1 calling the partition method to partition and sort the subarrays
     * 2.2 get pivot index to know from where to partition (from the partition method)
     * 2.3 recrusion: partition, sort and getting pivot index recursively so that we can keep partitioning based on subarrays' pivot index 
     *     partition excludes the pivot itself for every subarrays.
     * 2.4 thus startIndex and endIndex are necessary parameters for partition method to work
     */
    private static void quickSortSubarrayMyCode(int[] arr, int startI, int endI){

        // base case: check the subarray or array with this first then if pass only partition and sort, else there is no need to partition subarray anymore
        if (startI >= endI){
            return; // can return nothing for void
        }

        // come after base case, because base case might happen first if we put 0 or 1 element array before we know if it should be partitioned
        int pivotIndex = quickSortPartitionMyCode(arr, startI, endI); // this line does partitioning and sorting and eventually get the pivot index

        quickSortSubarrayMyCode(arr, startI, pivotIndex-1);
        quickSortSubarrayMyCode(arr, pivotIndex+1, endI);

    }


    /* 
     * 3. TO CALL QUICKSORT FOR THE PARTICULAR ARRAY -- to shorten the unnecessary parameter
     * 3.1 directly call quickSortSubarray method as it will partition recursively to get the pivot index recursively
     */
    public static void quickSortMyCode(int[] arr){
        quickSortSubarrayMyCode(arr, 0, arr.length-1);
    }

    public static void main(String[] args) throws Exception {
        // int[] arr = {9, 10, 2, 5, 6, 1};
        // int[] arr = {0, 8, 1, 2, 7, 9, 3, 4};
        int[] arr = {3,2};
        quickSortMyCode(arr);
        System.out.println(Arrays.toString(arr));
    }
}
