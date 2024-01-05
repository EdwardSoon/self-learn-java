import java.util.Arrays;

public class InsertionSort {

    // my self-implemented code trying to implement insertion sort
    public void insertionSortMyCode(int[] arr){
        int i,j;
        int n = arr.length;
        int temp;
        for(i=0; i<n-1; i++){
            // insert an element to the sorted part from unsorted part
            if(arr[i]>arr[i+1]){
                System.out.print("i:" + arr[i]);
                // swap i and i+1
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                
                // check the inserted element if it is sorted in the sorted part
                for(j=i; j>0; j--){ 
                    if(arr[j-1]>arr[j]){
                        System.out.print(" j:" + arr[j]);
                        temp = arr[j-1];
                        arr[j-1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            System.out.println();
        }
    }

    // Real insertionSort logic implementation 
    // https://www.geeksforgeeks.org/insertion-sort/
    /*
     * 1. set a key that is gonna be the element for that loop to swap and compare with others
     * 2. compare key to prev elements one by one
     * 3. traverse through the prev elements if they are smaller than the key, if yes then move them one index ahead
     * 4. do this until index <0 or key > the prev element to compare with, whichever comes first
     * 5. then stop and insert key to be the current index
     */
    public static void insertionSort(int[] arr){
        int curr, i, j;
        int n = arr.length;

        for (i=1; i<n; i++){
            curr = arr[i];
            j = i-1;

            while(j >= 0 && curr < arr[j]){
                arr[j+1] = arr[j]; // cannot use arr[i] here as we need it to traverse back in the same i, instead of staying the same in the same i
                j--;
            }
            arr[j+1] = curr; //index where it should insert the current element
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {10,9,100,3,9,1};
        // InsertionSort ob = new InsertionSort();
        // ob.insertionSortMyCode(arr); // for non-staic method need instance to call
        // System.out.println(Arrays.toString(arr));

        insertionSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
