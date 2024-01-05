/* 
Implementation of Merge Sort:
1. define Merge function: split the subarray to two subarrays (left and right) and sort the subarrays and merge the sorted subarrays
    a) define size of both left and right subarrays for loop later
    b) define subarrays for left and right with size
    c) copy the elements from orginial array to left and right subarrays
    d) merge and sort the two subarrays while they are under their size -- once one of the subarrays is done, the loop will end
    e) edge case: e.g: one subarray has more elemnent than the other: merge the remaining of elements (must be bigger than others) of one of the subarrays (if there is)
2. implement MergeSort helper function: mainly to help divides the arrays into subarrays
    a) define middle index
    b) ensure if left<right index then only starts the split 
    c) an orginal array split into two subarray
    d) call mege to have them to merge the two subarray back later at the end
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Sort {

    public void merge(int arr[], int left, int middle, int right){
        // a) declaring size of the two subarrays , imagine it by using samples {0,1,2,3,4,5,6}
        int size_L = middle - left + 1; //including middle
        int size_R = right - middle;

        // b) define subarrays for left and right with size
        int[] L = new int[size_L];
        int[] R = new int[size_R];

        // c) copy the elements from orginial array to left and right subarrays
        for (int i=0; i<size_L; i++){
            L[i] = arr[i+left];
        }
        for (int j=0; j<size_R; j++){
            R[j] = arr[j+middle+1]; 
        }
        // d) merge and sort the two subarrays while they are under their size -- once one of the subarrays is done, the loop will end
        int arr_i = left; // get the starting index
        int i = 0, j = 0;
        while (i < size_L && j < size_R){
            if(L[i] <= R[j]){ // the sorting is done here
                arr[arr_i] = L[i]; //merging
                i++;
            }
            else{ // the sorting is done here
                arr[arr_i] = R[j]; //merging
                j++;
            }
            arr_i++;
        }

        // e) merge the remaining of elements of one of the subarrays (if there is)
        while (i < size_L) {
                arr[arr_i] = L[i]; 
                i++;
                arr_i++;
            }
        while (j < size_R) {
                arr[arr_i] = R[j];
                j++;
                arr_i++;
            }
    }

    public void mergeSort(int[] arr, int left, int right){
        int middle;
        if (left < right) {
            middle = (left + right) / 2;

            mergeSort(arr, left, middle); // split into two: first subarray
            mergeSort(arr, middle+1, right); // second subarray -- and both of them will go to merge separately

            merge(arr, left, middle, right); // merge and sort back together later
        }
    }

    public static void main(String[] args) throws Exception {

        // execute merge sort
        // int arr[] = { 9, 3, 1, 5, 13, 12 };
        // Sort ob = new Sort();
        // ob.mergeSort(arr, 0, arr.length-1);
        // System.out.println(Arrays.toString(arr));

        //  Arrays.sort
        int[] x = new int[] {9,1,5,6,8,3};
        Arrays.sort(x); // this method returns void
        System.out.println(Arrays.toString(x));

        // Arrays.sort(arr[], Collections.reverseOrder())
        Integer[] array = {23, -9, 78, 102, 4, 0, -1, 11, 6, 110, 205}; 
        
        Arrays.sort(array);
        System.out.println(Arrays.toString(array)); 
        // Arrays.binarySearch
        int key = -9 ; 
        System.out.println(Arrays.binarySearch(array,key)); // return the index of the found value
        // sorts array[] in descending order   
        Arrays.sort(array, Collections.reverseOrder());   
        System.out.println(Arrays.toString(array));  


        // ArrayList sort
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1); 
        al.add(9); 
        al.add(0); 
        al.add(7); 
        al.add(5); 
        al.sort(Collections.reverseOrder());
        System.out.println("AL:" + al);
        // Collections.sort(al, Collections.reverseOrder());
        // System.out.println(al);
        // Collections.sort(al); // sorting call
        // System.out.println(al);

        // HashMap sort : 1. convert to ArrayList 2. sort
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(10,100);
        map.put(3,4 );
        ArrayList<Integer> mapListK = new ArrayList<>(map.keySet());
        ArrayList<Integer> mapListV = new ArrayList<>(map.values());
        System.out.println(mapListK);
        System.out.println(mapListV);
        mapListK.sort(Collections.reverseOrder());
        mapListV.sort(Collections.reverseOrder());
        System.out.println(mapListK);
        System.out.println(mapListV);
        // it seems like it was already sorted




        // convert Array to ArrayList
        Integer[] arra = {23, -9, 78, 102, 4, 0, -1, 11, 6, 110, 205}; 
        ArrayList<Integer> arra_al = new ArrayList<>(Arrays.asList(arra));
        System.out.println(arra_al);
        // convert ArrayList to array
        Object[] al_arra = arra_al.toArray();
        System.out.println(al_arra);
        
    }
}
