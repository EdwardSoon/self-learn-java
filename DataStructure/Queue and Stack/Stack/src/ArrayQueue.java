// https://www.geeksforgeeks.org/introduction-and-array-implementation-of-queue/

// use array to implement a queue
/* what do a QUEUE needs to be a queue?
 * - an array being the queue
 * - front pointer = front index
 * - rear pointer = rear index
 * - capacity of the array -- the size initiated at the first (max size to take the array elements in)
 */



public class ArrayQueue{
    // attributes 
    private final int capacity;    // the max size a queue can take and for initiating an array // we fix the capcity so no change of capacity as array memory is not dynamic
    private int[] queue;
    private int front;             // first pointer index
    private int rear;              // last pointer index
    int size;              // to keep track the size of the queue (as queue size != array size)

    // constructor
    public ArrayQueue(int capacity){
        this.capacity = capacity;
        this.queue = new int[this.capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;      
    }
    /* 
     * 1. check if the queue is full first, then dont add anything, prrint that it is overflow  
     * 2. if the queue still not full then add to the last one , and increment tail
     */
    public void enqueue(int data){
        if(isFull()){
            System.out.println("The queue is overflow");
            return;
        }
        rear = (rear+1) % this.capacity; // given the capcity 
        queue[rear] = data;
        size ++;
        
        // capacity++; 
            // just testing the final keyword, this will result error cannot compile
            // if you dont put final and change the capcity then it will also result error exception
    }

    /* 
     * 1. if queue has nothing then print underflow as we cannot drop anything
     * 2. if queue has soemthing then we drop the first element and return the first element
     */
    public int dequeue(){
        if(isEmpty()){
            System.out.println("The queue is underflow");
            return Integer.MIN_VALUE;   // to indicate it is an error
        }
        int removed = queue[front];
        /* 
        THIS IMPLEMENTED THINKING THAT QUEUE SHOULD BE = ENTIRE ARRAY, WHICH HAS LOWER EFFICIENCY
        while (front < rear){   // move elements from behind to one element infront
            queue[front] = queue[front+1]; 
            front++;
        }
        queue[rear] = 0; // need to assign the last value to become 0 means no more element there (cant be in while loop as it might out of index bound if it is full)
        // reset front and rear to the right position
        front = 0;
        rear--;   // as now the queue has one less element
        */
        front = (front+1) % this.capacity;
        size --;
        return removed;
    }

    public void printQueue(){
        int temp_front = front;
        int temp_size = size;
        System.out.print("Queue elements: ");
        while (temp_size > 0){
            System.out.print(queue[temp_front] + " ");
            temp_front = (temp_front+1)% capacity;
            temp_size--;
        }
        System.out.println();
    }

    public int front(){
        if (isEmpty()){
            return Integer.MIN_VALUE;
        }
        return queue[front];
    }

    public int rear(){
        if (isEmpty()){
            return Integer.MIN_VALUE;
        }
        return queue[rear];
    }

    public boolean isEmpty(){
        if(this.size <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFull(){
        if(this.size == this.capacity){
            return true;
        }
        else{
            return false;
        }
    }





    public static void main(String[] args) throws Exception {
        ArrayQueue arrq = new ArrayQueue(1000);
        System.out.println(arrq.isEmpty());
        arrq.enqueue(87);
        arrq.enqueue(5);
        arrq.enqueue(10);
        arrq.enqueue(20);
        arrq.enqueue(30);
        arrq.enqueue(40);
        arrq.printQueue();
        System.out.println("fIndex:" + arrq.front + "; front:" + arrq.front());
        // System.out.println(arrq.isFull());
        System.out.println("rIndex:" + arrq.rear + "; rear:" + arrq.rear());
        // arrq.printQueue();
        
        System.out.println("dequeued:" + arrq.dequeue());
        System.out.println("fIndex:" + arrq.front + "; front:" + arrq.front());
        // System.out.println(arrq.isFull());
        System.out.println("rIndex:" + arrq.rear + "; rear:" + arrq.rear());       
        arrq.printQueue();
        
        // arrq.printQueue();
        // arrq.enqueue(5);
        // System.out.println(arrq.front());
        // arrq.dequeue();
        // System.out.println(arrq.isFull());
        // arrq.printQueue();
        // arrq.dequeue();
        // // arrq.dequeue();
        // arrq.printQueue();
        // System.out.println(arrq.front());


        // ArrayQueue arrx = new ArrayQueue(1);
        // arrx.enqueue(100);
        // arrx.enqueue(90);
        // arrx.printQueue();
        // arrx.dequeue();
        // arrx.printQueue();
        
        
    }
}
