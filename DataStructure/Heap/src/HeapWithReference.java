/*
My own heap implementation is not as efficient thus, I am implementing again the real heap data structure to learn better
 */

import java.util.Arrays;

/**
 * <p>
 * Implementation
 * 1. heapify: O(logN): start from top to downwards (mine was from downwards to upwards hence checking every element)
 * 2. insert: O(logN): start from most bottom to upward, so that it can check only one side of tree where the new num is inserted
 * 3. removeMax: O(logN): replace the last element to the first element, then heapify
 * </p>
 * <p>
 * In general, the idea of implementation here is :
 * 1. when inserting new data, the heap property must maintained thus from bottom to top is scanned (to ensure the element will be at top)
 * , but only at one side of the tree that the element inserted --> O(logN)
 * 2. since the heap property is maintained from bottom to top already,
 * when removeMax we only need to call heapify (that only deal with the a subtree where the children of the max node surely is the next max value
 * </p>
 *
 * <p>
 * In conclusion: `insert` method helps a lot in terms of maintaining the heap property and thus much more efficient when other methods are called.
 * </p>
 * <p>
 * `insert` method this way is also the reason of why there is a priority queue where maxHeap decrease as we go down the tree.
 * </p>
 */
public class HeapWithReference {
    int maxSize;
    int heapSize;
    int[] arr;
    int root;

    HeapWithReference(int size) {
        this.maxSize = size;
        this.heapSize = 0;
        this.arr = new int[size];
        this.root = 0;
    }

    public static void main(String[] args) {
        HeapWithReference obj = new HeapWithReference(9);
        obj.insert(2);
        obj.insert(3);
        obj.insert(6);
        obj.insert(10);
        obj.insert(1);
        obj.insert(9);
        obj.insert(4);
        obj.insert(-1);
        obj.insert(7);

        obj.maxHeapifyASubTree(0);
        System.out.println(Arrays.toString(obj.arr));
        System.out.println(obj.removeMax());
        System.out.println(Arrays.toString(obj.arr));


    }

    int leftChild(int i) {
        return 2 * i + 1;
    }

    int rightChild(int i) {
        return 2 * i + 2;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    void maxHeapifyASubTree(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapifyASubTree(largest);
        }
    }

    void insert(int data) {
        if (heapSize >= maxSize) {
            throw new IndexOutOfBoundsException("Heap is Overflow");
        }

        heapSize++;
        int i = heapSize - 1;
        arr[i] = data;

        while (i != 0 && arr[i] > arr[parent(i)]) {
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }

    // note: there is no perfect way (unless you use copyOfRange)
    // to eliminate the data, just stick with the heapSize will do, ignore element beyond heapSize
    int removeMax() {
        if (heapSize == 1) {
            heapSize--;
            return arr[root];
        }

        int max = arr[root];

        arr[root] = arr[heapSize - 1];
        heapSize--;
        maxHeapifyASubTree(root); // this will not work well if insert didnt maintain heap property

        return max;

    }
}
