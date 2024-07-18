import java.util.Arrays;

/**
 * <p>Implementation operations:
 * Mainly implemented from an array (Note: left children node of the tree !=  left element index
 * 1. heapify: take a subtree, with left child and right child elements (with pointers) to put the largest or the smallest value at the middle (or top)
 * NOTE: THE heapify i implemented here is to heapify the entire tree, instead of just a sub-tree, thus O(N)
 * 2. insert: for each element inserted, heapify to maintain heap property -- can be optimised (refer to HeapWithReference)
 * 3. getMax/getMin: get the maximum or minimum number
 * 4. removeMAx/removeMin: returns the deleted element and maintain the heap property
 * </p>
 */

public class Heap {

    public int maxSize;
    public int heapSize;
    public int[] arr;
    public int root;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.heapSize = 0;
        this.root = 0;
    }

    public Heap(int maxSize, int root) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.heapSize = 0;
        this.root = root;
    }

    public static void main(String[] args) {
        Heap obj = new Heap(6);
        obj.insert(3);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
        obj.insert(4);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
        obj.insert(11);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
        obj.insert(14);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
//        obj.insert(13);
        obj.insert(15);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
        obj.insert(18);
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);
//        obj.insert(12);
        System.out.println(obj.removeMax());
        System.out.println(Arrays.toString(obj.arr) + "; heapSize: " + obj.heapSize);


    }

    /*
    1. left child, right child pointers and a largest pointer
    2. iterating from the most bottom (left or right first doesn't matter)
    3. shift the largest to the current index
    O(T) = O(N) due to visited all elements in array -- can be improved
     */
    void maxHeapifyEntireTree(int i) {

        if (i >= heapSize || i < 0) { // base case
            return;
        }
        int left = left(i); // left pointer
        int right = right(i); // right pointer

        maxHeapifyEntireTree(left); // iterate through the left all the way to the most bottom (for all left, right will need to be iterated too)
        maxHeapifyEntireTree(right); // iterate through the right all the way to the most bottom

        // start heapify from the most bottom (most bottom: it is the last in, first out LIFO [stack memory])
        int largest = i; // largest pointer
        if (left < heapSize && arr[left] > arr[largest]) {  // pointer ensure less than heapSize in order to access the element in array
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
        }

    }

    /*
    1. put at the last element of arr
    2. increase the size of heap
    3. heapify repeatedly so that the element will maintain heap property
    O(T) = O(1) insert + O(N) heapify = O(N)
     */
    void insert(int data) {
        if (heapSize >= maxSize) {
            throw new IndexOutOfBoundsException("heap has reached its maxSize");
        }
        arr[heapSize] = data;
        heapSize++;
        maxHeapifyEntireTree(this.root); // heapify from the root node (meaning this is where the max number will be placed)
    }

    // O(T) = O(1)
    int getMax() {
        return arr[this.root];
    }

    // O(N) copyOfRange + O(N) maxHeapify = O(2N) = O(N)
    int removeMax() {
        int max = getMax();
        arr = Arrays.copyOfRange(arr, 1, heapSize);
        heapSize--;
        maxHeapifyEntireTree(this.root);
        return max;
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }
}
