import java.util.Arrays;

public class MergeSort {

    /* 
     * 1. split the input into half
     * 2. sort each half by recursively using this same process
     * 3. merge the sorted halved back together
     */

    /*  
     * IMPLEMENTATION OF CODE
     * 1. implement sort and combine first
     * 2. split it recursively 
     * 2.1 sort and combine it recursively 
     */


    // merge while sort algo
    private static int[] combineSortMyCode(int[] leftArr, int[] rightArr){
        

        int[] merge = new int[leftArr.length + rightArr.length];
        int l = 0, r = 0, m = 0; // indices

        while (leftArr.length > l && rightArr.length > r){
            if (leftArr[l]<=rightArr[r]){
                merge[m] = leftArr[l];
                l++;
            }
            else if(rightArr[r]<leftArr[l]){
                merge[m] = rightArr[r];
                r++;
            }
            m++;
        }
        // for the leftover if rightArr is done
        while (leftArr.length > l) {
            merge[m] = leftArr[l];
            l++;
            m++;
        }
        // for the leftover if leftArr is done
        while (rightArr.length > r) {
            merge[m] = rightArr[r];
            r++;
            m++;
        }

        return merge;
    }

    /*
     * 1. split recursively 
     * 2. sort and combine recursively
     */
    // driver method
    public static int[] mergeSortMyCode(int[] arr){
        // base case
        if(arr.length <= 1){
            return arr ;
        }
        
        // 1. split into half
        int left = 0;
        int right = arr.length;
        int mid = right/2; 
        // 1.1 operation split statement
        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);    // copyOfRange exclude the last index, so need to plus 1
        System.out.println("leftA:" + Arrays.toString(leftArr) + " rightA:" + Arrays.toString(rightArr));
        // 1.2 recurse this function (that contains split statement), end at base case but the method that called will continue running until the end 
        int[] leftSorted = mergeSortMyCode(leftArr); // this is actually to sort and sorted: when it is base case: to sort and combine by the combine Method; when it is not at base case, it is already sorted subarray and non-sorted both array to merge and sort
        int[] rightSorted = mergeSortMyCode(rightArr);
        System.out.println("leftS:" + Arrays.toString(leftSorted) + " rightS:" + Arrays.toString(rightSorted));
        
        // 2. merge the sorted halved back together: start from base case as it is the first one that ends the recursive mergeSort then store into leftSorted and rightSorted for merge
        System.out.println("trigger combine and sort");
        return combineSortMyCode(leftSorted, rightSorted); // implemented as private method up there

    }


    public static void main(String[] args) throws Exception {
        int[] arr = {10,3,5,6,4};
        // mergeSortMyCode(arr);
        // System.out.println(arr);
        System.out.println(Arrays.toString(mergeSortMyCode(arr))); 
        //it must print with the method because the merged is the one we want and it is only in the returned results. 
        // unless we are using the same array like quicksort, then we just have to call the method on the array and print the array later refer to QuickSort code

    }
}
